package com.example.demo.practice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.demo.practice.widget.SunnyView;

public class CustomViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_layout);
        getSupportActionBar().hide();
        SunnyView sunnyView = findViewById(R.id.sunny);
        sunnyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sunnyView.start();
            }
        });

    }
}
