package com.example.emr.Services;

import com.example.emr.Models.CID;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CIDService {

    @GET("cid/{cid}")
    Call<CID> buscarCid(@Path("cid") String cid);

}
