package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.databinding.ActivityHomeBinding;
import com.example.myapplication.homeui.authentication.AuthenticationFragment;
import com.example.myapplication.homeui.donation.DonationFragment;
import com.example.myapplication.homeui.mypage.MyPageFragment;
import com.example.myapplication.homeui.notification.NotificationFragment;
import com.example.myapplication.homeui.point.RestaurantPointFragment;
import com.example.myapplication.homeui.point.UserPointFragment;
import com.example.myapplication.homeui.restaurant.RestaurantFragment;
import com.example.myapplication.homeui.welfare.WelfareFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    public static Activity _HomeActivity;
    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        _HomeActivity = HomeActivity.this;
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new MyPageFragment());

        int type = MainActivity.user.getType();
        BottomNavigationView navigationView = findViewById(R.id.nav_view);
        setBottomItem(navigationView, type);

        binding.navView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.navigation_my_page:
                    replaceFragment(new MyPageFragment());
                    break;
                case R.id.navigation_typeSelect1:
                    if(type == 2)
                        replaceFragment(new RestaurantFragment());
                    else if(type == 3)
                        replaceFragment(new WelfareFragment());
                    else
                        replaceFragment(new NotificationFragment());
                    break;
                case R.id.navigation_donation:
                    replaceFragment(new DonationFragment());
                    break;
                case R.id.navigation_typeSelect2:
                    if(type == 3)
                        replaceFragment(new AuthenticationFragment());
                    else if(type == 1 || type ==4)
                        replaceFragment(new UserPointFragment());
                    else
                        replaceFragment(new RestaurantPointFragment());
                    break;
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.home_container, fragment);
        fragmentTransaction.commit();
    }

    private void setBottomItem(BottomNavigationView navigationView, int type){
        if(type == 2){
            navigationView.getMenu().findItem(R.id.navigation_typeSelect1).setIcon(R.drawable.ic_restaurant);
            navigationView.getMenu().findItem(R.id.navigation_typeSelect1).setTitle("업장 관리");
        }
        if(type == 3){
            navigationView.getMenu().findItem(R.id.navigation_typeSelect1).setIcon(R.drawable.ic_handshake);
            navigationView.getMenu().findItem(R.id.navigation_typeSelect1).setTitle("기관 관리");
            navigationView.getMenu().findItem(R.id.navigation_typeSelect2).setIcon(R.drawable.ic_authentication);
            navigationView.getMenu().findItem(R.id.navigation_typeSelect2).setTitle("인증");
        }
    }
}