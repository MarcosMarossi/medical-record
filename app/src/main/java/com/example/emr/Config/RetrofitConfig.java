package com.example.emr.Config;


import com.example.emr.Services.DataService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class RetrofitConfig {

    private static String stringURL = "https://ey7li2szf0.execute-api.us-east-1.amazonaws.com/dev/";
    private static DataService service;
    private static Retrofit retrofit;
    public static Retrofit retrofitConfig(){


        retrofit = new Retrofit.Builder()
                .baseUrl(stringURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
