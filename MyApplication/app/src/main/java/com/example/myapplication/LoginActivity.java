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

import com.example.myapplication.modle.User;
import com.example.myapplication.retrofit.RetrofitService;
import com.example.myapplication.retrofit.UserApi;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    String id, pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText inputId = findViewById(R.id.idEditText);
        EditText inputPw = findViewById(R.id.pwEditText);

        Button signUpBtn = findViewById(R.id.sign_up_btn);
        Button loginBtn = findViewById(R.id.login_btn);

        RetrofitService retrofitService = new RetrofitService();
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(i);
            }
        });

        loginBtn.setOnClickListener(view -> {

            id = String.valueOf(inputId.getText());
            pw = String.valueOf(inputPw.getText());

            userApi.findUser(id)
                    .enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            MainActivity.user = response.body();
                            if(MainActivity.user != null && MainActivity.user.getPw().equals(pw)){
                                SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferences", Activity.MODE_PRIVATE);

                                SharedPreferences.Editor autoLogin = sharedPreferences.edit();

                                autoLogin.putString("inputId", id);
                                autoLogin.putString("inputPwd", pw);

                                autoLogin.commit();
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);
                                finish();
                            }
                            else
                                Toast.makeText(LoginActivity.this, "아이디/ 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(LoginActivity.this, "아이디/ 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error accurred",t);
                        }
                    });
        });
    }
}