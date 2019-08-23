package com.example.demo.practice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.motion.MotionLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MotionActivity extends AppCompatActivity {

    @BindView(R.id.text)
    ImageButton button;

    @BindView(R.id.motion)
    MotionLayout layout;

    @BindView(R.id.text1)
    TextView item1;

    @BindView(R.id.text2)
    TextView item2;

    @BindView(R.id.text3)
    TextView item3;

    @BindView(R.id.text4)
    TextView item4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_layout);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.text1,R.id.text2,R.id.text3,R.id.text4})
    public void ItemClick(View v){
        Toast.makeText(this, ((TextView)v).getText().toString()+"", Toast.LENGTH_SHORT).show();
    }
}
