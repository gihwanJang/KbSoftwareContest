package com.example.myapplication.retrofit;

import com.example.myapplication.modle.Store;
import com.example.myapplication.modle.Welfare;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WelfareApi {
    @GET("/welfares/{welfareId}")
    Call<Welfare> findWelfare(@Path("welfareId") String welfareId);

    @POST("/welfares/register")
    Call<Welfare> registerWelfare(@Body Welfare welfare);

    @POST("/welfares")
    Call<List<Welfare>> getAllWelfares();
}
