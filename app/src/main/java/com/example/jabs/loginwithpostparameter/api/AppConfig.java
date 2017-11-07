package com.example.jabs.loginwithpostparameter.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Brijesh on 11/3/2017.
 */
public class AppConfig {

    private static String BASE_URL = "http://www.imedicoz.com/";
    public static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
