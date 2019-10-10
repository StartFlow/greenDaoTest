package com.example.demo.practice.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class TitTok extends View {

    private Path mPath;
    private Paint paint;
    private Xfermode xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    private int width;
    private int height;
    private int offset = 20;
    private int radiu ;
    private int noteWidth;


    public TitTok(Context context) {
        this(context,null);
    }

    public TitTok(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TitTok(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        mPath = new Path();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.parseColor("#FF1753"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(72);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(width/2,height/2);
        canvas.save();
        mPath = getTitTokPath(0,0);
        canvas.drawPath(mPath,paint);
        int layer = canvas.saveLayer(null,null,Canvas.ALL_SAVE_FLAG);

        mPath = getTitTokPath(-offset,-offset);
        paint.setColor(Color.parseColor("#55FEFB"));
        canvas.drawPath(mPath,paint);

        paint.setColor(Color.WHITE);
        mPath = getTitTokPath(0,0);
        paint.setXfermode(xfermode);
        canvas.drawPath(mPath,paint);

        canvas.restoreToCount(layer);

    }

    private Path getTitTokPath(int offsetX, int offsetY){
        Path path = new Path();
        path.moveTo(0,-radiu);
        RectF rect = new RectF(-radiu,-radiu,radiu,radiu);
        rect.offset(offsetX,offsetY);
        path.addArc(rect,-90,-270);
        path.rLineTo(0,-radiu*3);
        path.rQuadTo(0,radiu,radiu,radiu);
        return path;
    }

}
