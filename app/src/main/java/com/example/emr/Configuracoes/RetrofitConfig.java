package com.example.emr.Configuracoes;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {
    private final Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:3000/misc/").
                addConverterFactory(GsonConverterFactory.create()).
                build();
    }
}
