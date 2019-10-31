package ru.tatarchuk.darkweather.ui.main.root.recycler.hourly;

import android.view.View;

import ru.tatarchuk.darkweather.R;
import ru.tatarchuk.darkweather.ui.base.BaseRecyclerAdapter;
import ru.tatarchuk.darkweather.ui.base.BaseViewHolder;
import ru.tatarchuk.darkweather.ui.base.BaseViewHolderFactory;
import ru.tatarchuk.darkweather.ui.base.ViewHolderClickListener;

public class HourlyAdapter extends BaseRecyclerAdapter {

    @Override
    protected BaseViewHolderFactory getFactory() {
        return new VHFactory();
    }

    private class VHFactory extends BaseViewHolderFactory {

        @Override
        protected BaseViewHolder createViewHolder(View view, int type, ViewHolderClickListener listener) {
            switch (type) {
                case R.layout.view_current_detail:
                    return new CurrentDetailViewHolder(view, listener);
                case R.layout.view_hourly:
                    return new HourlyViewHolder(view, listener);
                default:
                    throw exception(type);
            }
        }
    }
}
