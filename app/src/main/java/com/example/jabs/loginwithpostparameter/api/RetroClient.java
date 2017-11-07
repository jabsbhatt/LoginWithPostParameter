package com.example.jabs.loginwithpostparameter.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetroClient {


    private static String BASE_URL = "http://www.imedicoz.com/";

    static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(RetroClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
