package com.example.myapplication.retrofit;


import com.example.myapplication.modle.Store;
import com.example.myapplication.modle.User;

import java.util.List;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface StoreApi {
    @GET("/stores/{storeId}")
    Call<Store> findStore(@Path("storeId") String storeId);

    @POST("/stores/register")
    Call<Store> registerStore(@Body Store store);

    @POST("/stores")
    Call<List<Store>> getAllStores();
}
