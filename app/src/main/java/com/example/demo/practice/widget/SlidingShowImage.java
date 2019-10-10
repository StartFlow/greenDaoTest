package com.example.demo.practice.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.demo.practice.R;

public class SlidingShowImage extends View {
    private final String TAG = "SlidingShowImage";
    private Bitmap topBitmap;
    private Bitmap bottomBitmap;
    private Path mPath;
    private Paint mPaint;
    private float canvasRaius = 0;

    private int width;
    private int height;

    private Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);

    public void setPercent(float percent) {
        this.percent = percent;
        invalidate();
    }

    private float percent = 0;


    public SlidingShowImage(Context context) {
        this(context,null);
    }

    public SlidingShowImage(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }
    public SlidingShowImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);

    }

    private void init(Context context,AttributeSet attrs){
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SlidingShowImage);
        int topResId = array.getResourceId(R.styleable.SlidingShowImage_top_src,R.drawable.sanji);
        int bottomResId = array.getResourceId(R.styleable.SlidingShowImage_bottom_src,R.drawable.sanji);
        array.recycle();
        topBitmap = getBitmap(context,topResId);
        bottomBitmap = getBitmap(context,bottomResId);
        if (bottomBitmap==null||topBitmap==null){
            throw new  IllegalArgumentException("bottom src and top src must be not null");
        }
        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bottomBitmap,0,0,mPaint);
        int layer = canvas.saveLayer(null,null,Canvas.ALL_SAVE_FLAG);
        canvas.drawCircle(0,0,percent*canvasRaius,mPaint);
        canvas.drawCircle(width,height,percent*canvasRaius,mPaint);
        mPaint.setXfermode(xfermode);
        canvas.drawBitmap(topBitmap,0,0,mPaint);
        canvas.restoreToCount(layer);
        mPath.reset();
        mPaint.setXfermode(null);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if (widthMode==MeasureSpec.EXACTLY){
            width = widthSize;
        }else {
            width = Math.max(bottomBitmap.getWidth(),topBitmap.getWidth());
        }

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (heightMode ==MeasureSpec.EXACTLY){
            height = heightSize;
        }else {
            height = Math.max(topBitmap.getHeight(),bottomBitmap.getHeight());
        }
        setMeasuredDimension(width,height);
        int height = Math.max(topBitmap.getHeight(),bottomBitmap.getHeight());
        int width = Math.max(topBitmap.getWidth(),bottomBitmap.getWidth());
        canvasRaius = Math.max(height,width);
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
}
