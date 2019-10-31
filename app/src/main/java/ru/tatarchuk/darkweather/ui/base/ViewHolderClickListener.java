package ru.tatarchuk.darkweather.ui.base;


import android.view.View;

public interface ViewHolderClickListener {

    public enum Action {
        CLICK, LONG_CLICK, SWIPE_LEFT, SWIPE_RIGHT
    }

    public void onViewHolderClick(View view, Action action, int position);
}
