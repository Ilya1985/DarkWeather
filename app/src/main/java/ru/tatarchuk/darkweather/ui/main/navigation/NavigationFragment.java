package ru.tatarchuk.darkweather.ui.main.navigation;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.disposables.Disposable;
import ru.tatarchuk.darkweather.R;
import ru.tatarchuk.darkweather.WeatherApp;
import ru.tatarchuk.darkweather.ui.base.ViewHolderClickListener;
import ru.tatarchuk.darkweather.ui.main.navigation.recycler.CurrLocationViewHolder;
import ru.tatarchuk.darkweather.ui.main.navigation.recycler.ItemTouchHelperCallback;
import ru.tatarchuk.darkweather.ui.main.navigation.recycler.LocationAdapter;
import ru.tatarchuk.darkweather.ui.main.navigation.recycler.LocationItem;
import ru.tatarchuk.darkweather.ui.main.navigation.recycler.LocationViewHolder;
import ru.tatarchuk.darkweather.ui.main.navigation.recycler.SearchAdapter;
import ru.tatarchuk.darkweather.ui.main.navigation.recycler.SearchViewHolder;

public class NavigationFragment extends Fragment implements ViewHolderClickListener, SharedPreferences.OnSharedPreferenceChangeListener {

    private final static String TAG = "Weather " + NavigationFragment.class.getSimpleName();

    private static final String ARG_IS_SEARCH = "is_search";

    public static NavigationFragment newInstance() {
        return new NavigationFragment();
    }

    // Активен ли поиск
    private boolean isSearch;

    private NavigationViewModel mViewModel;
    private Disposable mSearchDisposable;

    private RecyclerView mRecyclerView;
    private SearchAdapter mSearchAdapter;
    private LocationAdapter mLocationAdapter;

    private SearchView mSearchView;
    private ProgressBar mProgressBar;
    private ImageView mCloseDrawer;

    ItemTouchHelperCallback mTouchHelperCallback;
    ItemTouchHelper mItemTouchHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            isSearch = savedInstanceState.getBoolean(ARG_IS_SEARCH);
        } else {
            isSearch = false;
        }
        mSearchAdapter = new SearchAdapter();
        mSearchAdapter.setViewHolderClickListener(this);
        mLocationAdapter = new LocationAdapter();
        mLocationAdapter.setViewHolderClickListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_navigation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = view.findViewById(R.id.navigation_recycler);
        mRecyclerView.setAdapter(isSearch ? mSearchAdapter : mLocationAdapter);

        mTouchHelperCallback = new ItemTouchHelperCallback(mLocationAdapter);
        mItemTouchHelper = new ItemTouchHelper(mTouchHelperCallback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);

        mProgressBar = view.findViewById(R.id.navigation_progress);
        mCloseDrawer = view.findViewById(R.id.close_drawer);
        mSearchView = view.findViewById(R.id.search);
        mSearchView.setOnCloseListener(() -> setSearchVisible(false));
        mSearchView.setOnSearchClickListener(v -> setSearchVisible(true));

        mCloseDrawer = view.findViewById(R.id.close_drawer);
        mCloseDrawer.setOnClickListener(v -> requireActivity().onBackPressed());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(NavigationViewModel.class);

    }

    @Override
    public void onStart() {
        super.onStart();
        PreferenceManager.getDefaultSharedPreferences(getContext()).registerOnSharedPreferenceChangeListener(this);
        mViewModel.getSearchResult().observe(getViewLifecycleOwner(), baseItems -> {
            mSearchAdapter.setItems(baseItems);
        });
        mViewModel.getLoadState().observe(getViewLifecycleOwner(), aBoolean -> mProgressBar.setVisibility(aBoolean ? View.VISIBLE : View.GONE));
        mViewModel.getLocations().observe(getViewLifecycleOwner(), locationItems -> mLocationAdapter.setItems(locationItems));
        mSearchDisposable = RxSearchView.queryTextChanges(mSearchView)
                .skipInitialValue()
                .doOnNext(charSequence -> mViewModel.disposeSearch())
                .doOnNext(charSequence -> mSearchAdapter.clear())
                .debounce(300, TimeUnit.MILLISECONDS)
                .filter(charSequence -> charSequence.length() > 1)
                .map(CharSequence::toString)
                .subscribe(s -> mViewModel.search(s), e -> {
                    Log.i(TAG, "search failed", e);
                    Toast.makeText(WeatherApp.getInstance(), "Search Failed", Toast.LENGTH_SHORT).show();
                });
    }

    @Override
    public void onStop() {
        super.onStop();
        PreferenceManager.getDefaultSharedPreferences(getContext()).unregisterOnSharedPreferenceChangeListener(this);
        mSearchDisposable.dispose();
    }

    private boolean setSearchVisible(boolean visible) {
        if (visible) {
            mRecyclerView.setAdapter(mSearchAdapter);
        } else {
            mRecyclerView.setAdapter(mLocationAdapter);
            mViewModel.clearSearchResult();
        }
        isSearch = visible;
        return visible;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(ARG_IS_SEARCH, isSearch);
    }


    public boolean hideSearch() {
        if (!mSearchView.isIconified()) {
            mSearchView.setQuery("", false);
            mSearchView.setIconified(true);
            return true;
        }
        return false;
    }

    @Override
    public void onViewHolderClick(View view, Action action, int position) {
        Log.i(TAG, "onClick " + view.getTag());
        if (view.getTag().equals(SearchViewHolder.class.getSimpleName()) && action == Action.CLICK) {
            mViewModel.addLocation(position);
            setSearchVisible(false);
        } else if (view.getTag().equals(LocationViewHolder.class.getSimpleName()) ||
                view.getTag().equals(CurrLocationViewHolder.class.getSimpleName())) {
            if (action == Action.SWIPE_RIGHT)
                mViewModel.deleteLocation(position);
            else if (action == Action.CLICK)
                mViewModel.setLocation(position);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        mViewModel.preferenceChanged(s);
    }
}
