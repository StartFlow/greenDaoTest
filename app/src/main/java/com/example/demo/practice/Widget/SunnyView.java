package com.example.demo.practice.Widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

public class SunnyView extends View {


    static final int CLOUDMOVE_LEFT = 101;
    static final int CLOUDMOVE_RIGHT = 102;

    public int sunnayRadiu = 128;
    public int width;
    private int height;
    private int centerX;
    private int centerY;

    private Cloud cloud;

    private int smallCircleRadius = 4;
    private int bigCircleRadius = 16;

    private Paint smallCirclePaint;
    private Paint bigCirclePaint;
    private Paint sunnyPaint;
    private int State;
    private final static int READY_1 = 1101;
    private final static int READY_2 = 1102;
    private final static int READY_3 = 1103;
    private final static int SHOWING_ = 1104;

    private float rotateAngle  = 0;


    private int innerCircleArcSatrtAngle = 75;
    private int outerCircleArcSatrtAngle = -60;
    private int innerCircleArcSweepAngle = 275;
    private int outerCircleArcSweepAngle = 120;
    private AnimatorSet set;
    private Paint arcPaint;
    private RectF sunRectF;

    private ValueAnimator BigCircleZoomAnima;
    private Shader sunShader;
    private MaskFilter sunFilter;

