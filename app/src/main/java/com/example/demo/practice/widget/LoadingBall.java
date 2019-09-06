package com.example.demo.practice.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.example.demo.practice.R;

public class LoadingBall extends View {

    private float radius;
    private float mWidth;
    private float mHeight;
    private float percent;
    private int color;
    private String text = "人";
    private Paint textPaint;
    private Path mPath;
    private Paint mainPaint;
    private float centerX;
    private float centerY;
    private Path clipPath;


    public LoadingBall(Context context) {
        this(context, null);
    }

    public LoadingBall(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingBall(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        }
        setMeasuredDimension((int)mWidth,(int)mHeight);
        centerX = mWidth / 2;
        centerY = mHeight / 2;
        radius = mWidth < mHeight ? mWidth/2 : mHeight/2;

    }

    private void init(Context context, AttributeSet arrt) {
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        TypedArray array = context.obtainStyledAttributes(arrt, R.styleable.LoadingBall);
        color = array.getColor(R.styleable.LoadingBall_color, Color.rgb(41, 163, 254));
        text = array.getString(R.styleable.LoadingBall_text);
        array.recycle();

        mainPaint = new Paint();
        mainPaint.setColor(color);
        mainPaint.setStyle(Paint.Style.FILL);
        mainPaint.setAntiAlias(true);
        mainPaint.setDither(true);

        textPaint = new Paint();
        textPaint.setDither(true);
        textPaint.setAntiAlias(true);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mPath = new Path();
        clipPath = new Path();
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                percent = animation.getAnimatedFraction();
                invalidate();
            }
        });
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.start();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(centerX,centerY);
        canvas.save();
        textPaint.setColor(color);
        drawTextOnCenter(canvas,textPaint,text);
        textPaint.setColor(Color.WHITE);
        clipPath.addCircle(0,0,radius, Path.Direction.CW);
        canvas.clipPath(clipPath);
        mPath = getSeibelPath();
        canvas.drawPath(mPath,mainPaint);
        canvas.clipPath(mPath);
        drawTextOnCenter(canvas,textPaint,text);
        canvas.restore();
    }


    //画文字居中
    private void drawTextOnCenter(Canvas canvas,Paint paint,String text){
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(radius);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float top = fontMetrics.top;
        float bottom = fontMetrics.bottom;
        float textY = -(top+ bottom)/2;
        canvas.drawText(text,0,textY,paint);

    }


    //画出周期曲线
    private Path getSeibelPath() {
        Path path = new Path();
        float x = -radius*3;
        x += percent * 2*radius;
        path.moveTo(x, 0);
        path.rQuadTo(radius / 2, radius/2 , radius , 0);
        path.rQuadTo(radius / 2, -radius/2 , radius , 0);

        path.rQuadTo(radius / 2, radius/2 , radius , 0);
        path.rQuadTo(radius / 2, -radius /2, radius , 0);

        path.lineTo(x + 4*radius, radius);
        path.lineTo(x, radius);
        path.close();
        return path;
    }
}
