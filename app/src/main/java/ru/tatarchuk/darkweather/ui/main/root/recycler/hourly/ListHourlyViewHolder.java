package ru.tatarchuk.darkweather.ui.main.root.recycler.hourly;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import ru.tatarchuk.darkweather.R;
import ru.tatarchuk.darkweather.ui.base.BaseViewHolder;
import ru.tatarchuk.darkweather.ui.base.ViewHolderClickListener;

public class ListHourlyViewHolder extends BaseViewHolder<ListHourlyItem> {

    @BindView(R.id.hourly_recycler)
    RecyclerView mRecyclerView;

    private HourlyAdapter mAdapter;

    public ListHourlyViewHolder(@NonNull View itemView, ViewHolderClickListener listener) {
        super(itemView, listener);
        mAdapter = new HourlyAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void bind(ListHourlyItem model) {
        mAdapter.setItems(model.getItems());
    }
}
