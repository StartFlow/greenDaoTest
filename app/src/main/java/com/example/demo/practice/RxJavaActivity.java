package com.example.demo.practice;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.demo.practice.model.RxJavaViewModel;
import com.example.demo.practice.util.InjectorUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RxJavaActivity extends AppCompatActivity {

    private RxJavaViewModel viewModel;

    @BindView(R.id.rx_image)
    TextView view;

    @BindView(R.id.rx_loadimage)
    ImageView imageView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_layout);
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this, InjectorUtil.getRxJavaViewModelFatory()).get(RxJavaViewModel.class);
        observe(viewModel.getLiveData());
        viewModel.getMapLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                Toast.makeText(RxJavaActivity.this, ""+integer, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void observe(LiveData<String> liveData){
        liveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                view.setVisibility(View.GONE);
                Glide.with(RxJavaActivity.this).load(s).into(imageView);

            }
        });
    }

    @OnClick(R.id.rx_image)
    public void loadImage(){
        viewModel.getImageUrl();
    }
}
