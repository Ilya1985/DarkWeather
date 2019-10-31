
package ru.tatarchuk.darkweather.rest.dark_sky.responce;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import ru.tatarchuk.darkweather.db.model.DataLocation;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = DataLocation.class, parentColumns = "mId", childColumns = "mLocationId", onDelete = CASCADE))
public class Currently {

    @PrimaryKey(autoGenerate = true)
    private long mCurrentlyId;
    private String mLocationId;

    @SerializedName("apparentTemperature")
    private Double mApparentTemperature;
    @SerializedName("cloudCover")
    private Double mCloudCover;
    @SerializedName("dewPoint")
    private Double mDewPoint;
    @SerializedName("humidity")
    private Double mHumidity;
    @SerializedName("icon")
    private String mIcon;
    @SerializedName("ozone")
    private Double mOzone;
    @SerializedName("precipIntensity")
    private Double mPrecipIntensity;
    @SerializedName("precipProbability")
    private Double mPrecipProbability;
    @SerializedName("pressure")
    private Double mPressure;
    @SerializedName("summary")
    private String mSummary;
    @SerializedName("temperature")
    private Double mTemperature;
    @SerializedName("time")
    private Long mTime;
    @SerializedName("uvIndex")
    private Double mUvIndex;
    @SerializedName("visibility")
    private Double mVisibility;
    @SerializedName("windBearing")
    private Long mWindBearing;
    @SerializedName("windGust")
    private Double mWindGust;
    @SerializedName("windSpeed")
    private Double mWindSpeed;

    public long getCurrentlyId() {
        return mCurrentlyId;
    }

    public void setCurrentlyId(long currentlyId) {
        mCurrentlyId = currentlyId;
    }

    public String getLocationId() {
        return mLocationId;
    }

    public void setLocationId(String locationId) {
        mLocationId = locationId;
    }

    public Double getApparentTemperature() {
        return mApparentTemperature;
    }

    public void setApparentTemperature(Double apparentTemperature) {
        mApparentTemperature = apparentTemperature;
    }

    public Double getCloudCover() {
        return mCloudCover;
    }

    public void setCloudCover(Double cloudCover) {
        mCloudCover = cloudCover;
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

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public Double getOzone() {
        return mOzone;
    }

    public void setOzone(Double ozone) {
        mOzone = ozone;
    }

    public Double getPressure() {
        return mPressure;
    }

    public void setPressure(Double pressure) {
        mPressure = pressure;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public Double getTemperature() {
        return mTemperature;
    }

    public void setTemperature(Double temperature) {
        mTemperature = temperature;
    }

    public Long getTime() {
        return mTime;
    }

    public void setTime(Long time) {
        mTime = time;
    }

    public Double getVisibility() {
        return mVisibility;
    }

    public void setVisibility(Double visibility) {
        mVisibility = visibility;
    }

    public Long getWindBearing() {
        return mWindBearing;
    }

    public void setWindBearing(Long windBearing) {
        mWindBearing = windBearing;
    }

    public Double getWindGust() {
        return mWindGust;
    }

    public void setWindGust(Double windGust) {
        mWindGust = windGust;
    }

    public Double getWindSpeed() {
        return mWindSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        mWindSpeed = windSpeed;
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

    public Double getUvIndex() {
        return mUvIndex;
    }

    public void setUvIndex(Double uvIndex) {
        mUvIndex = uvIndex;
    }
}
