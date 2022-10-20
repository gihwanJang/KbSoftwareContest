package com.example.myapplication.homeui.notification;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

public class NotificationFragment extends Fragment {
    Button curr;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        if(MainActivity.user.getType() == 1)
            replaceFragment(new NotificationNotValifyFragment());
        else if(MainActivity.user.getType() == 4)
            replaceFragment(new NotificationMapFragment());

        Button mapBtn = view.findViewById(R.id.notification_map_btn);
        Button welfareBtn = view.findViewById(R.id.notification_welfare_btn);
        Button restaurantBtn = view.findViewById(R.id.notification_restaurant_btn);
        curr = mapBtn;

        mapBtn.setOnClickListener(view1 -> {
            curr.setBackgroundResource(R.drawable.sign_up_edit_border);
            curr.setTextColor(Color.parseColor("#000000"));
            curr = mapBtn;
            mapBtn.setBackgroundResource(R.drawable.sign_up_edit_border2);
            mapBtn.setTextColor(Color.parseColor("#FFFFFF"));
            if(MainActivity.user.getType() == 1)
                replaceFragment(new NotificationNotValifyFragment());
            else if(MainActivity.user.getType() == 4)
                replaceFragment(new NotificationMapFragment());
        });
        welfareBtn.setOnClickListener(view1 -> {
            curr.setBackgroundResource(R.drawable.sign_up_edit_border);
            curr.setTextColor(Color.parseColor("#000000"));
            curr = welfareBtn;
            welfareBtn.setBackgroundResource(R.drawable.sign_up_edit_border2);
            welfareBtn.setTextColor(Color.parseColor("#FFFFFF"));
            replaceFragment(new NotificationWelfareFragment());
        });
        restaurantBtn.setOnClickListener(view1 -> {
            curr.setBackgroundResource(R.drawable.sign_up_edit_border);
            curr.setTextColor(Color.parseColor("#000000"));
            curr = restaurantBtn;
            restaurantBtn.setBackgroundResource(R.drawable.sign_up_edit_border2);
            restaurantBtn.setTextColor(Color.parseColor("#FFFFFF"));
            replaceFragment(new NotificationRestaurantFragment());
        });

        return  view;
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.notification_container, fragment);
        fragmentTransaction.commit();
    }
}
