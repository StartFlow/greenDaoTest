package com.example.demo.practice.widget;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnowyView extends View {


    private Paint bigCirclePaint;
    private Paint smallCirclePaint;
    private Paint snowManHandPaint;
    private Paint snowManBodyPaint;
    private int width;
    private int height;
    private int centerX;
    private int centerY;
    private final int outerRadius = 128;
    private final int innerRadius = 120;
    private final int BlurRadius = 120;
    private RectF snowManHandRect;

    private final int snowManHeadRadius = 24;
    private final int snowManBodyRadius = 36;

    private List<SnowFlower> snowFlowers;

    public SnowyView(Context context) {
        super(context);
        init();
    }

    public SnowyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SnowyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init(){
        //关闭硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        bigCirclePaint = new Paint();
        bigCirclePaint.setColor(Color.parseColor("#e6e8db"));
        bigCirclePaint.setAntiAlias(true);
        bigCirclePaint.setMaskFilter(new BlurMaskFilter(BlurRadius,BlurMaskFilter.Blur.SOLID));

        smallCirclePaint = new Paint();
        Shader shader = new LinearGradient(centerX-innerRadius,centerY-innerRadius,centerX+innerRadius,centerY+innerRadius, Color.parseColor("#e0e2e5"), Color.parseColor("#758595"), Shader.TileMode.CLAMP);
        smallCirclePaint.setShader(shader);
        smallCirclePaint.setAntiAlias(true);

        snowManHandPaint = new Paint();
        snowManHandPaint.setColor(Color.parseColor("#758595"));
        snowManHandPaint.setStrokeWidth(8);
        snowManHandPaint.setStyle(Paint.Style.STROKE);
        snowManHandRect = new RectF(-72,0,72,72);

        snowManBodyPaint = new Paint();
        snowManBodyPaint.setColor(Color.WHITE);
        snowManBodyPaint.setAntiAlias(true);
        snowManBodyPaint.setMaskFilter(new BlurMaskFilter(snowManHeadRadius,BlurMaskFilter.Blur.SOLID));

        Builder builder = new Builder();
        snowFlowers = builder.getFlowers(120,512);


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
        canvas.save();
        canvas.translate(centerX,centerY);
        canvas.drawColor(Color.BLACK);
        canvas.drawCircle(0,0,outerRadius,bigCirclePaint);
        canvas.drawCircle(0,0,innerRadius,smallCirclePaint);
        canvas.drawArc(snowManHandRect,35,120,false,snowManHandPaint);
        canvas.drawCircle(0,30,snowManHeadRadius,snowManBodyPaint);
        canvas.drawCircle(-6,80,snowManBodyRadius,snowManBodyPaint);
        canvas.restore();
        canvas.translate(centerX-2*outerRadius,centerY-2*outerRadius);
        for (SnowFlower snowFlower:snowFlowers){
            snowFlower.drawSnowFlower(canvas);
        }
        postInvalidateDelayed(10);
    }


    class SnowFlower {
       float radius;
       float speed;
       float angle;
       int range;
       private float locationX;
       private float locationY;
       private float nowSpeed = 0;
       private Random randomUtil;
       private Paint flowerPaint;

       private void init(){

           flowerPaint = new Paint();
           flowerPaint.setColor(Color.WHITE);
           flowerPaint.setMaskFilter(new BlurMaskFilter(12,BlurMaskFilter.Blur.SOLID));
           flowerPaint.setAntiAlias(true);
           flowerPaint.setStyle(Paint.Style.FILL);
           flowerPaint.setAlpha(120);

           locationX = randomUtil.nextInt(range);
           locationY =  randomUtil.nextInt(range);
       }

        public SnowFlower(int range) {
            randomUtil = new Random();
            this.range = range;
            this.radius = getSize();
            this.speed = getSpeed();
            this.angle = getAngle();
            init();

        }


        private float getSpeed(){
            nowSpeed = randomUtil.nextFloat()+1;
            return nowSpeed;
        }

        private float getSpeedX(){
            return (float) (nowSpeed*Math.sin(angle));
        }

        private float getSpeedY(){
            return (float)(nowSpeed*Math.cos(angle));
        }

        private float getAngle(){
            int radian = (int)((Math.random()*90));
            angle =(int) Math.toRadians(radian) ;
            return angle;
        }

        private float getSize(){
            radius = randomUtil.nextInt(8);
            return radius;
        }

        private void moveSonwX(){
           locationX = locationX+getSpeedX();
        }

        private void moveSnowY(){
           locationY = locationY+getSpeedY();
        }


        private void reSet(){
            this.radius = getSize();
            this.speed = getSpeed();
            this.angle = getAngle();
            this.nowSpeed = getSpeed();
            locationX = (int)(range-(Math.random()*range));
            locationY =  0;
        }



        private void drawSnowFlower(Canvas canvas){
           moveSonwX();
           moveSnowY();
           if (locationX>range||locationY>range){
               reSet();
           }
           canvas.drawCircle(locationX,locationY,radius,flowerPaint);
        }


    }

    class Builder {

        public Builder() {

        }


        public List<SnowFlower> getFlowers(int count,int range){
            List<SnowFlower> list = new ArrayList<>();
            for (int a = 0;a<count;a++){
                SnowFlower snowFlower = new SnowFlower(range);
                list.add(snowFlower);
            }

            return list;
        }
    }


}
