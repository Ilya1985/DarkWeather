package ru.tatarchuk.darkweather.ui.main.root.recycler;

import android.graphics.drawable.Drawable;

import ru.tatarchuk.darkweather.R;
import ru.tatarchuk.darkweather.ui.base.BaseItem;

public class CurrentlyItem implements BaseItem {

    private Drawable mIcon;
    private String mSummary;
    private String mTemp;
    private String mFlTemp;

    @Override
    public int getLayoutResId() {
        return R.layout.view_current;
    }

    public Drawable getIcon() {
        return mIcon;
    }

    public void setIcon(Drawable icon) {
        mIcon = icon;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public String getTemp() {
        return mTemp;
    }

    public void setTemp(String temp) {
        mTemp = temp;
    }

    public String getFlTemp() {
        return mFlTemp;
    }

    public void setFlTemp(String flTemp) {
        mFlTemp = flTemp;
    }
}
