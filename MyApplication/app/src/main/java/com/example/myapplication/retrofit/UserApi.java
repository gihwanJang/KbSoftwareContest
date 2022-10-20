package com.example.myapplication.retrofit;

import com.example.myapplication.modle.User;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

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
import retrofit2.http.Query;

public interface UserApi {
    @POST("/user/sign-up")
    Call<User> userSignUp(@Body User user);

    @GET("/users/{userId}")
    Call<User> findUser(@Path("userId") String userId);

    @POST("/user/changeType")
    Call<Boolean> userChangeType(@Query("userId") String userId, @Query("type") int type);
}
