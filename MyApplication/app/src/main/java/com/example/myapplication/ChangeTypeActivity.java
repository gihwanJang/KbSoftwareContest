package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ChangeTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_type);

        Button changeType2Btn = findViewById(R.id.change_type2_btn);
        Button changeType3Btn = findViewById(R.id.change_type3_btn);

        changeType2Btn.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), ChangeType2Activity.class);
            startActivity(i);
        });

        changeType3Btn.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), ChangeType3Activity.class);
            startActivity(i);
        });
    }
}