package ru.tatarchuk.darkweather.db.model;

import android.graphics.drawable.Drawable;

import androidx.room.ColumnInfo;
import androidx.room.TypeConverters;

import org.threeten.bp.LocalDateTime;

import ru.tatarchuk.darkweather.db.type_converters.IconConverter;
import ru.tatarchuk.darkweather.db.type_converters.TimeConverter;
import ru.tatarchuk.darkweather.ui.main.root.recycler.hourly.HourlyItem;
import ru.tatarchuk.darkweather.utils.AppDateFormatter;
import ru.tatarchuk.darkweather.utils.AppNumberFormatter;

public class HourlyElement implements BaseElement<HourlyItem> {

    @ColumnInfo(name = "mTime")
    @TypeConverters({TimeConverter.class})
    private LocalDateTime mTime;
    @ColumnInfo(name = "mIcon")
    @TypeConverters({IconConverter.class})
    private Drawable mIcon;
    @ColumnInfo(name = "mTemperature")
    private Double mTemp;

    public HourlyItem toItem() {
        HourlyItem item = new HourlyItem();
        item.setIcon(mIcon);
        item.setTemp(AppNumberFormatter.doubleToDeg(mTemp));
        item.setTime(mTime.format(AppDateFormatter.getTime24()));
        return item;
    }

    public LocalDateTime getTime() {
        return mTime;
    }

    public void setTime(LocalDateTime time) {
        mTime = time;
    }

    public Drawable getIcon() {
        return mIcon;
    }

    public void setIcon(Drawable icon) {
        mIcon = icon;
    }

    public Double getTemp() {
        return mTemp;
    }

    public void setTemp(Double temp) {
        mTemp = temp;
    }
}
