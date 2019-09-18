package com.example.demo.practice.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.example.demo.practice.R;

public class WaveBall extends View {

    private float width ;
    private float height ;
    private Paint wavePaint;
    private Paint textPaint;

    private float centerX;
    private float centerY;

    private int color;
    private String text;

    private float radius ;
    private float percent = 0;
    private Bitmap circleBitmap;

    private Xfermode mainXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    private Xfermode textXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP);

    public WaveBall(Context context) {
        this(context,null);
    }

    public WaveBall(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WaveBall(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }


    public void setText(String text) {
        this.text = text;
        invalidate();
    }

    public String getText() {
        return text;
    }

    private void init(Context context, AttributeSet attr){
        TypedArray array = context.obtainStyledAttributes(attr,R.styleable.WaveBall);
        color = array.getColor(R.styleable.WaveBall_Wave_color,Color.parseColor("#00bcd4"));
        text = array.getString(R.styleable.WaveBall_Wave_text);
        if (TextUtils.isEmpty(text)){
            text = "贴";
        }

        array.recycle();

        setLayerType(LAYER_TYPE_SOFTWARE,null);
        wavePaint = new Paint();
        wavePaint.setAntiAlias(true);
        wavePaint.setDither(true);
        wavePaint.setColor(color);
        wavePaint.setStyle(Paint.Style.FILL);

        textPaint = new Paint();
        textPaint.setDither(true);
        textPaint.setAntiAlias(true);
        textPaint.setColor(color);

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
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        }
        setMeasuredDimension((int)width,(int)height);
        centerX = width/2;
        centerY = height/2;
        radius = width<height?width/2:height/2;
        if (width>0&&height>0&& circleBitmap==null){
            circleBitmap  = getCircleBitmap((int)width,(int)height);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(centerX,centerY);
        canvas.save();
        //画底部主颜色的文字
        textPaint.setColor(color);
        drawTextOnCenter(canvas,textPaint,text);

        //离屏缓存  相当于新建图层
        int flgs = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);

        //画赛贝尔曲线
        Path path = getSeibelPath();
        canvas.drawPath(path, wavePaint);
        //画圆  使用BitMap来画才能得到百度经常看到SRC_IN的效果  不知道为什么？？ 否则直接canvas绘制不起作用!!!!
        wavePaint.setXfermode(mainXfermode);
        canvas.drawBitmap(circleBitmap,-width/2,-height/2,wavePaint);


        //画字
        textPaint.setColor(Color.WHITE);
        textPaint.setXfermode(textXfermode);
        drawTextOnCenter(canvas, textPaint, text);
        //取消Xfermode
        textPaint.setXfermode(null);
        wavePaint.setXfermode(null);

        canvas.restoreToCount(flgs);
    }

    private Bitmap getCircleBitmap(int width, int height){
        Bitmap bitmap=Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
		//bitmap.eraseColor(Color.TRANSPARENT);//填充颜色
        Canvas canvas=new Canvas(bitmap);
        canvas.translate(centerX,centerY);
        canvas.drawCircle(0, 0, radius, wavePaint);
        return bitmap;
    }

    private void drawTextOnCenter(Canvas canvas,Paint paint,String text){
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(radius);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float top = fontMetrics.top;
        float bottom = fontMetrics.bottom;
        float textY = -(top+ bottom)/2;
        canvas.drawText(text,0,textY,paint);

    }

    private Path getSeibelPath() {
        Path path = new Path();
        float x = -radius*3;
        x += percent * 2*radius;
        path.moveTo(x, 0);
        //二阶赛贝尔曲线使用相对控制点  二阶赛贝尔曲线  path当前点 控制点  结束点
        path.rQuadTo(radius / 2, radius/2 , radius , 0);
        path.rQuadTo(radius / 2, -radius/2 , radius , 0);

        path.rQuadTo(radius / 2, radius/2 , radius , 0);
        path.rQuadTo(radius / 2, -radius /2, radius , 0);

        path.lineTo(x + 4*radius, radius);
        path.lineTo(x, width/2);
        path.close();
        return path;
    }
}
