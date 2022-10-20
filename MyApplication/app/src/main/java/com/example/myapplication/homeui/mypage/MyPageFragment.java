package com.example.myapplication.homeui.mypage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.ChangeTypeActivity;
import com.example.myapplication.HomeActivity;
import com.example.myapplication.MainActivity;
import com.example.myapplication.MyPageActivity;
import com.example.myapplication.R;

public class MyPageFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_page, container, false);
        TextView userNameText = view.findViewById(R.id.user_name_text);
        Button userTypeText = view.findViewById(R.id.user_type_text);
        TextView usingPointBtn = view.findViewById(R.id.using_point_btn);
        TextView usingPointText = view.findViewById(R.id.using_point_text);
        Button myPageBtn = view.findViewById(R.id.my_page_button);

        userNameText.setText(MainActivity.user.getName() + " 님");
        getUserType(userTypeText, MainActivity.user.getType());
        getPointText(usingPointText, MainActivity.user.getType());
        getPointType(usingPointBtn, MainActivity.user.getType());

        myPageBtn.setOnClickListener(view1 -> {
            Intent i = new Intent(getActivity(), MyPageActivity.class);
            startActivity(i);
        });

        userTypeText.setOnClickListener(view1 -> {
            Intent i = new Intent(getActivity(), ChangeTypeActivity.class);
            startActivity(i);
        });

        return view;
    }
    // 계정정보 text 일치화
    private void getUserType(Button userTypeText, int type){
        if(type == 4)
            userTypeText.setText("일반계정");
        if(type == 2)
            userTypeText.setText("점주계정");
        if(type == 3)
            userTypeText.setText("복지기관");
        if(type == 1){
            userTypeText.setText("기관/업장 등록");
            userTypeText.setTextColor(Color.parseColor("#FFC000"));
            userTypeText.setTextSize(15);
        }
   }
   // 포인트 버튼 활성화 여부
   private void getPointType(TextView usingPointBtn, int type){
        if(type == 1)
            usingPointBtn.setText("알아 보기");
        if(type == 2 || type == 3)
            usingPointBtn.setText("정산하기");
        if(type == 4)
            usingPointBtn.setText("사용하기");
   }
   // 포인트 표기
   private void getPointText(TextView usingPointText, int type){
        if(type == 4 || type == 2 || type ==3)
            usingPointText.setText(String.valueOf(MainActivity.user.getPoint() + " Point"));
        else{
            usingPointText.setText("복지기관 인증이 필요합니다.");
            usingPointText.setTextSize(20);
        }
   }
}
