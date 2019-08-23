package com.example.demo.practice.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.demo.practice.model.repository.PicViewRepository;

public class PicViewModel extends ViewModel {
    public PicViewRepository picViewRepository;
    public String picUrl;

    public PicViewModel(PicViewRepository repository) {
        this.picViewRepository = repository;
        picUrl = getCacheUrl();
    }

    public LiveData<String> getPicUrl(){
        return  picViewRepository.getPicUrl();
    }

    public String getCacheUrl(){
        return picViewRepository.getCacheUrl();
    }

}
