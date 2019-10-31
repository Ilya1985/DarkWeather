package ru.tatarchuk.darkweather.ui.main.root.recycler.hourly;

import ru.tatarchuk.darkweather.R;
import ru.tatarchuk.darkweather.ui.base.BaseItem;

public class CurrentDetailItem implements BaseItem {

    private String mPressure;
    private Integer mPressureUnit;
    private String mHumidity;
    private String mWindSpeed;
    private Integer mSpeedUnit;
    private Integer mWindBearing;

    @Override
    public int getLayoutResId() {
        return R.layout.view_current_detail;
    }

    public String getPressure() {
        return mPressure;
    }

    public void setPressure(String pressure) {
        mPressure = pressure;
    }

    public String getHumidity() {
        return mHumidity;
    }

    public void setHumidity(String humidity) {
        mHumidity = humidity;
    }

    public String getWindSpeed() {
        return mWindSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        mWindSpeed = windSpeed;
    }

    public Integer getWindBearing() {
        return mWindBearing;
    }

    public void setWindBearing(Integer windBearing) {
        mWindBearing = windBearing;
    }

    public Integer getPressureUnit() {
        return mPressureUnit;
    }

    public void setPressureUnit(Integer pressureUnit) {
        mPressureUnit = pressureUnit;
    }

    public Integer getSpeedUnit() {
        return mSpeedUnit;
    }

    public void setSpeedUnit(Integer speedUnit) {
        mSpeedUnit = speedUnit;
    }
}
