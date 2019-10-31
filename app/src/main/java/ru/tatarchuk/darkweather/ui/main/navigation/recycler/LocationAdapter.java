package ru.tatarchuk.darkweather.ui.main.navigation.recycler;

import android.view.View;

import ru.tatarchuk.darkweather.R;
import ru.tatarchuk.darkweather.ui.base.BaseRecyclerAdapter;
import ru.tatarchuk.darkweather.ui.base.BaseViewHolder;
import ru.tatarchuk.darkweather.ui.base.BaseViewHolderFactory;
import ru.tatarchuk.darkweather.ui.base.ViewHolderClickListener;

import static ru.tatarchuk.darkweather.ui.base.ViewHolderClickListener.*;

public class LocationAdapter extends BaseRecyclerAdapter implements ItemTouchHelperAdapter {

    @Override
    protected BaseViewHolderFactory getFactory() {
        return new VHFactory();
    }

    @Override
    public void onItemDismiss(View view, int position) {
        if (mViewHolderClickListener != null) {
            mViewHolderClickListener.onViewHolderClick(view, Action.SWIPE_RIGHT, position);
        }
    }

    private class VHFactory extends BaseViewHolderFactory {

        @Override
        protected BaseViewHolder createViewHolder(View view, int type, ViewHolderClickListener listener) {
            switch (type) {
                case R.layout.view_current_location:
                    return new CurrLocationViewHolder(view, listener);
                case R.layout.view_location:
                    return new LocationViewHolder(view, listener);
                default:
                    throw exception(type);
            }
        }
    }
}
