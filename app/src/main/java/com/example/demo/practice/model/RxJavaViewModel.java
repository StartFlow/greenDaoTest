package com.example.demo.practice.model;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.demo.practice.model.repository.RxJavaRepository;

public class RxJavaViewModel extends ViewModel {

    private RxJavaRepository repository;

    public RxJavaViewModel(RxJavaRepository repository) {
        this.repository = repository;
    }

    private MutableLiveData<String> liveData = new MutableLiveData<>();

    public MutableLiveData<String> getLiveData() {
        return liveData;
    }

    public void getImageUrl(){
        repository.getImageUrl(liveData);
    }

}
