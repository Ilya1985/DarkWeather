package ru.tatarchuk.darkweather.ui.main.root.recycler.hourly;

import java.util.List;

import ru.tatarchuk.darkweather.R;
import ru.tatarchuk.darkweather.ui.base.BaseItem;

public class ListHourlyItem implements BaseItem {

    private List<BaseItem> mItems;

    @Override
    public int getLayoutResId() {
        return R.layout.view_list_hourly;
    }

    public ListHourlyItem(List<BaseItem> items) {
        mItems = items;
    }

    public List<BaseItem> getItems() {
        return mItems;
    }
}
