package ru.tatarchuk.darkweather.ui.main.root.recycler.hourly;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import butterknife.BindView;
import ru.tatarchuk.darkweather.R;
import ru.tatarchuk.darkweather.ui.base.BaseViewHolder;
import ru.tatarchuk.darkweather.ui.base.ViewHolderClickListener;

public class CurrentDetailViewHolder extends BaseViewHolder<CurrentDetailItem> {

    @BindView(R.id.wind_speed)
    TextView mWindSpeed;
    @BindView(R.id.wind_speed_units)
    TextView mWindSpeedUnit;
    @BindView(R.id.wind_direction)
    TextView mWindDir;

    @BindView(R.id.pressure)
    TextView mPressure;
    @BindView(R.id.pressure_unit)
    TextView mPressureUnit;

    @BindView(R.id.humidity)
    TextView mHumidity;

    public CurrentDetailViewHolder(@NonNull View itemView, ViewHolderClickListener listener) {
        super(itemView, listener);
    }

    @Override
    protected void bind(CurrentDetailItem model) {
        mPressure.setText(model.getPressure());
        mPressureUnit.setText(itemView.getResources().getString(model.getPressureUnit()));
        mHumidity.setText(model.getHumidity());
        mWindSpeed.setText(model.getWindSpeed().equals("0") ?
                itemView.getContext().getResources().getString(R.string.calm) :
                model.getWindSpeed());
        mWindDir.setText(model.getWindBearing() == -1 ? "" :
                itemView.getResources().getString(model.getWindBearing()));
        mWindSpeedUnit.setText(itemView.getResources().getString(model.getSpeedUnit()));
    }
}
