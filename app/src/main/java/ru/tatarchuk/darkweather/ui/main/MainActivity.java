package ru.tatarchuk.darkweather.ui.main;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import ru.tatarchuk.darkweather.R;
import ru.tatarchuk.darkweather.db.model.LocAddress;
import ru.tatarchuk.darkweather.service.LocationService;
import ru.tatarchuk.darkweather.ui.base.BaseActivity;
import ru.tatarchuk.darkweather.ui.base.LocationActivity;
import ru.tatarchuk.darkweather.ui.main.navigation.NavigationFragment;
import ru.tatarchuk.darkweather.ui.main.root.MainFragment;

import static ru.tatarchuk.darkweather.utils.ISharePref.LOCATION_KEY;

public class MainActivity extends LocationActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    //Toolbar
    private DrawerLayout mDrawerLayout;
    private ImageView mSettings;
    private TextView mFirstName;
    private TextView mSecondName;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public int getContainerId() {
        return R.id.fragment_container;
    }

    @Override
    public Fragment getFragment() {
        return MainFragment.newInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addNavigationFragment();
        addNavigationFragment();

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                NavigationFragment fragment = (NavigationFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_fragment_container);
                if (fragment != null) fragment.hideSearch();
            }
        });
        mFirstName = findViewById(R.id.first_name);
        mSecondName = findViewById(R.id.second_name);

        findViewById(R.id.menu).setOnClickListener(v -> mDrawerLayout.openDrawer(GravityCompat.START));
        findViewById(R.id.settings).setOnClickListener(v -> Toast.makeText(MainActivity.this, "Comming Soon!", Toast.LENGTH_SHORT).show());
    }

    @Override
    protected void onStart() {
        super.onStart();
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }

   // private View.OnClickListener mStartSettings = view -> ;

    private void addNavigationFragment() {
        //NavigationView на всю ширину
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        findViewById(R.id.navigation_view).getLayoutParams().width = displaymetrics.widthPixels;

        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.navigation_fragment_container);
        if (fragment == null) {
            fragment = NavigationFragment.newInstance();
            manager.beginTransaction()
                    .add(R.id.navigation_fragment_container, fragment)
                    .commit();
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if (s.equals(LOCATION_KEY)) {
            if (mDrawerLayout.isDrawerOpen(GravityCompat.START))
                new Handler().postDelayed(() -> mDrawerLayout.closeDrawer(GravityCompat.START), 200);
        }
    }

    public void setAddress(LocAddress address) {
        mFirstName.setText(address.getFirst());
        mSecondName.setText(address.getSecond());
    }

    @Override
    public void onBackPressed() {
        NavigationFragment fragment = (NavigationFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_fragment_container);
        if (fragment != null && fragment.hideSearch())
            return;
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START, true);
            return;
        }
        super.onBackPressed();
    }
}
