package com.example.restress.criminalintent;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

/**
 * Created by restress on 2017/5/6.
 */

public class PictureUtils {

    /*inSampleSize值很关键。它决定着缩略图像素的大小。假设这个值是1的话，276 第 16 章 使用 Intent 拍照
    就表明缩略图和原始照片的水平像素大小一样。如果是2的话，它们的水平像素比就是1∶2。因
    此， inSampleSize值为2时，缩略图的像素数就是原始文件的四分之一。*/
    public static Bitmap getScaledBitmap(String path,int destWidth,int destHeight){
        //Read in the dimensions of the image on the disk
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path,options);

        float srcWidth = options.outWidth;
        float srcHeight = options.outHeight;

        //Figure out how muc to scale down by
        int inSampleSize = 1;
        if (srcWidth > destHeight || srcWidth > destWidth){
            if (srcWidth > srcHeight){
                inSampleSize = Math.round(srcHeight/destHeight);
            }else {
                inSampleSize = Math.round(srcWidth / destWidth);
            }
        }

        options = new BitmapFactory.Options();
        options.inSampleSize = inSampleSize;

        //Read in and creat final bitmap
        return BitmapFactory.decodeFile(path,options);
    }

    public static Bitmap getScaledBitmap(String path, Activity activity){
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay()
                .getSize(size);

        return getScaledBitmap(path,size.x,size.y);
    }

}
