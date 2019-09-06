package com.example.demo.practice.widget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;

public class WindmillView extends View {


    public int width;
    private int height;
    private int centerX;
    private int centerY;

    private Paint   bladesPaint;
    private Path   bladsPath;

    private Paint  handlePaint;

    private List<String> smallColorList;
    private List<String> bigColorList;

    private float angle = 0;

    public WindmillView(Context context) {
        super(context);
        init();
    }

    public WindmillView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WindmillView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        bladesPaint = new Paint();
        bladsPath = new Path();
        handlePaint = new Paint();
        smallColorList = new ArrayList<>();
        bigColorList = new ArrayList<>();

        smallColorList.add("#00e676");
        smallColorList.add("#c51162");
        smallColorList.add("#00b0ff");
        smallColorList.add("#d50000");
        smallColorList.add("#311b92");
        smallColorList.add("#69f0ae");

        bigColorList = new ArrayList<>();
        bigColorList.add("#ffee58");
        bigColorList.add("#ffee58");
        bigColorList.add("#81d4fa");
        bigColorList.add("#81d4fa");
        bigColorList.add("#ec407a");
        bigColorList.add("#ef5350");
        ValueAnimator animator = ValueAnimator.ofFloat(0,360f);
        animator.setDuration(5000);
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                angle = (float)animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();


    }

    public WindmillView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        centerX = width/2;
        centerY = height/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(centerX,centerY);
        canvas.save();
        canvas.drawColor(Color.BLACK);
        drawHandle(canvas,handlePaint);
        canvas.rotate(-angle);
        for (int a = 0;a<4;a++){
            drawSingleBlads(canvas,bladesPaint,bladsPath,smallColorList.get(a),bigColorList.get(a));
            canvas.rotate(90);
        }
        canvas.restore();
        canvas.drawCircle(0,0,32,handlePaint);




    }

    private void drawSingleBlads(Canvas canvas, Paint bladesPaint, Path path,String smallColor,String bigColor){
        bladesPaint.setStyle(Paint.Style.FILL);
        bladesPaint.setAntiAlias(true);
        bladesPaint.setColor(Color.parseColor(bigColor));
        path.moveTo(0,0);
        path.lineTo(0,-300);
        path.lineTo(300,-300);
        path.close();
        canvas.drawPath(path,bladesPaint);
        path.reset();
        bladesPaint.setColor(Color.parseColor(smallColor));
        path.lineTo(-150,-150);
        path.lineTo(0,-300);
        path.close();
        canvas.drawPath(path,bladesPaint);

    }

    private void drawHandle(Canvas canvas,Paint handlePaint){
        handlePaint.setColor(Color.parseColor("#f5f5f5"));
        handlePaint.setStyle(Paint.Style.FILL);
        handlePaint.setStrokeCap(Paint.Cap.ROUND);
        handlePaint.setStrokeWidth(32);
        handlePaint.setAntiAlias(true);
        canvas.drawLine(0,0,0,600,handlePaint);
    }
}
