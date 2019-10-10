package com.example.demo.practice.model;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import com.example.demo.practice.model.repository.RxJavaRepository;

public class RxJavaViewModel extends ViewModel {

    private RxJavaRepository repository;

    public RxJavaViewModel(RxJavaRepository repository) {
        this.repository = repository;
    }
    private MutableLiveData<String> liveData = new MutableLiveData<>();

    public LiveData<Integer> getMapLiveData() {
        return mapLiveData;
    }
    //一个新的livedata ,随着livedata的变化而变化，当livedata的值改变时，switchLiveData可以监听到该改变，第二个方法参数做出相应的变化
    private LiveData<Integer> mapLiveData = Transformations.map(liveData, new Function<String, Integer>() {

        @Override
        public Integer apply(String input) {
            return 20;
        }
    });

    public MutableLiveData<String> getLiveData() {
        return liveData;
    }

    public void getImageUrl(){
        repository.getImageUrl(liveData);
    }


}
