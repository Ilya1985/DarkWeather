
package ru.tatarchuk.darkweather.rest.dark_sky.responce;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Weather {

    @SerializedName("currently")
    private Currently mCurrently;
    @SerializedName("daily")
    private Daily mDaily;
    @SerializedName("hourly")
    private Hourly mHourly;
    @SerializedName("latitude")
    private Double mLatitude;
    @SerializedName("longitude")
    private Double mLongitude;
    @SerializedName("offset")
    private Double mOffset;
    @SerializedName("timezone")
    private String mTimezone;

    public Currently getCurrently() {
        return mCurrently;
    }

    public void setCurrently(Currently currently) {
        mCurrently = currently;
    }

    public Daily getDaily() {
        return mDaily;
    }

    public void setDaily(Daily daily) {
        mDaily = daily;
    }

    public Hourly getHourly() {
        return mHourly;
    }

    public void setHourly(Hourly hourly) {
        mHourly = hourly;
    }

    public Double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(Double latitude) {
        mLatitude = latitude;
    }

    public Double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(Double longitude) {
        mLongitude = longitude;
    }

    public Double getOffset() {
        return mOffset;
    }

    public void setOffset(Double offset) {
        mOffset = offset;
    }

    public String getTimezone() {
        return mTimezone;
    }

    public void setTimezone(String timezone) {
        mTimezone = timezone;
    }

    public void setId(String id) {
        mCurrently.setLocationId(id);
        mCurrently.setIcon(mCurrently.getIcon().replace('-', '_'));
        for (HourlyDatum datum : mHourly.getData()) {
            datum.setLocationId(id);
            datum.setIcon(datum.getIcon().replace('-', '_'));
        }

        for (DailyDatum datum : mDaily.getData()) {
            datum.setLocationId(id);
            datum.setIcon(datum.getIcon().replace('-', '_'));
        }
    }
}
