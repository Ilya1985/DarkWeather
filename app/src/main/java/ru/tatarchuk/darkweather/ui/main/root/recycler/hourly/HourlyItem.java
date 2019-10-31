package ru.tatarchuk.darkweather.ui.main.root.recycler.hourly;

import android.graphics.drawable.Drawable;

import ru.tatarchuk.darkweather.R;
import ru.tatarchuk.darkweather.ui.base.BaseItem;

public class HourlyItem implements BaseItem {

    private String mTime;
    private Drawable mIcon;
    private String mTemp;

    @Override
    public int getLayoutResId() {
        return R.layout.view_hourly;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public Drawable getIcon() {
        return mIcon;
    }

    public void setIcon(Drawable icon) {
        mIcon = icon;
    }

    public String getTemp() {
        return mTemp;
    }

    public void setTemp(String temp) {
        mTemp = temp;
    }
}
