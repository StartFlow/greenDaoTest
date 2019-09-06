package com.example.demo.practice.diffutil;

import android.support.v7.util.DiffUtil;

import java.util.List;

public class MyDiffUtil extends DiffUtil.Callback {

    private List<String> newList;
    private List<String> oldList;

    public MyDiffUtil(List<String> newList, List<String> oldList) {
        this.newList = newList;
        this.oldList = oldList;
    }

    @Override
    public int getOldListSize() {
        int size = 0;
        if (oldList!=null){
            size = oldList.size();
        }
        return size;
    }

    @Override
    public int getNewListSize() {
        int size = 0;
        if (newList!=null){
            size = newList.size();
        }
        return size;
    }

    @Override
    public boolean areItemsTheSame(int i, int i1) {
        boolean isSame = false;
        if (oldList.get(i).getClass().equals(newList.get(i1).getClass())){
            isSame = true;
        }
        return isSame;
    }

    @Override
    public boolean areContentsTheSame(int i, int i1) {
        return oldList.get(i).equals(newList.get(i1));
    }
}
