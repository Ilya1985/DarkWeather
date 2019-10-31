package ru.tatarchuk.darkweather.ui.main.root.recycler;

import android.graphics.drawable.Drawable;

import ru.tatarchuk.darkweather.R;
import ru.tatarchuk.darkweather.ui.base.BaseItem;

public class DailyItem implements BaseItem {

    private String mDayOfWeek;
    private String mDate;
    private String mTempMin;
    private String mTempMax;
    private Drawable mIcon;

    @Override
    public int getLayoutResId() {
        return R.layout.view_daily;
    }

    public String getDayOfWeek() {
        return mDayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        mDayOfWeek = dayOfWeek;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getTempMin() {
        return mTempMin;
    }

    public void setTempMin(String tempMin) {
        mTempMin = tempMin;
    }

    public String getTempMax() {
        return mTempMax;
    }

    public void setTempMax(String tempMax) {
        mTempMax = tempMax;
    }

    public Drawable getIcon() {
        return mIcon;
    }

    public void setIcon(Drawable icon) {
        mIcon = icon;
    }
}
