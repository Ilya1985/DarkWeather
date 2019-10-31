package ru.tatarchuk.darkweather.ui.base;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;

import java.util.Locale;

import ru.tatarchuk.darkweather.exceptions.TypeNotSupportedException;

public abstract class BaseViewHolderFactory {

    public BaseViewHolder getViewHolder(ViewGroup parent, @LayoutRes int type, ViewHolderClickListener listener) {
        View view = LayoutInflater.from(parent.getContext()).inflate(type, parent, false);
        return createViewHolder(view, type, listener);
    }

    protected abstract BaseViewHolder createViewHolder(View view, @LayoutRes int type, ViewHolderClickListener listener);

    protected TypeNotSupportedException exception(int type) {
        return TypeNotSupportedException.create(String.format(Locale.getDefault(), "LayoutType: %d", type));
    }
}
