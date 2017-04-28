package com.example.restress.criminalintent;


import android.support.v4.app.Fragment;

/**
 * Created by restress on 2017/4/27.
 */

public class CrimeListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new CrimeListFragment();
    }
}
