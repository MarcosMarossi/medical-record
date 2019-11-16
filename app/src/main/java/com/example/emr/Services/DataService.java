package com.example.emr.Services;

import com.example.emr.Models.Sheduling;
import com.example.emr.Models.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Header;

public interface DataService {

    // @Headers({"Content-Type = application/json","x-access-token = fooBar123" })
    @POST("auth/jwt/login")
    Call<User> acessarLogin(@Body User token);


    @GET("auth/jwt/me")
    Call<User> pegarToken(@Header("x-access-token") String Token);

    @POST("auth/jwt/register")
    Call<User> registrarUsuario(@Body User register);


    @GET("api/doctors")
    Call<List<Sheduling>> pegarId();
}
