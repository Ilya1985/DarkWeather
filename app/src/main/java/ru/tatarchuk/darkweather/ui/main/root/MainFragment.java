package ru.tatarchuk.darkweather.ui.main.root;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import ru.tatarchuk.darkweather.R;
import ru.tatarchuk.darkweather.ui.main.MainActivity;
import ru.tatarchuk.darkweather.ui.main.root.recycler.MainAdapter;
import ru.tatarchuk.darkweather.utils.ISharePref;

public class MainFragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = "Weather " + MainFragment.class.getSimpleName();

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private MainViewModel mViewModel;

    private RecyclerView mRecyclerView;
    private MainAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        PreferenceManager.getDefaultSharedPreferences(requireActivity()).registerOnSharedPreferenceChangeListener(this);
        mViewModel.getItems().observe(getViewLifecycleOwner(), baseItems -> mAdapter.setItems(baseItems));
        mViewModel.getLoadState().observe(getViewLifecycleOwner(), state -> mSwipeRefreshLayout.setRefreshing(state));
        mViewModel.getAddress().observe(getViewLifecycleOwner(), address -> ((MainActivity)requireActivity()).setAddress(address));
        mViewModel.getDbData(ISharePref.getLocationId());
        mViewModel.refreshAll();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter = new MainAdapter();
        mRecyclerView = view.findViewById(R.id.main_recycler);
        mSwipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        mSwipeRefreshLayout.setOnRefreshListener(() -> mViewModel.refresh());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

    }

    @Override
    public void onStop() {
        super.onStop();
        PreferenceManager.getDefaultSharedPreferences(requireActivity()).unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
            mViewModel.preferenceChanged(s);
    }
}
