package com.example.demo.greendaotest.pagingpackage;

import android.arch.paging.DataSource;
import android.arch.paging.PositionalDataSource;

public class PageDataFatory extends DataSource.Factory<Integer,String> {
    /**
     *
     * 简单构造数据工厂 加载数据源数据
     * */
    private PositionalDataSource source;

    public PageDataFatory(PositionalDataSource dataSource) {
        this.source = dataSource;
    }


    @Override
    public DataSource<Integer,String> create() {
        return source;
    }
}
