package ru.tatarchuk.darkweather.ui.main.navigation.recycler;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import butterknife.BindView;
import ru.tatarchuk.darkweather.R;
import ru.tatarchuk.darkweather.ui.base.BaseViewHolder;
import ru.tatarchuk.darkweather.ui.base.ViewHolderClickListener;
import ru.tatarchuk.darkweather.utils.DrawableUtils;

import static ru.tatarchuk.darkweather.ui.base.ViewHolderClickListener.*;

public class LocationViewHolder extends BaseViewHolder<LocationItem> implements View.OnClickListener {

    @BindView(R.id.first_name)
    TextView mFirstName;
    @BindView(R.id.temperature)
    TextView mTemp;
    @BindView(R.id.icon)
    ImageView mIcon;

    private Boolean isAcross;
    private AnimatedVectorDrawableCompat mAvdc;

    public LocationViewHolder(@NonNull View itemView, ViewHolderClickListener listener) {
        super(itemView, listener);
        itemView.setOnClickListener(this);
        mAvdc = AnimatedVectorDrawableCompat.create(itemView.getContext(), R.drawable.ic_delete_24dp);
    }

    @Override
    protected void bind(LocationItem model) {
        mFirstName.setText(model.getFirstName());
        mFirstName.setTextColor(itemView.getResources().getColor(model.getColor()));
        mTemp.setText(model.getTem());
        mIcon.setImageDrawable(model.getIcon());
        DrawableUtils.animate(mIcon);
        isAcross = false;
        mAvdc.stop();
    }

    public Boolean isAcross() {
        return isAcross;
    }

    public void setAcross(Boolean across) {
        isAcross = across;
    }

    public AnimatedVectorDrawableCompat getAvdc() {
        return mAvdc;
    }

    @Override
    public void onClick(View view) {
        if (mViewHolderClickListener != null) {
            mViewHolderClickListener.onViewHolderClick(itemView, Action.CLICK, getAdapterPosition());
        }
    }
}
