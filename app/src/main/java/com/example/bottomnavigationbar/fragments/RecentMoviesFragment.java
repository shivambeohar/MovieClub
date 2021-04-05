package com.example.bottomnavigationbar.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.example.bottomnavigationbar.API.MovieService;
import com.example.bottomnavigationbar.API.RetrofitInstance;
import com.example.bottomnavigationbar.Constants.Constants;
import com.example.bottomnavigationbar.R;
import com.example.bottomnavigationbar.adapter.RecentMoviesAdapter;
import com.example.bottomnavigationbar.models.Movie;
import com.example.bottomnavigationbar.models.MovieResponse;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecentMoviesFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ArrayList<Movie> mMovies = new ArrayList<>();
    private RecentMoviesAdapter mRecentMovieAdapter;
    private int mPage = 1;
    private boolean mIsScrolling = false;
    private int mVisibleItems;
    private int mTotalItems;
    private int mScrolledOutItems;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recent_movies_fragment_layout, container, false);

        mRecyclerView = view.findViewById(R.id.rcv_recent_movies);

        fetchRecentMovies(mPage);

        mRecentMovieAdapter = new RecentMoviesAdapter(mMovies, getContext());
        mRecyclerView.setAdapter(mRecentMovieAdapter);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    mIsScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                mVisibleItems = linearLayoutManager.getChildCount();
                mTotalItems = linearLayoutManager.getItemCount();
                mScrolledOutItems = linearLayoutManager.findFirstVisibleItemPosition();

                if(mIsScrolling && (mTotalItems == mVisibleItems+mScrolledOutItems)){
                    mIsScrolling = false;
                    fetchRecentMovies(++mPage);
                }

            }
        });

        return view;
    }

    private void fetchRecentMovies(int page) {
        MovieService movieService = RetrofitInstance.getInstance().create(MovieService.class);

        movieService.getAllPopularMovies(Constants.KEY, page).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@Nullable Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("retrofit", "onResponse: " + response.body().getMovies().size());
                    mMovies = response.body().getMovies();
                    mRecentMovieAdapter.update(mMovies);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }


}
