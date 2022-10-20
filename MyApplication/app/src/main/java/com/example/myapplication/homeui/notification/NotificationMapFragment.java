package com.example.myapplication.homeui.notification;

import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.myapplication.ChangeType3Activity;
import com.example.myapplication.HomeActivity;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.modle.Store;
import com.example.myapplication.modle.Welfare;
import com.example.myapplication.retrofit.RetrofitService;
import com.example.myapplication.retrofit.StoreApi;
import com.example.myapplication.retrofit.UserApi;
import com.example.myapplication.retrofit.WelfareApi;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.util.FusedLocationSource;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationMapFragment extends Fragment implements OnMapReadyCallback{
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    private FusedLocationSource locationSource;
    RetrofitService retrofitService = new RetrofitService();
    WelfareApi welfareApi = retrofitService.getRetrofit().create(WelfareApi.class);
    StoreApi storeApi = retrofitService.getRetrofit().create(StoreApi.class);
    private NaverMap naverMap;
    List<Store> allStores;
    List<Welfare> allWelfare;
    Geocoder geocoder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification_map, container, false);
        geocoder = new Geocoder(view.getContext());

        setAllStores();
        setAllWelfares();

        FragmentManager fm = getChildFragmentManager();
        MapFragment mapFragment = (MapFragment)fm.findFragmentById(R.id.map_fragment);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.map_fragment, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);

        return  view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,  @NonNull int[] grantResults) {
        if (locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            if (!locationSource.isActivated()) { // 권한 거부됨
                naverMap.setLocationTrackingMode(LocationTrackingMode.None);
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.naverMap = naverMap;
        naverMap.setLocationSource(locationSource);
        CameraUpdate cameraUpdate = CameraUpdate.scrollTo(new LatLng(MainActivity.latitude, MainActivity.longitude));
        naverMap.moveCamera(cameraUpdate);
        for(Store s: allStores){
            List<Address> list = getAddressPoint(s.getAddress1());
            if (list != null)
                if(list.size() != 0){
                    Marker marker = new Marker();
                    marker.setIconTintColor(Color.RED);
                    marker.setPosition(new LatLng(list.get(0).getLatitude(), list.get(0).getLongitude()));
                    marker.setMap(naverMap);
                }
        }
        for(Welfare w: allWelfare){
            List<Address> list = getAddressPoint(w.getAddress1());
            if (list != null)
                if(list.size() != 0){
                    Marker marker = new Marker();
                    marker.setIconTintColor(Color.BLUE);
                    marker.setPosition(new LatLng(list.get(0).getLatitude(), list.get(0).getLongitude()));
                    marker.setMap(naverMap);
                }
        }
    }

    private void setAllStores(){
        storeApi.getAllStores().enqueue(new Callback<List<Store>>() {
            @Override
            public void onResponse(Call<List<Store>> call, Response<List<Store>> response) {
                allStores = response.body();
            }
            @Override
            public void onFailure(Call<List<Store>> call, Throwable t) {}
        });
    }

    private void setAllWelfares(){
        welfareApi.getAllWelfares().enqueue(new Callback<List<Welfare>>() {
            @Override
            public void onResponse(Call<List<Welfare>> call, Response<List<Welfare>> response) {
                allWelfare = response.body();
            }
            @Override
            public void onFailure(Call<List<Welfare>> call, Throwable t) {}
        });
    }

    private List<Address> getAddressPoint(String address1){
        List<Address> list = null;
        try {
            list = geocoder.getFromLocationName(address1, 10);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("test","입출력 오류 - 서버에서 주소변환시 에러발생");
        }
        return  list;
    }
}
