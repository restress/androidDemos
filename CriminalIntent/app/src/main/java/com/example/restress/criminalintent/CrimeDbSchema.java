package com.example.restress.criminalintent;

/**
 * Created by restress on 2017/5/4.
 */

public class CrimeDbSchema {

    public static final class CrimeTable{
        public static final String NAME = "crimes";

        public static final class Cols{
            public static final String UUID = "uuis";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String SOLVED = "solved";
            public static final String SUSPECT = "suspect";
        }
    }


}
