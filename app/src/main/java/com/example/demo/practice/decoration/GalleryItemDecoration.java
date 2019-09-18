package com.example.demo.practice.decoration;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class GalleryItemDecoration extends RecyclerView.ItemDecoration {

    int mPageMargin = 20;
    int mLeftPageVisibleWith = 80;

    public static int mCosumerX;
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildLayoutPosition(view);

        //页面宽度  没有边距
        int itemWidth = parent.getWidth()-dpToPx(4*mPageMargin+2*mLeftPageVisibleWith);
        int itemCount = parent.getAdapter().getItemCount();

        mCosumerX = itemWidth+dpToPx(2*mPageMargin);

        int itemLeftMargin = position==0?dpToPx(mLeftPageVisibleWith+2*mPageMargin):dpToPx(mPageMargin);
        int itemRightMargin = position==itemCount-1?dpToPx(mLeftPageVisibleWith+2*mPageMargin):dpToPx(mPageMargin);
        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) view.getLayoutParams();
        lp.setMargins(itemLeftMargin,0,itemRightMargin,0);
        lp.width = itemWidth;
        view.setLayoutParams(lp);
        view.setScaleY(0.8f);
        view.setScaleX(0.8f);

    }

    public int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density + 0.5f);
    }
}
