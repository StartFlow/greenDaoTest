package com.example.demo.practice.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.demo.practice.model.repository.DataBindingRepository;

public class DataBindingViewModel extends ViewModel {
    private DataBindingRepository bindingRepository;

    public DataBindingViewModel(DataBindingRepository bindingRepository) {
        this.bindingRepository = bindingRepository;
    }

    public LiveData<String> getName(){
        return  bindingRepository.getName();
    }
}
