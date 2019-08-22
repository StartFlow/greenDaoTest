package com.example.demo.greendaotest.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;

import com.example.demo.greendaotest.model.repository.PagingItemRepository;

public class PagingItemViewModel extends ViewModel {
    private PagingItemRepository itemRepository;

    public PagingItemViewModel(PagingItemRepository itemRepository) {
       this.itemRepository =  itemRepository;
    }

    public LiveData<PagedList<String>> getItemList(){

       return itemRepository.getItemList();
    }
}
