package com.example.demo.greendaotest.util;

import com.example.demo.greendaotest.model.PagingItemViewModelFatory;
import com.example.demo.greendaotest.model.PicViewModelFatory;

public class InjectorUtil {

    private static PicViewModelFatory picViewFatory;
    private static PagingItemViewModelFatory pagingItemFatory;

    public static PicViewModelFatory getPicViewModelFatory(){
        if (picViewFatory==null){
            picViewFatory = new PicViewModelFatory();
        }

        return  picViewFatory;
    }

    public static PagingItemViewModelFatory getPagingItemFatory(){
        if (pagingItemFatory==null){
            pagingItemFatory = new PagingItemViewModelFatory();
        }
        return pagingItemFatory;
    }
}
