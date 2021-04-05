package com.example.bottomnavigationbar.API;

import com.example.bottomnavigationbar.Constants.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit mRetrofitInstance;

    public static synchronized Retrofit getInstance() {
        if (mRetrofitInstance == null) {
            mRetrofitInstance = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofitInstance;
    }

}
