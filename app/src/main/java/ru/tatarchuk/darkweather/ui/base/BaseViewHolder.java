package ru.tatarchuk.darkweather.ui.base;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.ButterKnife;

public abstract class BaseViewHolder<M extends BaseItem> extends RecyclerView.ViewHolder {

    @Nullable
    protected ViewHolderClickListener mViewHolderClickListener;

    public BaseViewHolder(@NonNull View itemView, @Nullable ViewHolderClickListener listener) {
        super(itemView);
        itemView.setTag(getClass().getSimpleName());
        ButterKnife.bind(this, itemView);
        mViewHolderClickListener = listener;
    }

    abstract protected void bind(M model);
}
