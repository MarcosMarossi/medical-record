package com.example.emr.Services;

import com.example.emr.Models.Heartbeat;
import com.example.emr.Models.Scheduling;
import com.example.emr.Models.Test;
import com.example.emr.Models.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DataService {

    // @Headers({"Content-Type = application/json","x-access-token = fooBar123" })
    @POST("auth/jwt/login")
    Call<User> acessApp(@Body User token);


    @GET("auth/jwt/me")
    Call<User> getToken(@Header("x-access-token") String Token);

    @POST("auth/jwt/register")
    Call<User> registerNewUser(@Body User register);


    @GET("api/doctors")
    Call<List<Scheduling>> pegarId();

    @POST("api/schedulling")
    Call<Scheduling> newSchedule(@Body Scheduling scheduling);

    @GET("api/screcord/schedullingsByDates/{month}/{year}")
    Call<Test> historicPatient(@Path( "month" ) String month, @Path( "year" ) String year);

    @DELETE("api/schedulling/{id}")
    Call<Test> deleteSchedule(@Path("id") String id);

    @GET("api/patients")
    Call<ArrayList<User>> getAllPatients();

    @GET("{mac}")
    Call<Heartbeat> getBPM(@Path("mac") String mac);

    @POST("misc/heartbeat")
    Call<User> setBPMUser(@Body User user);
}
