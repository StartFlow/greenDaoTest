package com.example.demo.greendaotest;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.OnLifecycleEvent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.bumptech.glide.Glide;
import com.example.demo.greendaotest.workmanage.MyWork;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 继承自AppCompatActivity默认实现了LifeCycleOwner接口
 * 继承自Activity需要自己实现LifeCycleOwner接口
 */
public class LifecycleActivity extends AppCompatActivity {


    @BindView(R.id.load_image)
    ImageView loadImage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle_layout);
        ButterKnife.bind(this);
        getLifecycle().addObserver(new MyObserve());
        getSupportActionBar().hide();

    }

    class MyObserve implements LifecycleObserver{
        //任务约束
        Constraints constraints = new Constraints.Builder()
                .setRequiresCharging(true).build();
        //任务实例
        OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(MyWork.class)
                .setConstraints(constraints)
                .build();

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        private void getImageUrl(){
            WorkManager.getInstance().enqueue(request);
            //通过livedata观察数据变化
            WorkManager.getInstance().getWorkInfoByIdLiveData(request.getId()).observe(LifecycleActivity.this, new Observer<WorkInfo>() {
                @Override
                public void onChanged(@Nullable WorkInfo workInfo) {
                    if (workInfo!=null){
                        Data data = workInfo.getOutputData();
                        String url = data.getString("imageurl");
                        Glide.with(LifecycleActivity.this).load(url).into(loadImage);
                    }
                }
            });
        }

    }
}
