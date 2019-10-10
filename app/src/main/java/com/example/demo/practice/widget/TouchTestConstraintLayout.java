package com.example.demo.practice.widget;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class TouchTestConstraintLayout extends ConstraintLayout {

    private final String TAG = "TouchEvent";

    public TouchTestConstraintLayout(Context context) {
        super(context);
    }

    public TouchTestConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchTestConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG,"parent dispatchTouchEvent - Down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG,"parent dispatchTouchEvent - Move");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG,"parent dispatchTouchEvent - Up");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG,"parent onTouchEvent - Down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG,"parent onTouchEvent - Move");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG,"parent onTouchEvent - Up");
                break;
        }
        return super.onTouchEvent(event);
    }
}
