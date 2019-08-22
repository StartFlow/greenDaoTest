package com.example.demo.greendaotest;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.demo.greendaotest.model.PicViewModel;
import com.example.demo.greendaotest.util.InjectorUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JetpackActivity extends AppCompatActivity {

    @BindView(R.id.bg_pic)
    ImageView bgImagview;

    @BindView(R.id.pro_show)
    ProgressBar bar;

    private PicViewModel picViewModel;

    /**
     * 使用MVVM构造模式，分层级引用  UI 控制器引用 ViewModel，ViewModel 引用数据源仓库 ，仓库持有网络层或者本地化数据获取的引用
     *
     * 依赖ViewModel LiveData 实现， 暂时没有使用 DataBinding
     * */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jetpack_layout);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
        picViewModel = ViewModelProviders.of(this, InjectorUtil.getPicViewModelFatory()).get(PicViewModel.class);
        if (TextUtils.isEmpty(picViewModel.picUrl)){
            obServebgPic(picViewModel.getPicUrl());
            bar.setVisibility(View.VISIBLE);
        }else {
            loadBgImageView(picViewModel.picUrl);
        }
    }
    private void obServebgPic(LiveData<String> liveData) {
        //需要观察为同一对象
        liveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                bar.setVisibility(View.GONE);
                if (picViewModel.picUrl == null) {
                    picViewModel.picUrl = s;
                }else {
                    if (picViewModel.picUrl.equals(s)){
                        Toast.makeText(JetpackActivity.this, "已经是最新图片啦~", Toast.LENGTH_SHORT).show();
                        return;
                    }
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
        obServebgPic(picViewModel.getPicUrl());
        bar.setVisibility(View.VISIBLE);
    }


}
