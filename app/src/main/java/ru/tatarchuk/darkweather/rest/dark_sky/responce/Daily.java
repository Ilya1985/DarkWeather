
package ru.tatarchuk.darkweather.rest.dark_sky.responce;

import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity
public class Daily {

    @SerializedName("data")
    private List<DailyDatum> mData;
    @SerializedName("icon")
    private String mIcon;
    @SerializedName("summary")
    private String mSummary;

    public List<DailyDatum> getData() {
        return mData;
    }

    public void setData(List<DailyDatum> data) {
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
