package com.example.demo.greendaotest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.demo.greendaotest.fragment.PopMenu;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.greendao)
    TextView in2Green;

    @BindView(R.id.fragment)
    TextView showFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.greendao)
    public void in2GreenDaoActivity(){
        Intent intent = new Intent(this,GreenDaoActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.fragment)
    public void showFragment(){
        new PopMenu().show(getSupportFragmentManager(),"");
    }

    @OnClick(R.id.view)
    public void in2MotionActivity(){
        startActivity(new Intent(this,MotionActivity.class));
    }


}
