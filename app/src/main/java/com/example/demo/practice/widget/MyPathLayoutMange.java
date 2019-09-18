package com.example.demo.practice.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;


/**
 * 这个有Bug  待学习改进
 * */
public class MyPathLayoutMange extends RecyclerView.LayoutManager {

    private final int MAX_COUNT = 4;
    private final float levelScale = 0.05f;
    private final int levelTranslation = 25;

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        detachAndScrapAttachedViews(recycler);
        int itemCount = getItemCount();
        if (itemCount>=MAX_COUNT){
            for (int position = itemCount-MAX_COUNT;position<itemCount;position++){
                View view = recycler.getViewForPosition(position);
                addView(view);
                measureChildWithMargins(view,0,0);
                int childWidth = getDecoratedMeasuredWidth(view);
                int childeHeight = getDecoratedMeasuredHeight(view);
                int left = getWidth()/2-childWidth/2;
                int top = getHeight()/2-childeHeight/2;
                layoutDecoratedWithMargins(view,left,top,left+childWidth,top+childeHeight);

                int viewLevel = itemCount-position-1;
                if (viewLevel>0){
                    view.setScaleX(1-viewLevel*levelScale);
                    if (viewLevel<MAX_COUNT-1){
                        view.setTranslationY(viewLevel*levelTranslation);
                        view.setScaleY(1-viewLevel*levelScale);
                    }else {
                        view.setTranslationY(levelScale*(viewLevel-1));
                        view.setScaleY(1-levelScale*(viewLevel-1));
                    }
                }
            }
        }
    }




//    @Override
//    public boolean canScrollVertically() {
//        return true;
//    }
//
//    @Override
//    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
//        int distance = 0;
//        return distance;
//    }
}
