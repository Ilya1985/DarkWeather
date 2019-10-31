package ru.tatarchuk.darkweather.db.model;

import android.util.Log;

import androidx.room.ColumnInfo;

import ru.tatarchuk.darkweather.R;
import ru.tatarchuk.darkweather.ui.main.root.recycler.hourly.CurrentDetailItem;
import ru.tatarchuk.darkweather.utils.AppNumberFormatter;
import ru.tatarchuk.darkweather.utils.UnitConverter;
import ru.tatarchuk.darkweather.utils.Utils;

public class CurrentDetailElement implements BaseElement<CurrentDetailItem> {

    private static String TAG = "Weather " + CurrentDetailElement.class.getSimpleName();

    @ColumnInfo(name = "mPressure")
    private Double mPressure;
    @ColumnInfo(name = "mHumidity")
    private Double mHumidity;
    @ColumnInfo(name = "mWindSpeed")
    private Double mWindSpeed;
    @ColumnInfo(name = "mWindBearing")
    private Integer mWindBearing;

    public CurrentDetailItem toItem() {
        CurrentDetailItem item = new CurrentDetailItem();
        item.setPressure(UnitConverter.hPaToMmHg(mPressure));
        item.setPressureUnit(R.string.mmHg);
        item.setHumidity(AppNumberFormatter.round(mHumidity * 100) + "%");
        item.setWindSpeed(AppNumberFormatter.round(mWindSpeed));
        item.setSpeedUnit(R.string.Ms);
        item.setWindBearing(Utils.degToDir(mWindBearing));
        return item;
    }

    public Double getPressure() {
        return mPressure;
    }

    public void setPressure(Double pressure) {
        mPressure = pressure;
    }

    public Double getHumidity() {
        return mHumidity;
    }

    public void setHumidity(Double humidity) {
        mHumidity = humidity;
    }

    public Double getWindSpeed() {
        return mWindSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        mWindSpeed = windSpeed;
    }

    public Integer getWindBearing() {
        return mWindBearing;
    }

    public void setWindBearing(Integer windBearing) {
        mWindBearing = windBearing;
    }
}
