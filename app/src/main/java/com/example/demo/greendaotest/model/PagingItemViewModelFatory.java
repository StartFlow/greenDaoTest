package com.example.demo.greendaotest.model;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.demo.greendaotest.model.repository.PagingItemRepository;

public class PagingItemViewModelFatory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new PagingItemViewModel(new PagingItemRepository());
    }
}
