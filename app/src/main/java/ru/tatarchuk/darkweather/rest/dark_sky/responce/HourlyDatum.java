package ru.tatarchuk.darkweather.rest.dark_sky.responce;

import android.graphics.drawable.Drawable;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import ru.tatarchuk.darkweather.R;
import ru.tatarchuk.darkweather.db.model.DataLocation;
import ru.tatarchuk.darkweather.ui.base.BaseItem;
import ru.tatarchuk.darkweather.utils.AppNumberFormatter;
import ru.tatarchuk.darkweather.utils.DrawableUtils;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = DataLocation.class, parentColumns = "mId", childColumns = "mLocationId", onDelete = CASCADE))
public class HourlyDatum implements BaseItem {

    @PrimaryKey(autoGenerate = true)
    private long mHourlyId;
    private String mLocationId;

    @SerializedName("time")
    private Long mTime;
    @SerializedName("summary")
    private String mSummary;
    @SerializedName("icon")
    private String mIcon;
    @SerializedName("precipIntensity")
    private Double mPrecipIntensity;
    @SerializedName("precipProbability")
    private Double mPrecipProbability;
    @SerializedName("temperature")
    private Double mTemperature;
    @SerializedName("apparentTemperature")
    private Double mApparentTemperature;
    @SerializedName("dewPoint")
    private Double mDewPoint;
    @SerializedName("humidity")
    private Double mHumidity;
    @SerializedName("pressure")
    private Double mPressure;
    @SerializedName("windSpeed")
    private Double mWindSpeed;
    @SerializedName("windGust")
    private Double mWindGust;
    @SerializedName("windBearing")
    private Long mWindBearing;
    @SerializedName("cloudCover")
    private Double mCloudCover;
    @SerializedName("uvIndex")
    private Long mUvIndex;
    @SerializedName("visibility")
    private Double mVisibility;
    @SerializedName("ozone")
    private Double mOzone;

    public long getHourlyId() {
        return mHourlyId;
    }

    public void setHourlyId(long hourlyId) {
        mHourlyId = hourlyId;
    }

    public String getLocationId() {
        return mLocationId;
    }

    public void setLocationId(String locationId) {
        mLocationId = locationId;
    }

    public Long getTime() {
        return mTime;
    }

    public void setTime(Long time) {
        mTime = time;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public Double getPrecipIntensity() {
        return mPrecipIntensity;
    }

    public void setPrecipIntensity(Double precipIntensity) {
        mPrecipIntensity = precipIntensity;
    }

    public Double getPrecipProbability() {
        return mPrecipProbability;
    }

    public void setPrecipProbability(Double precipProbability) {
        mPrecipProbability = precipProbability;
    }

    public Double getTemperature() {
        return mTemperature;
    }

    public void setTemperature(Double temperature) {
        mTemperature = temperature;
    }

    public Double getApparentTemperature() {
        return mApparentTemperature;
    }

    public void setApparentTemperature(Double apparentTemperature) {
        mApparentTemperature = apparentTemperature;
    }

    public Double getDewPoint() {
        return mDewPoint;
    }

    public void setDewPoint(Double dewPoint) {
        mDewPoint = dewPoint;
    }

    public Double getHumidity() {
        return mHumidity;
    }

    public void setHumidity(Double humidity) {
        mHumidity = humidity;
    }

    public Double getPressure() {
        return mPressure;
    }

    public void setPressure(Double pressure) {
        mPressure = pressure;
    }

    public Double getWindSpeed() {
        return mWindSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        mWindSpeed = windSpeed;
    }

    public Double getWindGust() {
        return mWindGust;
    }

    public void setWindGust(Double windGust) {
        mWindGust = windGust;
    }

    public Long getWindBearing() {
        return mWindBearing;
    }

    public void setWindBearing(Long windBearing) {
        mWindBearing = windBearing;
    }

    public Double getCloudCover() {
        return mCloudCover;
    }

    public void setCloudCover(Double cloudCover) {
        mCloudCover = cloudCover;
    }

    public Long getUvIndex() {
        return mUvIndex;
    }

    public void setUvIndex(Long uvIndex) {
        mUvIndex = uvIndex;
    }

    public Double getVisibility() {
        return mVisibility;
    }

    public void setVisibility(Double visibility) {
        mVisibility = visibility;
    }

    public Double getOzone() {
        return mOzone;
    }

    public void setOzone(Double ozone) {
        mOzone = ozone;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.view_hourly_by_date;
    }

    public String getTemp () {
        return AppNumberFormatter.doubleToDeg(mTemperature);
    }

    public Drawable getDrawIcon() {
        return DrawableUtils.getDrawable(mIcon);
    }

    public String getPress() {
        return AppNumberFormatter.round(mPressure);
    }

    public String getHumid() {
        return AppNumberFormatter.round(mHumidity) + "%";
    }

    public String getWinDSp() {
        return AppNumberFormatter.round(mWindSpeed);
    }
}