    private float scale = 1.0f;



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        centerX = width/2;
        centerY = height/2;
    }

    private void init(){

        setLayerType(LAYER_TYPE_SOFTWARE, null);
        set = new AnimatorSet();
        smallCirclePaint = new Paint();
        smallCirclePaint.setColor(Color.WHITE);

        sunRectF = new RectF();

        cloud = new Cloud(24,0,24,CLOUDMOVE_LEFT);

        sunShader = new RadialGradient(0,0,sunnayRadiu,Color.parseColor("#fff176"),Color.parseColor("#fbc02d"),Shader.TileMode.CLAMP);

        arcPaint = new Paint();
        arcPaint.setColor(Color.parseColor("#ffd600"));
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setStrokeCap(Paint.Cap.ROUND);
        arcPaint.setAntiAlias(true);
        arcPaint.setStrokeWidth(24);

        bigCirclePaint = new Paint();
        bigCirclePaint.setColor(Color.parseColor("#ffee58"));
        bigCirclePaint.setAntiAlias(true);

        sunnyPaint = new Paint();
        sunnyPaint.setAntiAlias(true);
        sunnyPaint.setColor(Color.parseColor("#ffee58"));
        sunFilter =  new BlurMaskFilter(sunnayRadiu,BlurMaskFilter.Blur.SOLID);
        sunnyPaint.setMaskFilter(sunFilter);

        BigCircleZoomAnima = ValueAnimator.ofInt(sunnayRadiu);
        BigCircleZoomAnima.setDuration(500);
        BigCircleZoomAnima.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                bigCircleRadius = (int)animation.getAnimatedValue();
                invalidate();
            }
        });

        ValueAnimator smallCircleZoomAnima = ValueAnimator.ofInt(sunnayRadiu-8).setDuration(300);
        smallCircleZoomAnima.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                smallCircleRadius = (int)animation.getAnimatedValue();
                invalidate();
            }
        });


        smallCircleZoomAnima.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                    State = READY_3;
                    set.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        BigCircleZoomAnima.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                        smallCircleZoomAnima.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        ValueAnimator arcAnima1 = ValueAnimator.ofInt(180);
        arcAnima1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                innerCircleArcSweepAngle = (int)animation.getAnimatedValue();
            }
        });

        ValueAnimator arcAnima2 = ValueAnimator.ofInt(275);
        arcAnima2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                outerCircleArcSweepAngle = (int)animation.getAnimatedValue();
                invalidate();
            }
        });
        ValueAnimator arc = ValueAnimator.ofFloat(0,359.9f);
        arc.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                rotateAngle = (float)animation.getAnimatedValue();
            }
        });
        set.playTogether(arcAnima1,arcAnima2,arc);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                State = SHOWING_;
                rotateAngle = 0;
                //postInvalidateDelayed(100);
                ValueAnimator valueAnimator = getSunScaleAnima().setDuration(500);
                valueAnimator.setInterpolator(new BounceInterpolator());
                valueAnimator.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.setDuration(1000);
    }

    private ValueAnimator getSunScaleAnima(){
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1f);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                scale = (float)animation.getAnimatedValue();
                invalidate();
            }
        });
        return valueAnimator;
    }

    public SunnyView(Context context) {
        super(context);
        init();
    }

    public SunnyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SunnyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void initData(){
        State = READY_1;
        smallCircleRadius = 4;
        bigCircleRadius = 16;

    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        canvas.translate(centerX,centerY);
        canvas.save();
        switch (State){
            case READY_1:
                drawBigCircle(canvas);
                drawSmallCircle(canvas,smallCircleRadius);
                break;
            case READY_3:
                canvas.rotate(rotateAngle);
                arcPaint.setStrokeWidth(16);
                canvas.drawArc(-sunnayRadiu,-sunnayRadiu,sunnayRadiu,sunnayRadiu,outerCircleArcSatrtAngle,outerCircleArcSweepAngle,false,arcPaint);
                arcPaint.setStrokeWidth(10);
                canvas.drawArc(-sunnayRadiu/2,-sunnayRadiu/2,sunnayRadiu/2,sunnayRadiu/2,innerCircleArcSatrtAngle,-innerCircleArcSweepAngle,false,arcPaint);
                break;
            case SHOWING_:
                drawSun(canvas);
                cloud.drawCloud(canvas);
                break;
        }
    }

    public void start(){
        initData();
        BigCircleZoomAnima.start();
    }

    private void drawSmallCircle(Canvas canvas,float radius){
        canvas.drawCircle(0,0,radius,smallCirclePaint);
    }

    private void drawBigCircle(Canvas canvas){
        canvas.drawCircle(0,0,bigCircleRadius,bigCirclePaint);
    }

    private void drawSun(Canvas canvas){
        sunRectF.left = -sunnayRadiu;
        sunRectF.top = -sunnayRadiu;
        sunRectF.right = sunnayRadiu;
        sunRectF.bottom = sunnayRadiu;
        sunnyPaint.setShader(null);
        canvas.rotate(rotateAngle);
        canvas.scale(scale,scale);
        rotateAngle++;
        if (rotateAngle==360){
            rotateAngle=0;
        }
        canvas.drawRect(sunRectF,sunnyPaint);
        canvas.rotate(45);
        canvas.drawRect(sunRectF,sunnyPaint);
        sunnyPaint.setShader(sunShader);
        canvas.drawCircle(0,0,sunnayRadiu,sunnyPaint);
        postInvalidateDelayed(100);
        canvas.restore();
    }

    class Cloud {
        //左边最顶点为坐标基点
        private float locationX;
        private float locationY;
        private int range; //左右摆动的范围
        final float  cloudWidth = 96;
        final float  cloudBottomHeight = 32;
        final float leftCircleRadius = 24;
        final float rightCircleRadius = 16;
        final int cloudBottomRoundRadius = 16;
        private Paint cloudPaint;
        private int Direction;
        private float moveDistance = 0;
       // private Path shadowPath;
        //private Paint shadowPaint;


        public Cloud(int locationX, int locationY, int range,int Direction) {
            this.locationX = locationX;
            this.locationY = locationY;
            this.range = range;
            this.Direction = Direction;
            init();
        }

        private void init(){
            cloudPaint = new Paint();
            cloudPaint.setColor(Color.parseColor("#e3f2fd"));
            cloudPaint.setAntiAlias(true);
            Shader shader = new RadialGradient((float)(locationX+cloudWidth/2),(float)(locationY+cloudWidth/2),24,Color.GRAY,Color.BLACK,Shader.TileMode.CLAMP);
//            shadowPaint = new Paint();
//            shadowPaint.setShader(shader);
//            shadowPath = new Path();
//            shadowPath.moveTo(locationX,locationY);
//            shadowPath.lineTo(locationX+cloudWidth,locationY);
//            shadowPath.lineTo(locationX+cloudWidth,locationY+cloudWidth);
//            shadowPath.close();
            // cloudPaint.setMaskFilter(new BlurMaskFilter(cloudWidth,BlurMaskFilter.Blur.SOLID));

        }

        private void drawLeftCircle(Canvas canvas){
            canvas.drawCircle(locationX+cloudWidth/3,locationY-cloudBottomHeight,leftCircleRadius,cloudPaint);
        }
        private void drawBottom(Canvas canvas){
            RectF bottomRectF = new RectF(locationX,locationY-cloudBottomHeight,locationX+cloudWidth,locationY);
            canvas.drawRoundRect(bottomRectF,cloudBottomRoundRadius,cloudBottomRoundRadius,cloudPaint);
        }

        private void drawRightCircle(Canvas canvas){
            canvas.drawCircle(locationX+(cloudWidth/3*2),locationY-(cloudBottomHeight/5*4),rightCircleRadius,cloudPaint);
        }

        public void drawCloud(Canvas canvas){
            drawBottom(canvas);
            drawLeftCircle(canvas);
            drawRightCircle(canvas);
          //  canvas.drawPath(shadowPath,shadowPaint);
            switch (Direction){
                case CLOUDMOVE_LEFT:
                    locationX-=0.5;
                    moveDistance+=0.5;
                    if (moveDistance==range){
                        Direction = CLOUDMOVE_RIGHT;
                        moveDistance = 0;
                    }
                    break;
                case CLOUDMOVE_RIGHT:
                    locationX+=0.5;
                    moveDistance+=0.5;
                    if (moveDistance==range){
                        Direction = CLOUDMOVE_LEFT;
                        moveDistance = 0;
                    }
                    break;
            }
        }
    }
}
