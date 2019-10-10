package com.example.demo.practice.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class TouchTestView extends View {

    private final String TAG = "TouchEvent";

    public TouchTestView(Context context) {
        super(context);
    }

    public TouchTestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        return  super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG,"child dispatchTouchEvent - Down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG,"child dispatchTouchEvent - Move");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG,"child dispatchTouchEvent - Up");
                break;
        }
        return super.dispatchTouchEvent(event);
    }
}
