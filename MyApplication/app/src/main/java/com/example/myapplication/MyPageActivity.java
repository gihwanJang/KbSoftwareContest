package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        HomeActivity HA = (HomeActivity) HomeActivity._HomeActivity;
        Button logoutBtn = findViewById(R.id.logout_btn);
        Button userTypeSettingBtn = findViewById(R.id.my_page_user_type_setting_btn);
        TextView userNameText = findViewById(R.id.my_page_user_name_text);
        TextView userIDText = findViewById(R.id.my_page_user_id_text);
        TextView userValidation = findViewById(R.id.my_page_user_validation);
        TextView userAddress = findViewById(R.id.my_page_user_address);


        userNameText.setText(MainActivity.user.getName() + " 님");
        userIDText.setText(MainActivity.user.getId());
        userTypeSetting(userValidation, userAddress, userTypeSettingBtn, MainActivity.user.getType());

        logoutBtn.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);

            MainActivity.user = null;

            SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferences", Activity.MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();

            HA.finish();
            finish();
        });
    }

    private void userTypeSetting(TextView userValidation, TextView userAddress, Button userTypeSettingBtn, int type){
        if(type == 1){
            userValidation.setText("한솥밥 인증 : 미인증");
            userAddress.setVisibility(View.GONE);
            userTypeSettingBtn.setText("알아보기");
        }
        if(type == 4){
            userValidation.setText("한솥밥 인증 : 인증");
            userAddress.setText("인증자 - "+MainActivity.user.getName()+" 님");
            userTypeSettingBtn.setVisibility(View.GONE);
        }
        if(type == 2){
            userValidation.setText("업장명 : " + MainActivity.user.getName());
            userAddress.setText("업장 주소 : " + MainActivity.user.getAddress());
            userTypeSettingBtn.setText("업장 정보 변경");
        }
        if(type == 3){
            userValidation.setText("기관명 : " + MainActivity.user.getName());
            userAddress.setText("기관 주소 : " + MainActivity.user.getAddress());
            userTypeSettingBtn.setText("기관 정보 변경");
        }
    }
}