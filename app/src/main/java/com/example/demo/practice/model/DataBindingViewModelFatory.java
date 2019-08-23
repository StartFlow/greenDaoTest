package com.example.demo.practice.model;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.demo.practice.model.repository.DataBindingRepository;

public class DataBindingViewModelFatory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new DataBindingViewModel(new DataBindingRepository());
    }
}
