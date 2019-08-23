package com.example.demo.practice.model.repository;

import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.example.demo.practice.pagingpackage.PageDataFatory;
import com.example.demo.practice.pagingpackage.PageDataSource;

public class PagingItemRepository {

    public PagingItemRepository() {
    }

    public LiveData<PagedList<String>> getItemList(){
        final PageDataSource source = new PageDataSource();
        PageDataFatory fatory =new PageDataFatory(source);
        //分页配置
        PagedList.Config config = new PagedList.Config.Builder().setPageSize(10)
                .setPrefetchDistance(10)
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(10)
                .build();

        return new LivePagedListBuilder<Integer,String>(fatory,config).build();

    }
}
