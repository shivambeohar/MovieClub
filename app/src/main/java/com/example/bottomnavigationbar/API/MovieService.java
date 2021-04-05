package com.example.bottomnavigationbar.API;

import com.example.bottomnavigationbar.models.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {

    @GET("top_rated")
    Call<MovieResponse> getAllMovies(@Query("api_key") String key, @Query("page") int page);

    @GET("popular")
    Call<MovieResponse> getAllPopularMovies(@Query("api_key") String key, @Query("page") int page);

    @GET("upcoming")
    Call<MovieResponse> getAllUpcomingMovies(@Query("api_key") String key, @Query("page") int page);
}
