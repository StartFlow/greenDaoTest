package com.example.demo.practice.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

public class CustomViewGroup extends ViewGroup {

    private int mTouchSlop = 0;
    private Interpolator interpolator = new DecelerateInterpolator();

    public CustomViewGroup(Context context) {
        this(context, null);
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取系统最小识别滑动距离
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //测量子View
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        final int width = measureWidth(widthMeasureSpec);
        final int height = measureHeight(heightMeasureSpec);
        setMeasuredDimension(width, height);



    }
    //如果有实际高度直接按实际高度  否则按是最高的子View的高度
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
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                int currentChildHeight = child.getMeasuredHeight() + lp.bottomMargin + lp.topMargin;
                if (currentChildHeight > maxHeight) {
                    maxHeight = currentChildHeight;
                }
            }
            height = maxHeight;
        }
        return height;
    }
    //如果有实际宽度直接按实际宽度  否则按是所有子View的宽度和
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
                width += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            }
        }
        return width;
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


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int a = 0; a < getChildCount(); a++) {
            View child = getChildAt(a);
            //按照基线去布局子View
            final int baseLine = getBaseLineViaChild(child);
            layoutChild(child, baseLine);
        }
    }

    private void layoutChild(View child, int baseLine) {
        //具体的布局子View 包含margin参数
        MyLayoutParams lp = (MyLayoutParams) child.getLayoutParams();
        int left = baseLine - child.getMeasuredWidth() / 2;
        int top = getHeight() / 2 - child.getMeasuredHeight() / 2;
        int right = left + child.getMeasuredWidth();
        int bottom = top + child.getMeasuredHeight();
        child.setAlpha(lp.alpha);
        child.setScaleX(lp.scale);
        child.setScaleY(lp.scale);
        child.layout(left + lp.leftMargin, top + lp.topMargin
                , right + lp.rightMargin, bottom + lp.bottomMargin);

    }

    private int getBaseLineViaChild(View child) {
        //根据手指滑动宽度百分比 动态更改基线的位置
        int baseLine = 0;
        int leftBase = getWidth() / 4;
        int centerBase = getWidth() / 2;
        int rightBase = getWidth() / 2 + getWidth() / 4;
        MyLayoutParams lp = (MyLayoutParams) child.getLayoutParams();
        if (lp.from == -1) {
            lp.from = indexOfChild(child);
        }
        switch (lp.from) {
            case 0:
                switch (lp.to) {
                    case 1:
                        baseLine = leftBase + (int) ((rightBase - leftBase) * -mPercent);
                        break;
                    case 2:
                        baseLine = leftBase + (int) ((centerBase - leftBase) * mPercent);
                        break;
                    default:
                        baseLine = leftBase;
                }
                break;
            case 2:
                switch (lp.to) {
                    case 0:
                        baseLine = centerBase + (int) ((centerBase - leftBase) * mPercent);
                        break;
                    case 1:
                        baseLine = centerBase + (int) ((rightBase - centerBase) * mPercent);
                        break;
                    default:
                        baseLine = centerBase;
                }
                break;
            case 1:
                switch (lp.to) {
                    case 0:
                        baseLine = rightBase + (int) ((rightBase - leftBase) * -mPercent);
                        break;
                    case 2:
                        baseLine = rightBase + (int) ((rightBase - centerBase) * mPercent);
                        break;
                    default:
                        baseLine = rightBase;
                }
                break;
            default:

        }
        return baseLine;

    }

    @Override
    public void attachViewToParent(View child, int index, ViewGroup.LayoutParams params) {
        super.attachViewToParent(child, index, params);
    }

    @Override
    public void detachViewFromParent(View child) {
        super.detachViewFromParent(child);
    }

    @Override
    public void addView(View child) {
        child.setLayoutParams(new MyLayoutParams(new ViewGroup.LayoutParams(400, 400)));
        super.addView(child);
    }


    private static float MIN_Scale = 0.8f;
    private static float MIN_Alpha = 0.2f;

    @Override
    public void addView(View child, int index, LayoutParams params) {
        //复现XML布局添加View的方法
        if (getChildCount() > 2) {
            throw new IllegalStateException("ViewGroup only can contain 3 childView");
        }

        if (!(params instanceof MyLayoutParams)) {
            params = new MyLayoutParams(params);
        }
        if (getChildCount() < 2) {
            ((MyLayoutParams) params).alpha = MIN_Alpha;
            ((MyLayoutParams) params).scale = MIN_Scale;
        } else {
            ((MyLayoutParams) params).alpha = 1f;
            ((MyLayoutParams) params).scale = 1f;
        }
        super.addView(child, index, params);
    }


    private float mLastX = 0;
    private float mLastY = 0;
    private boolean isDrag;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final float x = ev.getX();
        final float y = ev.getY();
        final int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                interruptAnima();
                downX = x;
                downY = y;
                mLastX = x;
                mLastY = y;
                //点击不是中间最大的View 拦截触摸事件
                View  hitView = findHitView(x,y);
                if (indexOfChild(hitView)!=2){
                    return true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                float offsetX = x - mLastX;
                float offsetY = y - mLastY;
                //滑动状态拦截触摸事件
                if (Math.abs(offsetX) > mTouchSlop || Math.abs(offsetY) > mTouchSlop) {
                    isDrag = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                handleTouchUp(x, y);
                isDrag = false;
                break;
        }
        return isDrag;
    }

    private float mOffsetX = 0;
    private float downX;
    private float downY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final float x = event.getX();
        final float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = x;
                downY = y;
                interruptAnima();
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(x - downX) > mTouchSlop || Math.abs(y - downY) > mTouchSlop) {
                    final float offsetX = x - mLastX;
                    mOffsetX += offsetX;
                    itemMove();
                }

                break;
            case MotionEvent.ACTION_UP:
                handleTouchUp(x, y);
                isDrag = false;
                break;
        }
        mLastX = x;
        mLastY = y;
        return true;
    }

    private float mPercent = 0;

    private void itemMove() {
        mPercent = mOffsetX / getWidth();

        updateChildrenFromAndTo();

        updateChildrenAlphaAndScale();

        updateChildIndexOrder();

        requestLayout();
    }

    private void updateChildrenFromAndTo() {
        if (Math.abs(mPercent) >= 1f) {
            isDrag = false;
            for (int a = 0; a < getChildCount(); a++) {
                View child = getChildAt(a);
                MyLayoutParams lp = (MyLayoutParams) child.getLayoutParams();
                lp.from = lp.to;
            }
            isReOder = false;
            mOffsetX %= getWidth();
            mPercent %= 1f;
        } else {
            for (int a = 0; a < getChildCount(); a++) {
                MyLayoutParams lp = (MyLayoutParams) getChildAt(a).getLayoutParams();
                int to = 0;
                switch (lp.from) {
                    case 0:
                        if (mPercent > 0) {
                            to = 2;
                        } else {
                            to = 1;
                        }
                        break;
                    case 1:
                        if (mPercent > 0) {
                            to = 0;
                        } else {
                            to = 2;
                        }
                        break;
                    case 2:
                        if (mPercent > 0) {
                            to = 1;
                        } else {
                            to = 0;
                        }
                        break;
                }
                lp.to = to;
            }
        }
    }

    private ValueAnimator animator;

    private void handleTouchUp(float x, float y) {
        handleClick(x, y);
        if (getChildCount() == 0)
            return;
        final float start = mOffsetX;
        float end = 0;
        if (mPercent > 0.5f) {
            end = getWidth();
        } else if (mPercent < -0.5f) {
            end = -getWidth();
        }
        startAnimation(start, end);
    }

    private void handleClick(float x, float y) {
        final float offsetX = x - downX;
        final float offsetY = y - downY;
        if (Math.abs(offsetX) < mTouchSlop && Math.abs(offsetY) < mTouchSlop) {
            View hitChild = findHitView(x, y);
            if (hitChild != null) {
                if (indexOfChild(hitChild) != 2) {
                    MyLayoutParams lp = (MyLayoutParams) hitChild.getLayoutParams();
                    setSelection(lp.from);
                }
            }
        }
    }

    private void setSelection(int index) {
        if ((animator != null && animator.isRunning()) || (getChildCount() == 0) || indexOfChild(getChildAt(getChildCount() - 1)) == index)
            return;
        final float start = mOffsetX;
        float end = 0;
        switch (index) {
            case 0:
                end = getWidth();
                break;
            case 1:
                end = -getWidth();
                break;
        }
        startAnimation((int) start, (int) end);
    }

    private View findHitView(float x, float y) {
        int count = getChildCount();
        for (int a = count-1; a >=0; a--) {
            Rect rect = new Rect();
            View child = getChildAt(a);
            child.getHitRect(rect);
            if (rect.contains((int) x, (int) y)) {
                return child;
            }
        }
        return null;
    }

    private void startAnimation(float start, float end) {
        if (start == end)
            return;
        interruptAnima();

        animator = ValueAnimator.ofFloat(start, end);
        animator.setDuration(500);
        animator.setInterpolator(interpolator);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mOffsetX = (float) animation.getAnimatedValue();
                itemMove();
            }
        });
        animator.start();
    }


    private void interruptAnima() {
        if (animator != null && animator.isRunning()) {
            animator.cancel();
        }
    }

    private boolean isReOder;
    private void updateChildIndexOrder() {
        if (Math.abs(mPercent) > 0.5f) {
            if (!isReOder) {
                exChangeOrder(1, 2);
                isReOder = true;
            }
        }else {
            if (isReOder){
                exChangeOrder(1, 2);
                isReOder = false;
            }

        }
    }

    private void setBottomOrder(View child) {
        exChangeOrder(indexOfChild(child), 0);
    }

    private void updateChildrenAlphaAndScale() {
        for (int a = 0; a < getChildCount(); a++) {
            updateAlphaAndScale(getChildAt(a));
        }
    }

    private void updateAlphaAndScale(View child) {
        MyLayoutParams lp = (MyLayoutParams) child.getLayoutParams();
        switch (lp.from) {
            case 0:
                switch (lp.to) {
                    case 1:
                        if (indexOfChild(child)!=0){
                            setBottomOrder(child);
                        }
                        break;
                    case 2:
                        lp.alpha = MIN_Alpha + (1f - MIN_Alpha) * mPercent;
                        lp.scale = MIN_Scale + (1f - MIN_Scale) * mPercent;
                        break;
                    default:
                }
                break;
            case 1:
                switch (lp.to) {
                    case 0:
                        if (indexOfChild(child)!=0){
                            setBottomOrder(child);
                        }
                        break;
                    case 2:
                        lp.alpha = MIN_Alpha + (1f - MIN_Alpha) * -mPercent;
                        lp.scale = MIN_Scale + (1f - MIN_Scale) * -mPercent;
                        break;
                }
                break;
            case 2:
                lp.scale = 1f - (1f - MIN_Scale) * Math.abs(mPercent);
                lp.alpha = 1f - (1f - MIN_Alpha) * Math.abs(mPercent);
                break;
        }
    }


    static class MyLayoutParams extends MarginLayoutParams {

        int from = -1;
        int to;
        float scale = 0;
        float alpha = 0;

        public MyLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public MyLayoutParams(int width, int height) {
            super(width, height);
        }

        public MyLayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public MyLayoutParams(LayoutParams source) {
            super(source);
        }
    }

}
