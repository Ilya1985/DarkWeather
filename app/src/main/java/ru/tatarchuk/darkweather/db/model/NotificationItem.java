package ru.tatarchuk.darkweather.db.model;

import android.graphics.drawable.Drawable;

import androidx.room.ColumnInfo;

public class NotificationItem {

    @ColumnInfo(name = "mFirstName")
    private String mFirstName;
    @ColumnInfo(name = "mSecondName")
    private String mSecondName;
    @ColumnInfo(name = "mIcon")
    private Drawable mIcon;
    @ColumnInfo(name = "mTemperature")
    private Double mTemperature;

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getSecondName() {
        return mSecondName;
    }

    public void setSecondName(String secondName) {
        mSecondName = secondName;
    }

    public Drawable getIcon() {
        return mIcon;
    }

    public void setIcon(Drawable icon) {
        mIcon = icon;
    }

    public Double getTemperature() {
        return mTemperature;
    }

    public void setTemperature(Double temperature) {
        mTemperature = temperature;
    }
}
