package ru.tatarchuk.darkweather.db.model;

import androidx.room.ColumnInfo;


public class LocAddress {

    @ColumnInfo(name = "mFirstName")
    private String mFirst;
    @ColumnInfo(name = "mSecondName")
    private String mSecond;

    public LocAddress() {
        mFirst = "";
        mSecond = "";
    }

    public String getFirst() {
        return mFirst;
    }

    public void setFirst(String first) {
        mFirst = first;
    }

    public String getSecond() {
        return mSecond;
    }

    public void setSecond(String second) {
        mSecond = second;
    }
}
