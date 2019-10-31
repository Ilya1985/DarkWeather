package ru.tatarchuk.darkweather.ui.base;

import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public abstract class BaseActivity extends AppCompatActivity {

    @LayoutRes
    public abstract int getLayoutResId();

    @IdRes
    public abstract int getContainerId();

    public abstract Fragment getFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(getContainerId());
        if (fragment == null) {
            fragment = getFragment();
            manager.beginTransaction()
                    .add(getContainerId(), fragment)
                    .commit();
        }
    }
}
