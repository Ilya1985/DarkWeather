package ru.tatarchuk.darkweather.ui.main.root.recycler.hourly;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import butterknife.BindView;
import ru.tatarchuk.darkweather.R;
import ru.tatarchuk.darkweather.ui.base.BaseViewHolder;
import ru.tatarchuk.darkweather.ui.base.ViewHolderClickListener;
import ru.tatarchuk.darkweather.utils.DrawableUtils;

public class HourlyViewHolder extends BaseViewHolder<HourlyItem> {

    @BindView(R.id.time)
    TextView mTime;
    @BindView(R.id.icon)
    ImageView mIcon;
    @BindView(R.id.temperature)
    TextView mTemp;

    public HourlyViewHolder(@NonNull View itemView, ViewHolderClickListener listener) {
        super(itemView, listener);
    }

    @Override
    protected void bind(HourlyItem model) {
        mTime.setText(model.getTime());
        mIcon.setImageDrawable(model.getIcon());
        DrawableUtils.animate(mIcon);
        mTemp.setText(model.getTemp());
    }
}
