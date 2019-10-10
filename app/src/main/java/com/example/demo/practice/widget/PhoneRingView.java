package com.example.demo.practice.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.example.demo.practice.R;
/**
 * create by zzs 2three_level/09/19
 * */
public class PhoneRingView extends View {

    private int color;
    private Paint mPaint;
    private Path mPath;

    private float width = 0;
    private float height = 0;

    private float centerX;
    private float centerY;


    private Bitmap phoneBitmap;
    private float phoneWidth;
    private float phoneHeight;

    private volatile  float fraction;

    private ValueAnimator animator;
    
    private float one_level = 0.4f;
    private float two_level = 0.6f;
    private float three_level = 0.8f;
    
    public PhoneRingView(Context context) {
        this(context, null);
    }

    public PhoneRingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PhoneRingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.PhoneRingView);
        color = array.getColor(R.styleable.PhoneRingView_phone_color, Color.WHITE);
        int resId = array.getResourceId(R.styleable.PhoneRingView_src_phone, R.drawable.ic_phone);
        array.recycle();
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        phoneBitmap = getBitmap(context, resId);

        if (phoneBitmap == null) {
            throw new IllegalStateException("phone src bitmap  is null");
        }

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(color);

        mPath = new Path();

        animator = ValueAnimator.ofFloat(0,1);
        animator.setDuration(1500);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                fraction  =(float) animation.getAnimatedValue();
                invalidate();
            }
        });
    }

    public void ring(){
        animator.start();
    }

    public void hangUp(){
        fraction = 0;
        if (animator!=null&&animator.isRunning()){
            animator.cancel();
        }
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            width = MeasureSpec.getSize(widthMeasureSpec);
        }else {
            width = 2*phoneBitmap.getWidth();
        }

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (heightMode == MeasureSpec.EXACTLY) {
            height = MeasureSpec.getSize(widthMeasureSpec);
        }else {
            height = 2*phoneBitmap.getHeight();
        }

        setMeasuredDimension((int)width, (int)height);
        phoneWidth = phoneBitmap.getWidth();
        phoneHeight = phoneBitmap.getHeight();
        centerX =  width / 2;
        centerY = height / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(centerX, centerY);
        canvas.save();
        canvas.drawBitmap(phoneBitmap,  -phoneWidth / 2, -phoneHeight / 2, mPaint);
       // canvas.restoreToCount(layer);
        if (fraction>one_level){
            mPath.moveTo(0, (-one_level*phoneWidth));
            mPath.addArc(0, -(one_level*phoneWidth), (one_level*phoneWidth),0,-90,75);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(2);
            canvas.drawPath(mPath,mPaint);
        }

        if (fraction>two_level){
            mPath.moveTo(0, (-two_level*phoneWidth));
            mPath.addArc(0,- (two_level*phoneWidth), (two_level*phoneWidth),0,-90,75);
            canvas.drawPath(mPath,mPaint);
        }

        if (fraction>three_level){
            mPath.moveTo(0, (-three_level*phoneWidth));
            mPath.addArc(0,- (three_level*phoneWidth), (three_level*phoneWidth),0,-90,75);
            canvas.drawPath(mPath,mPaint);
        }
        mPath.reset();
        Log.e("fraction","fraction = " + fraction);




    }

    private static Bitmap getBitmap(Context context, int vectorDrawableId) {
        Bitmap bitmap = null;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            Drawable vectorDrawable = context.getDrawable(vectorDrawableId);
            bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                    vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            vectorDrawable.draw(canvas);
        } else {
            bitmap = BitmapFactory.decodeResource(context.getResources(), vectorDrawableId);
        }
        return bitmap;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }
}
