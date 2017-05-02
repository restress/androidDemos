package com.example.restress.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by restress on 2017/4/27.
 */

public class Crime {

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;


    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public UUID getId() {

        return mId;

    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public Date getDate() {

        return mDate;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public Crime(){

        mId = UUID.randomUUID();
        mDate = new Date();
    }



}
