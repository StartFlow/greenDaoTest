package com.example.demo.practice.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.example.demo.practice.util.BlurBitmap;

public class BlurImageView extends AppCompatImageView {

    private final  String TAG = "BlurImageView";

    private Bitmap blurBitmap;
    private Paint mPaint;
    private float blurFraction = 0;
    private Rect rect;

    public BlurImageView(Context context) {
        this(context,null);
    }

    public BlurImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BlurImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        rect.set(0,0,getWidth(),getHeight());
        mPaint.setAlpha((int)(Math.abs(blurFraction*255)));
        canvas.drawBitmap(blurBitmap,null,rect,mPaint);
    }

    private void init(){
        Bitmap bitmap = ((BitmapDrawable)getDrawable()).getBitmap();
        if (bitmap==null)
            return;
        blurBitmap = BlurBitmap.blur(getContext(),bitmap);
        mPaint = new Paint();
        rect = new Rect();
    }

    public void setBlurFraction(float blurFraction) {
        this.blurFraction = blurFraction;
        invalidate();
    }
    /**
     * 获取某种颜色的半透明颜色值
     * @param a alpha degree [0~255]
     * @param color get Argb color
     */
    private int getArgb(int a,int color){
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(a,red,green,blue);
    }
}
