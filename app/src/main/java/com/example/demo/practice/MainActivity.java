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

    @OnClick(R.id.rxjava)
    public void in2RxJavaActivity(){

        startActivity(new Intent(this,RxJavaActivity.class));
    }

    @OnClick(R.id.objectbox)
    public void in2ObjectBoxActivity(){
        startActivity(new Intent(this,ObjectBoxActivity.class));
    }

    @OnClick(R.id.diffutil)
    public void in2DiffUtilActivity(){
        startActivity(new Intent(this,DiffUtilActivity.class));
    }

    @OnClick(R.id.customview)
    public void in2CustomActivity(){

        startActivity(new Intent(this,CustomViewActivity.class));
    }

    @OnClick(R.id.custom_layout_manage)
    public void in2CustonLayoutManageActivity(){
        startActivity(new Intent(this, CustomViewGroupActivity.class));
    }

    @OnClick(R.id.re_test)
    public void in2CamerXActivity(){
        startActivity(new Intent(this, RecyclerViewTestActivity.class));
    }

    @OnClick(R.id.touch_test)
    public void in2TouchEventActiviti(){
        startActivity(new Intent(this, TouchEventActivity.class));
    }


}
