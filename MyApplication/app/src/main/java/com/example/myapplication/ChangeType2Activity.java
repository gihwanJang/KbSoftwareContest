package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.modle.Store;
import com.example.myapplication.modle.User;
import com.example.myapplication.retrofit.RetrofitService;
import com.example.myapplication.retrofit.StoreApi;
import com.example.myapplication.retrofit.UserApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeType2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_type2);

        EditText inputName = findViewById(R.id.store_name_text_edit);
        EditText inputAddress1 = findViewById(R.id.store_address1_text_edit);
        EditText inputAddress2 = findViewById(R.id.store_address2_text_edit);
        EditText inputTel = findViewById(R.id.store_tel_text_edit);
        EditText inputAccount = findViewById(R.id.store_account_text_edit);
        Button changeTypeSubmitBtn = findViewById(R.id.change_type2_submit_btn);

        RetrofitService retrofitService = new RetrofitService();
        StoreApi storeApi = retrofitService.getRetrofit().create(StoreApi.class);
        UserApi userApi = retrofitService.getRetrofit().create(UserApi.class);

        changeTypeSubmitBtn.setOnClickListener(view -> {
            Store store = new Store();
            store.setAddress2(String.valueOf(inputAddress2.getText()));
            store.setName(String.valueOf(inputName.getText()));
            store.setTel(String.valueOf(inputTel.getText()));
            store.setAddress1(String.valueOf(inputAddress1.getText()));
            store.setDescription("");
            store.setScore(0);
            store.setId(MainActivity.user.getId());
            store.setAccount(String.valueOf(inputAccount.getText()));

            storeApi.registerStore(store).enqueue(new Callback<Store>() {
                @Override
                public void onResponse(Call<Store> call, Response<Store> response) {
                    Toast.makeText(ChangeType2Activity.this, "가게가 등록 되었습니다.", Toast.LENGTH_SHORT).show();
                    userApi.userChangeType(MainActivity.user.getId(), 2).enqueue(new Callback<Boolean>() {
                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            Toast.makeText(ChangeType2Activity.this, "업주 권한으로 변경 되었습니다.", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {
                            Toast.makeText(ChangeType2Activity.this, "권한 변경 오류", Toast.LENGTH_SHORT).show();
                        }
                    });
                    userApi.findUser(MainActivity.user.getId()).enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            MainActivity.user = response.body();
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {

                        }
                    });
                    finish();
                }

                @Override
                public void onFailure(Call<Store> call, Throwable t) {
                    Toast.makeText(ChangeType2Activity.this, "입력 정보가 잘못 되었습니다.", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}