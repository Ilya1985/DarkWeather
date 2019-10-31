package ru.tatarchuk.darkweather.ui.main.navigation.recycler;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import butterknife.BindView;
import ru.tatarchuk.darkweather.R;
import ru.tatarchuk.darkweather.ui.base.BaseViewHolder;
import ru.tatarchuk.darkweather.ui.base.ViewHolderClickListener;
import ru.tatarchuk.darkweather.utils.DrawableUtils;

import static ru.tatarchuk.darkweather.ui.base.ViewHolderClickListener.*;

public class CurrLocationViewHolder extends BaseViewHolder<LocationItem> implements View.OnClickListener {

    @BindView(R.id.first_name)
    TextView mFirstName;
    @BindView(R.id.second_name)
    TextView mSecondName;
    @BindView(R.id.temperature)
    TextView mTemp;
    @BindView(R.id.icon)
    ImageView mIcon;

    public CurrLocationViewHolder(@NonNull View itemView, ViewHolderClickListener listener) {
        super(itemView, listener);
        itemView.setOnClickListener(this);
    }

    @Override
    protected void bind(LocationItem model) {
        mFirstName.setText(model.getFirstName());
        mFirstName.setTextColor(itemView.getResources().getColor(model.getColor()));
        mSecondName.setText(model.getSecondName());
        mTemp.setText(model.getTem());
        mIcon.setImageDrawable(model.getIcon());
        DrawableUtils.animate(mIcon);
    }

    @Override
    public void onClick(View view) {
        if (mViewHolderClickListener != null) {
            mViewHolderClickListener.onViewHolderClick(itemView, Action.CLICK, getAdapterPosition());
        }
    }
}
