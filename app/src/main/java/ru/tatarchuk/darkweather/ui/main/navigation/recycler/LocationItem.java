package ru.tatarchuk.darkweather.ui.main.navigation.recycler;

import android.graphics.drawable.Drawable;

import androidx.room.ColumnInfo;
import androidx.room.TypeConverters;

import ru.tatarchuk.darkweather.R;
import ru.tatarchuk.darkweather.db.type_converters.IconConverter;
import ru.tatarchuk.darkweather.ui.base.BaseItem;
import ru.tatarchuk.darkweather.utils.AppNumberFormatter;
import ru.tatarchuk.darkweather.utils.ISharePref;

import static ru.tatarchuk.darkweather.utils.Const.CURRENT_LOCATION_ID;

public class LocationItem implements BaseItem {

    @ColumnInfo(name = "mLocationId")
    private String mId;
    @ColumnInfo(name = "mFirstName")
    private String mFirstName;
    @ColumnInfo(name = "mSecondName")
    private String mSecondName;
    @ColumnInfo(name = "mTemperature")
    private Double mTemperature;
    @ColumnInfo(name = "mIcon")
    @TypeConverters({IconConverter.class})
    private Drawable mIcon;

    @Override
    public int getLayoutResId() {
        return mId.equals(CURRENT_LOCATION_ID) ? R.layout.view_current_location : R.layout.view_location;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

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

    public Double getTemperature() {
        return mTemperature;
    }

    public void setTemperature(Double temperature) {
        mTemperature = temperature;
    }

    public Drawable getIcon() {
        return mIcon;
    }

    public void setIcon(Drawable icon) {
        mIcon = icon;
    }

    public String getTem() {
        return AppNumberFormatter.doubleToDeg(mTemperature);
    }

    public int getColor() {
        return mId.equals(ISharePref.getLocationId()) ? R.color.colorPrimaryText : R.color.colorSecondaryText;
    }
}
