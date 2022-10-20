package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeType3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_type3);

        EditText name = findViewById(R.id.welfare_name_text_edit);
        EditText address1 = findViewById(R.id.welfare_address1_text_edit);
        EditText address2 = findViewById(R.id.welfare_address2_text_edit);
        EditText tel = findViewById(R.id.welfare_tel_text_edit);
        Button changeTypeSubmitBtn = findViewById(R.id.change_type3_submit_btn);

        RetrofitService retrofitService = new RetrofitService();
        WelfareApi welfareApi = retrofitService.getRetrofit().create(WelfareApi.class);
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);

        changeTypeSubmitBtn.setOnClickListener(view -> {
            Welfare welfare = new Welfare();
            welfare.setId(MainActivity.user.getId());
            welfare.setName(String.valueOf(name.getText()));
            welfare.setAddress1(String.valueOf(address1.getText()));
            welfare.setAddress2(String.valueOf(address2.getText()));
            welfare.setTel(String.valueOf(tel.getText()));
            welfare.setDescription("");
            welfare.setScore(0);

            welfareApi.registerWelfare(welfare).enqueue(new Callback<Welfare>() {
                @Override
                public void onResponse(Call<Welfare> call, Response<Welfare> response) {
                    Toast.makeText(ChangeType3Activity.this, "복지관이 등록 되었습니다.", Toast.LENGTH_SHORT).show();
                    userApi.userChangeType(MainActivity.user.getId(), 3).enqueue(new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            Toast.makeText(ChangeType3Activity.this, "업주 권한으로 변경 되었습니다.", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {
                            Toast.makeText(ChangeType3Activity.this, "권한 변경 오류", Toast.LENGTH_SHORT).show();
                        }
                    });
                    userApi.findUser(MainActivity.user.getId()).enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            MainActivity.user = response.body();
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {}
                    });
                    finish();
                }

                @Override
                public void onFailure(Call<Welfare> call, Throwable t) {
                    Toast.makeText(ChangeType3Activity.this, "입력 정보가 잘못 되었습니다.", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}