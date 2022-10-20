package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.modle.Store;
import com.example.myapplication.modle.User;
import com.example.myapplication.modle.Welfare;
import com.example.myapplication.retrofit.RetrofitService;
import com.example.myapplication.retrofit.StoreApi;
import com.example.myapplication.retrofit.UserApi;
import com.example.myapplication.retrofit.WelfareApi;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static User user = null;
    public static Welfare welfare = null;
    public static Store store = null;
    public static double latitude, longitude;
    RetrofitService retrofitService = new RetrofitService();
    UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);
    WelfareApi welfareApi = retrofitService.getRetrofit().create(WelfareApi.class);
    StoreApi storeApi = retrofitService.getRetrofit().create(StoreApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferences", Activity.MODE_PRIVATE);

        String loginId = sharedPreferences.getString("inputId", null);
        String loginPwd = sharedPreferences.getString("inputPwd", null);

        Intent intent = getIntent();
        latitude = intent.getDoubleExtra("latitude", 0);
        longitude = intent.getDoubleExtra("longitude", 0);

        userApi.findUser(loginId)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User tmp = response.body();
                        if(tmp.getPw().equals(loginPwd)){
                            user = tmp;
                            if(user.getType() == 2) setStore(user.getId());
                            if(user.getType() == 3) setWelfare(user.getId());
                            Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(i);
                            finish();
                        }
                        else{
                            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(i);
                            finish();
                        }
                    }
                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(i);
                        finish();
                    }
                });
    }
    public void setWelfare(String id){
        welfareApi.findWelfare(id).enqueue(new Callback<Welfare>() {
            @Override
            public void onResponse(Call<Welfare> call, Response<Welfare> response) {
                welfare = response.body();
            }
            @Override
            public void onFailure(Call<Welfare> call, Throwable t) {}
        });
    }

    public void setStore(String id){
        storeApi.findStore(id).enqueue(new Callback<Store>() {
            @Override
            public void onResponse(Call<Store> call, Response<Store> response) {
                store = response.body();
            }
            @Override
            public void onFailure(Call<Store> call, Throwable t) {}
        });
    }
}