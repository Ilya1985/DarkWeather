package ru.tatarchuk.darkweather.ui.main.root.recycler;

import android.view.View;

import ru.tatarchuk.darkweather.R;
import ru.tatarchuk.darkweather.ui.base.BaseRecyclerAdapter;
import ru.tatarchuk.darkweather.ui.base.BaseViewHolder;
import ru.tatarchuk.darkweather.ui.base.BaseViewHolderFactory;
import ru.tatarchuk.darkweather.ui.base.ViewHolderClickListener;
import ru.tatarchuk.darkweather.ui.main.root.recycler.hourly.ListHourlyViewHolder;

public class MainAdapter extends BaseRecyclerAdapter {

    @Override
    protected BaseViewHolderFactory getFactory() {
        return new VHFactory();
    }

    private class VHFactory extends BaseViewHolderFactory {

        @Override
        protected BaseViewHolder createViewHolder(View view, int type, ViewHolderClickListener listener) {

            switch (type) {
                case R.layout.view_current:
                    return new CurrentlyViewHolder(view, listener);
                case R.layout.view_daily:
                    return new DailyViewHolder(view, listener);
                case R.layout.view_list_hourly:
                    return new ListHourlyViewHolder(view, listener);
                default:
                    throw exception(type);
            }
        }
    }
}
