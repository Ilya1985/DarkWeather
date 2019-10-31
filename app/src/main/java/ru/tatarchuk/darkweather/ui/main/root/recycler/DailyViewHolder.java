package ru.tatarchuk.darkweather.ui.main.root.recycler;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import butterknife.BindView;
import ru.tatarchuk.darkweather.R;
import ru.tatarchuk.darkweather.ui.base.BaseViewHolder;
import ru.tatarchuk.darkweather.ui.base.ViewHolderClickListener;
import ru.tatarchuk.darkweather.ui.daily.DailyActivity;
import ru.tatarchuk.darkweather.ui.main.MainActivity;
import ru.tatarchuk.darkweather.utils.DrawableUtils;

public class DailyViewHolder extends BaseViewHolder<DailyItem> implements View.OnClickListener {

    @BindView(R.id.day_of_week)
    TextView mDayOfWeek;
    @BindView(R.id.date)
    TextView mDate;
    @BindView(R.id.icon)
    ImageView mIcon;
    @BindView(R.id.temp_max)
    TextView mTempMax;
    @BindView(R.id.temp_min)
    TextView mTempMin;

    DailyViewHolder(@NonNull View itemView, ViewHolderClickListener listener) {
        super(itemView, listener);
        itemView.setOnClickListener(this);
    }

    @Override
    protected void bind(DailyItem model) {
        mDayOfWeek.setText(model.getDayOfWeek());
        mDate.setText(model.getDate());
        mIcon.setImageDrawable(model.getIcon());
        DrawableUtils.animate(mIcon);
        mTempMax.setText(model.getTempMax());
        mTempMin.setText(model.getTempMin());
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Comming Soon!", Toast.LENGTH_SHORT).show();
        //itemView.getContext().startActivity(new Intent(view.getContext(), DailyActivity.class));
    }
}
