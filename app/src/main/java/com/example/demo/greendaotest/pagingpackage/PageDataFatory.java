package com.example.demo.greendaotest.pagingpackage;

import android.arch.paging.DataSource;
import android.arch.paging.PositionalDataSource;

public class PageDataFatory extends DataSource.Factory<Integer,String> {

    private PositionalDataSource source;

    public PageDataFatory(PositionalDataSource dataSource) {
        this.source = dataSource;
    }


    @Override
    public DataSource<Integer,String> create() {
        return source;
    }
}
