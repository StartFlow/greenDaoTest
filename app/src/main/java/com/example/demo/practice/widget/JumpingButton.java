package com.example.demo.practice.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import com.example.demo.practice.R;

public class JumpingButton extends View {


    private String bottomText;
    private int color;
    private Bitmap bitmap;

    private Paint mPaint;
    private Path mPath;

    private int width;
    private int height;
    private float centerX;
    private float centerY;
    private float radius;
    private float fraction;

    private RectF bitmapRect;

    private float bitmapTop = 0;

    public JumpingButton(Context context) {
        this(context,null);
    }

    public JumpingButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public JumpingButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attrs){
        initAttribute(context,attrs);
        initDrawUtil();
        initAnimator();
    }

    private void initAnimator(){
        initZoomAnimator();
        initMoveAnimator();
    }

    private ValueAnimator zoomAnimator;
    private void initZoomAnimator(){
      zoomAnimator = ValueAnimator.ofFloat(0,1);
      zoomAnimator.setDuration(1500);
      zoomAnimator.setInterpolator(new OvershootInterpolator());
      zoomAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
          @Override
          public void onAnimationUpdate(ValueAnimator animation) {
              fraction = (float)animation.getAnimatedValue();
              invalidate();
          }
      });

      zoomAnimator.addListener(new Animator.AnimatorListener() {
          @Override
          public void onAnimationStart(Animator animation) {

          }

          @Override
          public void onAnimationEnd(Animator animation) {
                moveAnimator.start();
          }

          @Override
          public void onAnimationCancel(Animator animation) {

          }

          @Override
          public void onAnimationRepeat(Animator animation) {

          }
      });
    }

    private ValueAnimator moveAnimator;
    private float moveFraction = 0;
    private void initMoveAnimator(){
        moveAnimator = ValueAnimator.ofFloat(0,1);
        moveAnimator.setDuration(1500);
        moveAnimator.setInterpolator(new OvershootInterpolator());
        moveAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                moveFraction = animation.getAnimatedFraction();
                invalidate();
            }
        });
    }

    public void start(){
        initData();
        zoomAnimator.start();
    }

    private void initAttribute(Context context,AttributeSet attrs){
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.JumpingButton);
        bottomText = array.getString(R.styleable.JumpingButton_bottom_text);
        color = array.getColor(R.styleable.JumpingButton_main_color, Color.GRAY);
        int resId = array.getResourceId(R.styleable.JumpingButton_src,R.drawable.ic_smile);
        array.recycle();
        bitmap = getBitmap(context,resId);
        if (TextUtils.isEmpty(bottomText)){
            bottomText = "item";
        }
    }

    private void initDrawUtil(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);
        mPaint.setColor(color);
        mPaint.setStyle(Paint.Style.FILL);
        mPath = new Path();
        bitmapRect = new RectF();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureWidth(widthMeasureSpec);
        measureHeight(heightMeasureSpec);
        setMeasuredDimension(width,height);
        centerX = (float) width/2;
        centerY = (float) height/2;
        radius = bitmap.getWidth()/2;
        bitmapTop = -bitmap.getHeight();


    }

    private void measureWidth(int widthMeasureSpec){
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if (widthMode==MeasureSpec.EXACTLY){
            width = widthSize;
        }else {
            if (bitmap!=null){
                width = (2*bitmap.getWidth());
            }
        }
    }

    private void measureHeight(int heightMeasureSpec){
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (heightMode==MeasureSpec.EXACTLY){
            height = heightSize;
        }else {
            if (bitmap!=null){
                height =(3*bitmap.getHeight());
            }
        }
    }

    private void initData(){
        fraction = 0;
        moveFraction = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(centerX,centerY);
        canvas.save();
        mPath.addCircle(0,bitmapTop*moveFraction+bitmap.getHeight()/2,radius*fraction+moveFraction*radius*0.8f, Path.Direction.CW);
        canvas.drawPath(mPath,mPaint);
       // canvas.drawCircle(0,bitmapTop*moveFraction+bitmap.getHeight()/2,radius*fraction+moveFraction*radius*0.8f,mPaint);
        if (bitmap!=null){
            canvas.drawBitmap(bitmap,-bitmap.getWidth()/2,bitmapTop*moveFraction,mPaint);
        }
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mPaint.setTextSize(24);
        drawTextOnCenter(canvas,mPaint,bottomText);
        mPath.reset();
    }

    private static Bitmap getBitmap(Context context,int vectorDrawableId) {
        Bitmap bitmap=null;
        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
            Drawable vectorDrawable = context.getDrawable(vectorDrawableId);
            bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                    vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            vectorDrawable.draw(canvas);
        }else {
            bitmap = BitmapFactory.decodeResource(context.getResources(), vectorDrawableId);
        }
        return bitmap;
    }

    private void drawTextOnCenter(Canvas canvas,Paint paint,String text){
        paint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        float top = fontMetrics.top;
        float bottom = fontMetrics.bottom;
        float textY =(top+bottom) + height-(float) (0.5*height)*moveFraction;
        canvas.drawText(text,0,textY,paint);
    }
}
