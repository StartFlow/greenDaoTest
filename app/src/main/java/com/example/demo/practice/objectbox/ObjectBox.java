package com.example.demo.practice.objectbox;

import android.content.Context;

import com.example.demo.practice.entity.MyObjectBox;

import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;
import io.objectbox.android.BuildConfig;

public class ObjectBox {

    public static BoxStore getBoxStore() {
        return boxStore;
    }

    private static BoxStore boxStore;

    public static void init(Context context){
        boxStore = MyObjectBox.builder()
                .androidContext(context.getApplicationContext())
                .build();
        if (BuildConfig.DEBUG) {
            boolean started = new AndroidObjectBrowser(boxStore).start(context);
        }
    }

}
