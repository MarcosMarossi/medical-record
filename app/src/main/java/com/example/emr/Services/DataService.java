package com.example.emr.Services;

import com.example.emr.Models.Heartbeat;
import com.example.emr.Models.Scheduling;
import com.example.emr.Models.User;
import com.example.emr.Models.UserLog;
import com.example.emr.Models.UserLogAux;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface DataService {

    // @Headers({"Content-Type = application/json","x-access-token = fooBar123" })
    @POST("auth/jwt/login")
    Call<User> acessarLogin(@Body User token);


    @GET("auth/jwt/me")
    Call<User> pegarToken(@Header("x-access-token") String Token);

    @POST("auth/jwt/register")
    Call<User> registrarUsuario(@Body User register);


    @GET("api/doctors")
    Call<List<Scheduling>> pegarId();

    @POST("api/schedulling")
    Call<Scheduling> agendar(@Body Scheduling scheduling);

    @GET("api/screcord/schedullingsByDate/{date}")
    Call<ArrayList<Scheduling>> historico(@Path( "date" ) String date);

    @GET("api/patients")
    Call<ArrayList<User>> getAllPatients();

    @GET("{mac}")
    Call<Heartbeat> getBPM(@Path("mac") String mac);

    @POST("misc/heartbeat")
    Call<User> setBPMUser(@Body User user);

    @GET("api/heartbeatlog/{id}")
    Call<ArrayList<User>> getLog(@Path("id") String id);

}
