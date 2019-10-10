package com.example.demo.practice.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;


/**
 *一个伸缩的ViewGroup
 */

public class PopupLayout extends ViewGroup {

    private final String TAG = "PopUpLayout";

    private float fraction = 0;
    private boolean isOpen = false;

    private boolean indicatorRight =  true;
    private View theTopChild;
    private ValueAnimator mAnimator;

    public PopupLayout(Context context) {
        super(context);
        init();
    }

    public PopupLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PopupLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mAnimator = ValueAnimator.ofFloat(0,1);
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                fraction = animation.getAnimatedFraction();
                requestLayout();
            }
        });
        mAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                    Log.e(TAG,"mAnimator End");
                    if (!isOpen){
                        exChangeOrder(indexOfChild(theTopChild),getChildCount()-1);
                    }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
            final int childCount = getChildCount();
            for (int a = 0;a<childCount;a++){
                View  child = getChildAt(a);
                MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
                //Left
                if (!indicatorRight){
                    int left =(int) (fraction*indexOfChild(child)*(child.getMeasuredWidth()+layoutParams.leftMargin+layoutParams.rightMargin));
                    int right = left+child.getMeasuredWidth();
                    int top = layoutParams.topMargin;
                    int bottom = top+child.getMeasuredHeight();
                    child.layout(left,top,right,bottom);
                }else {
                    final int parentWidth =getWidth();
                    int left =(int)(parentWidth- (fraction*indexOfChild(child)*(child.getMeasuredWidth()+layoutParams.leftMargin+layoutParams.rightMargin))-child.getMeasuredWidth());
                    int right = left+child.getMeasuredWidth();
                    int top = layoutParams.topMargin;
                    int bottom = top+child.getMeasuredHeight();
                    child.layout(left,top,right,bottom);
                }

            }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final float x = ev.getX();
        final float y = ev.getY();
        final int action = ev.getAction();
        if (null==findHitView(x,y)){
            return false;
        }
        if (!isOpen){
            return true;
        }else {
            if (action==MotionEvent.ACTION_UP){
                closeLayout(x,y);
            }
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        final float x = event.getX();
        final float y = event.getY();
        if (action==MotionEvent.ACTION_UP){
            if (!isOpen){
                isOpen = true;
                mAnimator.start();
                Log.e(TAG,"open layout");
            }else {
                closeLayout(x,y);
            }
        }
        return true;
    }

    private void closeLayout(float x,float y){
        isOpen = false;
        mAnimator.reverse();
        Log.e(TAG,"close layout");
        View view = findHitView(x,y);
        if (view!=null){
            theTopChild = view;
        }
    }

    public void exChangeOrder(int from, int to) {
        Log.e("CustomLayout"," exChange index");
        if (from == to || from >= getChildCount() || to >= getChildCount())
            return;

        if (from > to) {
            int temp = from;
            from = to;
            to = temp;
        }

        View fromView = getChildAt(from);
        View toView = getChildAt(to);

        detachViewFromParent(fromView);
        detachViewFromParent(toView);

        attachViewToParent(toView, from, toView.getLayoutParams());
        attachViewToParent(fromView, to, fromView.getLayoutParams());
        invalidate();
    }

    private View findHitView(float x,float y){
        final int childCount = getChildCount();
        for (int a = 0;a<childCount;a++){
            View child = getChildAt(a);
            Rect rect = new Rect();
            child.getHitRect(rect);
            if (rect.contains((int)x,(int)y)){
                return child;
            }
        }
        return null;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        measureChildren(widthMeasureSpec,heightMeasureSpec);
        final int width = measureWidth(widthMeasureSpec);
        final int height = measureHeight(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    private int measureHeight(int heightMeasureSpac) {
        int height = 0;
        int heightSize = MeasureSpec.getSize(heightMeasureSpac);
        int heightMode = MeasureSpec.getMode(heightMeasureSpac);
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            int maxHeight = 0;
            for (int a = 0; a < getChildCount(); a++) {
                View child = getChildAt(a);
                child.setBackgroundColor(Color.WHITE);
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                int currentChildHeight = child.getMeasuredHeight()+lp.topMargin+lp.bottomMargin;
                if (currentChildHeight > maxHeight) {
                    maxHeight = currentChildHeight;
                }
            }
            height = maxHeight;
        }
        return height;
    }

    private int measureWidth(int widthMeasureSpec) {
        int width = 0;
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            for (int a = 0; a < getChildCount(); a++) {
                View child = getChildAt(a);
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

                width += child.getMeasuredWidth() +lp.rightMargin+lp.leftMargin;
            }
        }
        return width;
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }
}
