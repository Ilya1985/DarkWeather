package ru.tatarchuk.darkweather.ui.main.navigation.recycler;

import android.util.Log;
import android.view.View;

import ru.tatarchuk.darkweather.R;
import ru.tatarchuk.darkweather.ui.base.BaseRecyclerAdapter;
import ru.tatarchuk.darkweather.ui.base.BaseViewHolder;
import ru.tatarchuk.darkweather.ui.base.BaseViewHolderFactory;
import ru.tatarchuk.darkweather.ui.base.ViewHolderClickListener;

public class SearchAdapter extends BaseRecyclerAdapter {

    @Override
    protected BaseViewHolderFactory getFactory() {
        return new VHFactory();
    }

    private class VHFactory extends BaseViewHolderFactory {

        @Override
        protected BaseViewHolder createViewHolder(View view, int type, ViewHolderClickListener listener) {
            if (type == R.layout.view_search) {
                return new SearchViewHolder(view, listener);
            }
            throw exception(type);
        }
    }
}
