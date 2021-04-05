package com.example.bottomnavigationbar;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.bottomnavigationbar.fragments.RecentMoviesFragment;
import com.example.bottomnavigationbar.fragments.SearchMoviesFragment;
import com.example.bottomnavigationbar.fragments.TopRatedMoviesFragment;
import com.example.bottomnavigationbar.fragments.UpcomingMoviesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView
        .OnNavigationItemSelectedListener {

    private BottomNavigationView mBottomNavigationView;
    private FrameLayout mFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        loadFragment(new TopRatedMoviesFragment());

        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    /**
     * Initializing Views
     */
    void initViews(){
        mBottomNavigationView = findViewById(R.id.bnb_navigation_bar);
        mFrameLayout = findViewById(R.id.fl_fragment_container);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.itm_top_rated_movies:
                fragment = new TopRatedMoviesFragment();
                Log.d("fragment", "Top Rated Fragment Loaded");
                break;
            case R.id.itm_recent_movies:
                fragment = new RecentMoviesFragment();
                Log.d("fragment", "Recent Fragment Loaded");
                break;
            case R.id.itm_upcoming_movies:
                fragment = new UpcomingMoviesFragment();
                Log.d("fragment", "Upcoming Fragment Loaded");
                break;
            case R.id.itm_search_movies:
                fragment = new SearchMoviesFragment();
                Log.d("fragment", "Search Fragment Loaded");
                break;
        }
        return loadFragment(fragment);
    }

    /**
     * This method will load the fragment when user click on the bottom navigation bar.
     * @param loadFragment fragment object to be displayed on the activity
     * @return true if fragment is not null otherwise false
     */
    public boolean loadFragment(Fragment loadFragment){
        if(loadFragment != null){
            Log.d("fragment", "loadFragment: fragment is not null");
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_fragment_container,loadFragment)
                    .commit();
            Log.d("fragment", "loadFragment: fragment loaded");
            return true;
        }
        return false;
    }
}
