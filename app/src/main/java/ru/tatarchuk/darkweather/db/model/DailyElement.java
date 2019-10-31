package ru.tatarchuk.darkweather.db.model;

import android.graphics.drawable.Drawable;

import androidx.room.ColumnInfo;
import androidx.room.TypeConverters;

import org.threeten.bp.LocalDateTime;

import ru.tatarchuk.darkweather.db.type_converters.IconConverter;
import ru.tatarchuk.darkweather.db.type_converters.TimeConverter;
import ru.tatarchuk.darkweather.ui.main.root.recycler.DailyItem;
import ru.tatarchuk.darkweather.utils.AppDateFormatter;
import ru.tatarchuk.darkweather.utils.AppNumberFormatter;

public class DailyElement implements BaseElement<DailyItem> {

    @ColumnInfo(name = "mTime")
    @TypeConverters({TimeConverter.class})
    private LocalDateTime mTime;
    @ColumnInfo(name = "mTemperatureMin")
    private Double mTempMin;
    @ColumnInfo(name = "mTemperatureMax")
    private Double mTempMax;
    @ColumnInfo(name = "mIcon")
    @TypeConverters({IconConverter.class})
    private Drawable mIcon;

    public DailyItem toItem() {
        DailyItem item = new DailyItem();
        item.setDayOfWeek(mTime.format(AppDateFormatter.getDayOfWeek()));
        item.setDate(mTime.format(AppDateFormatter.getDayAndMonth()));
        item.setTempMin(AppNumberFormatter.doubleToDeg(mTempMin));
        item.setTempMax(AppNumberFormatter.doubleToDeg(mTempMax));
        item.setIcon(mIcon);
        return item;
    }

    public LocalDateTime getTime() {
        return mTime;
    }

    public void setTime(LocalDateTime time) {
        mTime = time;
    }

    public Double getTempMin() {
        return mTempMin;
    }

    public void setTempMin(Double tempMin) {
        mTempMin = tempMin;
    }

    public Double getTempMax() {
        return mTempMax;
    }

    public void setTempMax(Double tempMax) {
        mTempMax = tempMax;
    }

    public Drawable getIcon() {
        return mIcon;
    }

    public void setIcon(Drawable icon) {
        mIcon = icon;
    }
}
