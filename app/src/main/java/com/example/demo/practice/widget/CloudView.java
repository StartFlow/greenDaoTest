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

public class CloudView extends View {

    static final int CLOUDMOVE_LEFT = 101;
    static final int CLOUDMOVE_RIGHT = 102;

    private Paint bigCirclePaint;
    private Paint smallCirclePaint;
    private int width;
    private int height;
    private int centerX;
    private int centerY;
    private final int outerRadius = 128;
    private final int innerRadius = 120;
    private final int BlurRadius = 120;
    private Cloud rightCloud;
    private Cloud leftCloud;
    public CloudView(Context context) {
        super(context);
        init();
    }

    public CloudView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CloudView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CloudView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

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
        bigCirclePaint = new Paint();
        bigCirclePaint.setColor(Color.parseColor("#e6e8db"));
        bigCirclePaint.setAntiAlias(true);
        bigCirclePaint.setMaskFilter(new BlurMaskFilter(BlurRadius,BlurMaskFilter.Blur.SOLID));

        smallCirclePaint = new Paint();
        Shader shader = new LinearGradient(centerX-innerRadius,centerY+innerRadius,centerX+innerRadius,centerY-innerRadius, Color.parseColor("#42a5f5"), Color.parseColor("#e3f2fd"), Shader.TileMode.CLAMP);
        smallCirclePaint.setShader(shader);
        smallCirclePaint.setAntiAlias(true);

        leftCloud = new Cloud(0,-outerRadius/4,48,CLOUDMOVE_LEFT);
        rightCloud = new Cloud(outerRadius/3,-outerRadius/3,32,CLOUDMOVE_RIGHT);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(centerX,centerY);
        canvas.drawColor(Color.BLACK);
        canvas.drawCircle(0,0,outerRadius,bigCirclePaint);
        canvas.drawCircle(0,0,innerRadius,smallCirclePaint);
        leftCloud.drawCloud(canvas);
        rightCloud.drawCloud(canvas);
        postInvalidateDelayed(10);
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
