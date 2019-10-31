package ru.tatarchuk.darkweather.ui.main.root.recycler;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import butterknife.BindView;
import ru.tatarchuk.darkweather.R;
import ru.tatarchuk.darkweather.ui.base.BaseViewHolder;
import ru.tatarchuk.darkweather.ui.base.ViewHolderClickListener;
import ru.tatarchuk.darkweather.utils.DrawableUtils;

public class CurrentlyViewHolder extends BaseViewHolder<CurrentlyItem> {

    @BindView(R.id.icon)
    ImageView mIcon;
    @BindView(R.id.temperature)
    TextView mTemp;
    @BindView(R.id.feels_like_temp)
    TextView mFlTEmp;
    @BindView(R.id.description)
    TextView mDescription;

    CurrentlyViewHolder(@NonNull View itemView, ViewHolderClickListener listener) {
        super(itemView, listener);
    }

    @Override
    protected void bind(CurrentlyItem model) {
        mIcon.setImageDrawable(model.getIcon());
        DrawableUtils.animate(mIcon);
        mTemp.setText(model.getTemp());
        mFlTEmp.setText(model.getFlTemp());
        mDescription.setText(model.getSummary());
    }
}
