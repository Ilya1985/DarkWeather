package ru.tatarchuk.darkweather.db.model;

import ru.tatarchuk.darkweather.ui.base.BaseItem;

public interface BaseElement<I extends  BaseItem> {

    public I toItem();
}
