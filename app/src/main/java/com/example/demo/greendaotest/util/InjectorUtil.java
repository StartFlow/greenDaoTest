package com.example.demo.greendaotest.util;

import com.example.demo.greendaotest.model.PicViewModelFatory;

public class InjectorUtil {

    private static PicViewModelFatory fatory;

    public static PicViewModelFatory getPicViewModelFatory(){
        if (fatory==null){
            fatory = new PicViewModelFatory();
        }

        return  fatory;
    }
}
