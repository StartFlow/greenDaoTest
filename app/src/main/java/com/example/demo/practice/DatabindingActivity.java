package com.example.demo.practice;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.example.demo.practice.databinding.ActivityDatabindingLayoutBinding;
import com.example.demo.practice.model.DataBindingViewModel;
import com.example.demo.practice.util.InjectorUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DatabindingActivity  extends AppCompatActivity {
    ActivityDatabindingLayoutBinding binding;
    private DataBindingViewModel viewModel;
    /**
     * 粗浅使用
     * */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding_layout);
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this, InjectorUtil.getDataBindingViewModelFatory()).get(DataBindingViewModel.class);
        obServeName(viewModel.getName());
    }

    private void obServeName(LiveData<String> liveData) {
        liveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                if (!TextUtils.isEmpty(s)) {
                    binding.setName(s);
                }
            }
        });
    }

    @OnClick(R.id.change)
    public void change() {
        obServeName(viewModel.getName());
    }

//    public List<Activity> getAllActivitys(){
//        List<Activity> list=new ArrayList<>();
//        try {
//            Class<?> activityThread=Class.forName("android.app.ActivityThread");
//            Method currentActivityThread=activityThread.getDeclaredMethod("currentActivityThread");
//            currentActivityThread.setAccessible(true);
//            //获取主线程对象
//            Object activityThreadObject=currentActivityThread.invoke(null);
//            Field mActivitiesField = activityThread.getDeclaredField("mActivities");
//            mActivitiesField.setAccessible(true);
//            Map<Object,Object> mActivities = (Map<Object,Object>) mActivitiesField.get(activityThreadObject);
//            for (Map.Entry<Object,Object> entry:mActivities.entrySet()){
//                Object value = entry.getValue();
//                Class<?> activityClientRecordClass = value.getClass();
//                Field activityField = activityClientRecordClass.getDeclaredField("activity");
//                activityField.setAccessible(true);
//                Object o = activityField.get(value);
//                list.add((Activity) o);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }

}