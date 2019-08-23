package com.example.demo.practice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.demo.practice.fragment.PopMenu;

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

    @OnClick(R.id.jetpack)
    public void in2JetpackActivity(){
        startActivity(new Intent(this,JetpackActivity.class));
    }

    @OnClick(R.id.paging)
    public void in2PagingActivity(){
        startActivity(new Intent(this,PagingActivity.class));
    }

    @OnClick(R.id.lifecycle)
    public void  in2LifeCycleActivity(){
        startActivity(new Intent(this,LifecycleActivity.class));
    }

    @OnClick(R.id.databinding)
    public void in2DataBindingActivity(){
        startActivity(new Intent(this,DatabindingActivity.class));
    }
}
