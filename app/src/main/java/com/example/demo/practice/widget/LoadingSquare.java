package com.example.demo.practice.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.example.demo.practice.R;

public class LoadingSquare extends View {

    private String TAG = "LoadingSquare";
    private float squareLength;

    private float width;

    private float height;

    private Path mPath;

    private Paint mPaint;

    private int color = Color.YELLOW;

    private RectF square;

    private float percent = 0.8f;
    private float pathPercent = 0;

    private float centerX;
    private float centerY;

    private ValueAnimator percentAnima;

    private float angle = 0;

    private ValueAnimator angleAnima;

    private String  text = "loading";
    private Paint textPaint;



    public LoadingSquare(Context context) {
        this(context, null);
    }

    public LoadingSquare(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingSquare(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY){
            width = widthSize;
        }
        if (heightMode == MeasureSpec.EXACTLY){
            height = heightSize;
        }
        setMeasuredDimension((int)width,(int)height);
        float shortSize = width<height?width:height;
        squareLength = (float) (shortSize/1.5);
        centerX = width/2;
        centerY = height/2;
        square = new RectF(-squareLength/2,-squareLength/2,squareLength/2,squareLength/2);


    }

    private void init(Context context,AttributeSet attr) {
        TypedArray array = context.obtainStyledAttributes(attr, R.styleable.LoadingSquare);
        color = array.getColor(R.styleable.LoadingSquare_square_color,Color.BLUE);
        array.recycle();

        setLayerType(LAYER_TYPE_SOFTWARE,null);

        textPaint = new Paint(Canvas.ALL_SAVE_FLAG);
        textPaint.setColor(Color.WHITE);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        textPaint.setFakeBoldText(true);
        textPaint.setStrikeThruText(false);
        textPaint.setUnderlineText(false);
        mPath = new Path();

        mPaint = new Paint(Canvas.ALL_SAVE_FLAG);
        mPaint.setDither(true);
        mPaint.setColor(color);
        mPaint.setStrokeWidth(10);


        AnimatorSet set = new AnimatorSet();

        percentAnima = ValueAnimator.ofFloat(0,1f);
        percentAnima.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                percent = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        percentAnima.setDuration(1500);
        percentAnima.setInterpolator(new AccelerateDecelerateInterpolator());
        percentAnima.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                set.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        angleAnima = ValueAnimator.ofFloat(0,360f);
        angleAnima.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //这个函数是使用百分比驱动的时候用的
                angle = (float)animation.getAnimatedValue();
                invalidate();
            }
        });
        angleAnima.setDuration(1500);
        angleAnima.setInterpolator(new DecelerateInterpolator());
        angleAnima.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                percentAnima.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1f,0);
        valueAnimator.setDuration(1000);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                percent = (float)animation.getAnimatedValue();
            }
        });
        set.playTogether(angleAnima,valueAnimator);
        percentAnima.start();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(centerX,centerY);
        canvas.save();
        canvas.rotate(angle);
        mPath.moveTo(-squareLength/2,squareLength/2);
        mPath.addRect(square, Path.Direction.CW);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(mPath,mPaint);
        mPaint.setMaskFilter(null);
        mPath.reset();
        mPath.moveTo(-squareLength / 2, squareLength / 2);

        float y = squareLength / 2;
        y = y - percent * squareLength;
        RectF rectF = new RectF(-squareLength / 2, y, squareLength / 2, squareLength / 2);
        mPath.addRect(rectF, Path.Direction.CW);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawPath(mPath, mPaint);
        mPath.reset();
        canvas.restore();
        textPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        drawTextOnCenter(canvas,textPaint,text);
       // canvas.restoreToCount(layer);

    }

    private void drawTextOnCenter(Canvas canvas,Paint paint,String text){
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(squareLength/4);
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float top = fontMetrics.top;
        float bottom = fontMetrics.bottom;
        float textY = -(top+ bottom)/2;
        canvas.drawText(text,0,textY,paint);
    }

}
