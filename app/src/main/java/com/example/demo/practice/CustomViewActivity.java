package com.example.demo.practice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;

import com.example.demo.practice.widget.BlurImageView;
import com.example.demo.practice.widget.SunnyView;

public class CustomViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_layout);
        SunnyView sunnyView = findViewById(R.id.sunny);
        sunnyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sunnyView.start();
            }
        });
//
//        PhoneRingView phoneRingView = findViewById(R.id.phone);
//        phoneRingView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                phoneRingView.ring();
//            }
//        });
//
//        JumpingButton jpbt = findViewById(R.id.jpbt);
//        jpbt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                jpbt.start();
//            }
//        });

        BlurImageView blurImageView = findViewById(R.id.blur_image);
        SeekBar seekBar = findViewById(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float fraction = (float) progress / seekBar.getMax();
                blurImageView.setBlurFraction(fraction);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
