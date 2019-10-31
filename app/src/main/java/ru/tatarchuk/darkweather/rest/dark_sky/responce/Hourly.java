
package ru.tatarchuk.darkweather.rest.dark_sky.responce;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class Hourly {

    @SerializedName("data")
    private List<HourlyDatum> mData;
    @SerializedName("icon")
    private String mIcon;
    @SerializedName("summary")
    private String mSummary;

    public List<HourlyDatum> getData() {
        return mData;
    }

    public void setData(List<HourlyDatum> data) {
        mData = data;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

}
