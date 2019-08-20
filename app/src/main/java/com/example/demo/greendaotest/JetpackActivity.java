package com.example.demo.greendaotest;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.demo.greendaotest.model.PicViewModel;
import com.example.demo.greendaotest.util.InjectorUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JetpackActivity extends AppCompatActivity {

    @BindView(R.id.bg_pic)
    ImageView bgImagview;

    private PicViewModel picViewModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jetpack_layout);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        picViewModel = ViewModelProviders.of(this, InjectorUtil.getPicViewModelFatory()).get(PicViewModel.class);
        if (TextUtils.isEmpty(picViewModel.picUrl)){
            obServebgPic(picViewModel.getPicUrl());
        }else {
            loadBgImageView(picViewModel.picUrl);
        }
    }
    private void obServebgPic(LiveData<String> liveData) {
        liveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (picViewModel.picUrl == null) {
                    picViewModel.picUrl = s;
                }
                loadBgImageView(s);
            }
        });
    }

    private void loadBgImageView(String s) {
        Glide.with(this).load(s).into(bgImagview);
    }

    @OnClick(R.id.refresh)
    public void getPic() {
        picViewModel.getPicUrl();
    }

}
