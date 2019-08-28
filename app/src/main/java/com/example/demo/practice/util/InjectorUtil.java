package com.example.demo.practice.util;

import com.example.demo.practice.model.DataBindingViewModelFatory;
import com.example.demo.practice.model.PagingItemViewModelFatory;
import com.example.demo.practice.model.PicViewModelFatory;
import com.example.demo.practice.model.RxJavaViewModelFatory;

public class InjectorUtil {

    private static PicViewModelFatory picViewFatory;
    private static PagingItemViewModelFatory pagingItemFatory;
    private static DataBindingViewModelFatory dataBindingViewModelFatory;

    public static RxJavaViewModelFatory getRxJavaViewModelFatory() {
        if (rxJavaViewModelFatory==null){
            rxJavaViewModelFatory = new RxJavaViewModelFatory();
        }
        return rxJavaViewModelFatory;
    }

    private static RxJavaViewModelFatory rxJavaViewModelFatory;

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

    public static DataBindingViewModelFatory getDataBindingViewModelFatory(){
        if (dataBindingViewModelFatory==null){
            dataBindingViewModelFatory = new DataBindingViewModelFatory();
        }

        return dataBindingViewModelFatory;
    }



}
