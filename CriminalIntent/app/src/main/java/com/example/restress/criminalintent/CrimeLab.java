package com.example.restress.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by restress on 2017/4/27.
 */

public class CrimeLab {
    private static CrimeLab sCrimeLab;
    /*private List<Crime> mCrimes;*/
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static CrimeLab get(Context context){
        if (sCrimeLab == null){
            sCrimeLab =new CrimeLab(context);
        }
        return sCrimeLab;
    }


    private CrimeLab(Context context){
        mContext =context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext)
                .getWritableDatabase();
        /*mCrimes = new ArrayList<>();*/
        /*for (int i=0; i<100;i++){
            Crime crime = new Crime();
            crime.setTitle("Crime #" +i);
            crime.setSolved(i%2 ==0);//every other one
            mCrimes.add(crime);
        }*/
    }

    public List<Crime> getCrimes() {
        /*return mCrimes;*/
        /*return new ArrayList<>();*/
        List<Crime> crimes = new ArrayList<>();

        CrimeCursorWrapper cursor = queryCrimes(null,null);

        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }

        return crimes;
    }

   public Crime getCrime(UUID id){
      /* for (Crime crime:mCrimes){
           if (crime.getId().equals(id)){
               return crime;
           }
       }*/
       /*return null;*/
       CrimeCursorWrapper cursor = queryCrimes(
               CrimeDbSchema.CrimeTable.Cols.UUID + "= ?",
               new String[] {id.toString()}
       );

       try {
           if (cursor.getCount() ==0){
               return null;
           }
           cursor.moveToFirst();
           return cursor.getCrime();
       }finally {
           cursor.close();
       }
   }

   public File getPhotoFile(Crime crime){
       File externalFileDir = mContext
               .getExternalFilesDir(Environment.DIRECTORY_PICTURES);

       if (externalFileDir == null){
           return null;
       }

       return new File(externalFileDir,crime.getPhotoFilename());
   }

   public void addCrimes(Crime c){
       ContentValues values =getContentValues(c);

       mDatabase.insert(CrimeDbSchema.CrimeTable.NAME,null,values);
       /*mCrimes.add(c);*/
   }

   public void updateCrime(Crime crime){
       String uuidString = crime.getId().toString();
       ContentValues values = getContentValues(crime);

       mDatabase.update(CrimeDbSchema.CrimeTable.NAME,values,
               CrimeDbSchema.CrimeTable.Cols.UUID+"=?",
               new String[]{uuidString});
   }

   private CrimeCursorWrapper queryCrimes(String whereClause,String[] whereargs){
       Cursor cursor =mDatabase.query(
               CrimeDbSchema.CrimeTable.NAME,
               null,//Colums-null selescts all columrd
               whereClause,
               whereargs,
               null,//groupby
               null,//having
               null//orderby
       );
       /*return cursor;*/
       return new CrimeCursorWrapper(cursor);
   }

   private static ContentValues getContentValues(Crime crime){
       ContentValues values =new ContentValues();
       values.put(CrimeDbSchema.CrimeTable.Cols.UUID,crime.getId().toString());
       values.put(CrimeDbSchema.CrimeTable.Cols.TITLE,crime.getTitle());
       values.put(CrimeDbSchema.CrimeTable.Cols.DATE,crime.getDate().getTime());
       values.put(CrimeDbSchema.CrimeTable.Cols.SOLVED,crime.isSolved()?1:0);
       values.put(CrimeDbSchema.CrimeTable.Cols.SUSPECT,crime.getSuspect());


       return values;
   }






}
