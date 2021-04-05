package com.example.bottomnavigationbar.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieResponse {

    @SerializedName("results")
    private ArrayList<Movie> mMovies;

    public ArrayList<Movie> getMovies() {
        return mMovies;
    }
}
