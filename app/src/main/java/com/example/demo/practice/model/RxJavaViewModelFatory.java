package com.example.demo.practice.model;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.demo.practice.model.repository.RxJavaRepository;

public class RxJavaViewModelFatory  implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new RxJavaViewModel(new RxJavaRepository());
    }
}
