package com.example.demo.greendaotest.application;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.demo.greendaotest.entity.DaoMaster;
import com.example.demo.greendaotest.entity.DaoSession;

public class MyApplication extends Application {

    private DaoMaster.DevOpenHelper helper;
    private SQLiteDatabase db;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    public static MyApplication application;

    final String dbName = "qq.db";
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        setDatabase();
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    private void setDatabase(){
        helper = new DaoMaster.DevOpenHelper(this,dbName,null);
        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

}
