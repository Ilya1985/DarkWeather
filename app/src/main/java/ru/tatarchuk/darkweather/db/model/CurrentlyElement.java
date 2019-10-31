package ru.tatarchuk.darkweather.db.model;

import android.graphics.drawable.Drawable;

import androidx.room.ColumnInfo;
import androidx.room.TypeConverters;

import ru.tatarchuk.darkweather.db.type_converters.IconConverter;
import ru.tatarchuk.darkweather.ui.main.root.recycler.CurrentlyItem;
import ru.tatarchuk.darkweather.utils.AppNumberFormatter;

public class CurrentlyElement implements BaseElement<CurrentlyItem>{

    @ColumnInfo(name = "mIcon")
    @TypeConverters({IconConverter.class})
    private Drawable mIcon;
    @ColumnInfo(name = "mSummary")
    private String mSummary;
    @ColumnInfo(name = "mTemperature")
    private Double mTemp;
    @ColumnInfo(name = "mApparentTemperature")
    private Double mFlTemp;

    public CurrentlyItem toItem() {
        CurrentlyItem item = new CurrentlyItem();
        item.setIcon(getIcon());
        item.setSummary(getSummary());
        item.setTemp(AppNumberFormatter.doubleToDeg(getTemp()));
        item.setFlTemp(AppNumberFormatter.doubleToDeg(getTemp()));
        return item;
    }

    public Drawable getIcon() {
        return mIcon;
    }

    public void setIcon(Drawable icon) {
        mIcon = icon;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public Double getTemp() {
        return mTemp;
    }

    public void setTemp(Double temp) {
        mTemp = temp;
    }

    public Double getFlTemp() {
        return mFlTemp;
    }

    public void setFlTemp(Double flTemp) {
        mFlTemp = flTemp;
    }
}
