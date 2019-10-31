package ru.tatarchuk.darkweather.ui.base;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final static String TAG = "Weather " + BaseRecyclerAdapter.class.getSimpleName();

    private BaseViewHolderFactory mFactory;

    @Nullable
    protected ViewHolderClickListener mViewHolderClickListener;

    protected abstract BaseViewHolderFactory getFactory();

    private List<? extends BaseItem> mItems;

    public BaseRecyclerAdapter() {
        mFactory = getFactory();
        mItems = new ArrayList<>();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return mFactory.getViewHolder(parent, viewType, mViewHolderClickListener);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getLayoutResId();
    }

    public void setItems(List<? extends BaseItem> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    public void setViewHolderClickListener(ViewHolderClickListener viewHolderClickListener) {
        mViewHolderClickListener = viewHolderClickListener;
    }

    public void clear(){
        mItems.clear();
        notifyDataSetChanged();
    }

    public void remove(int position){
        mItems.remove(position);
        notifyItemRemoved(position);
    }
}
