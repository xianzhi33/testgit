package com.app.diyzujian;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.app.recyclerview.R;

/**
 * Created by Administrator on 2017/1/13.
 */
public class MyProgress extends View {
    private static final int DEFAULT_TEXT_SIZE = 10;
    private static final int DEFAULT_LINE_SIZE = 10;

    int line_background_color;//线背景颜色
    int line_font_progress; //线已经到达的颜色
    int line_weight = sp2px(DEFAULT_LINE_SIZE);//线条大小
    int text_color; //字体颜色
    int currentProgress = 0;
    private int mTextSize = sp2px(DEFAULT_TEXT_SIZE); //字体大小


    public MyProgress(Context context) {
        super(context);
    }

    public MyProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        intDATA(context, attrs);
    }

    public MyProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        intDATA(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        DrawLineBG(canvas);
        DrawLineProgress(canvas, currentProgress);
        DrawLineProgress_Current_penct(canvas, currentProgress);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
       // super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
     //   System.out.println("onMeasure:width--》"+"width-->"+width);

        int height = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//widthMode != MeasureSpec.EXACTLY ||
        if ( heightMode != MeasureSpec.EXACTLY) {


            Paint paint = new Paint();
            paint.setColor(text_color);
            paint.setTextSize(mTextSize);

            String ShowText = (float) (currentProgress / 10.0) + "%";
            Rect rect = new Rect();
            paint.getTextBounds(ShowText, 0, ShowText.length(), rect);
            float TextHeight = rect.height();
            float TextWidth = rect.width();

            height= (int) Math.max(TextHeight,line_weight)+getPaddingTop()+getPaddingBottom();
            width= (int) (TextWidth*3)+getPaddingRight()+getPaddingLeft();
            setMeasuredDimension(width, height);
            System.out.println("模糊测量onMeasure:height--》"+height+"width-->"+width);
        } else {

            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
        //System.out.println("onMeasure2:height--》"+height+"width-->"+width);

      //  setMeasuredDimension(900, height);
    }

    public void intDATA(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HorizontalProgress);
        line_background_color = typedArray.getColor(R.styleable.HorizontalProgress_line_background_color, Color.BLACK);
        line_font_progress = typedArray.getColor(R.styleable.HorizontalProgress_line_font_progress, Color.RED);
        line_weight = (int) typedArray.getDimension(R.styleable.HorizontalProgress_line_weight, line_weight);

        text_color = typedArray.getColor(R.styleable.HorizontalProgress_text_color, Color.GREEN);
        mTextSize = (int) typedArray.getDimension(R.styleable.HorizontalProgress_text_size, mTextSize);
        //currentProgress=30;
    }

    private void DrawLineBG(Canvas canvas) { //设置Progress 的线条的颜色

     /*   float startX = getPaddingLeft();
        float startY = (float) (getHeight() / 2.0);
        float stopX = getWidth() - getPaddingRight();
        float stopY = (float) (getHeight() / 2.0);
        // canvas.drawLine(0,(getHeight())/2-(line_weight/2),getWidth(),getHeight()/2+(line_weight/2));
        Paint paint = new Paint();
        paint.setColor(line_background_color);
        paint.setStrokeWidth(line_weight);
        canvas.drawLine(startX, startY, stopX, stopY, paint);*/

        Paint paint1 = new Paint();
        paint1.setColor(text_color);
        paint1.setTextSize(mTextSize);
        String ShowText = (float) (currentProgress / 10.0) + "%";
        Rect rect = new Rect();
        paint1.getTextBounds(ShowText, 0, ShowText.length(), rect);
        float TextHeight = rect.height();
        float TextWidth = rect.width();

        float startX = (getWidth() - getPaddingRight() - getPaddingLeft()) * ((float) (currentProgress / 1000.0)) + getPaddingLeft() + TextWidth;
        float startY = (float) ((getHeight()-getPaddingBottom()-getPaddingTop()) / 2.0)+getPaddingTop();
        float stopX = getWidth() - getPaddingRight();
        float stopY = (float) ((getHeight()-getPaddingBottom()-getPaddingTop()) / 2.0)+getPaddingTop();
        // canvas.drawLine(0,(getHeight())/2-(line_weight/2),getWidth(),getHeight()/2+(line_weight/2));
        Paint paint = new Paint();
        paint.setColor(line_background_color);
        paint.setStrokeWidth(line_weight);
        canvas.drawLine(startX, startY, stopX, stopY, paint);

        System.out.println("startX" + startX);
        System.out.println("stopX"+stopX);
    }

    private void DrawLineProgress(Canvas canvas, int currentProgress) {
        float startX = getPaddingLeft();
        float startY = (float) ((getHeight()-getPaddingBottom()-getPaddingTop()) / 2.0)+getPaddingTop();
        //float stopX =(getWidth() - getPaddingRight()-getPaddingLeft())*(currentProgress/100);

        float stopX = (getWidth() - getPaddingRight() - getPaddingLeft()) * ((float) (currentProgress / 1000.0)) + getPaddingLeft();
        float stopY = (float) ((getHeight()-getPaddingBottom()-getPaddingTop()) / 2.0)+getPaddingTop();
        // canvas.drawLine(0,(getHeight())/2-(line_weight/2),getWidth(),getHeight()/2+(line_weight/2));
        Paint paint = new Paint();
        paint.setColor(line_font_progress);
        paint.setStrokeWidth(line_weight);
        canvas.drawLine(startX, startY, stopX, stopY, paint);

    }

    private void DrawLineProgress_Current_penct(Canvas canvas, int currentProgress) {
        float startX = getPaddingLeft();
        float startY = (float) ((getHeight()-getPaddingBottom()-getPaddingTop()) / 2.0)+getPaddingTop();
        //float stopX =(getWidth() - getPaddingRight()-getPaddingLeft())*(currentProgress/100);

        float stopX = (getWidth() - getPaddingRight() - getPaddingLeft()) * ((float) (currentProgress / 1000.0)) + getPaddingLeft();
        float stopY =(float) ((getHeight()-getPaddingBottom()-getPaddingTop()) / 2.0)+getPaddingTop();
        Paint paint = new Paint();
        paint.setColor(text_color);
        paint.setTextSize(mTextSize);

        String ShowText = (float) (currentProgress / 10.0) + "%";
        Rect rect = new Rect();
        paint.getTextBounds(ShowText, 0, ShowText.length(), rect);
        float TextHeight = rect.height();
        float TextWidth = rect.width();
        //  System.out.println("currentProgress");

    /*    System.out.println("stopX"+stopX);
        System.out.println("TextWidth"+TextWidth);
        System.out.println("getWidth()"+getWidth());
        System.out.println("getRight()"+getPaddingRight());*/

        if ((stopX + TextWidth) > ((float) getWidth() - (float) getPaddingRight())) {
            stopX = (getWidth() - getPaddingRight() - TextWidth);
            // System.out.println("stopX---"+stopX);

        }

        stopY= (float) (stopY+(TextHeight / 2.0));
        //System.out.println("stopX" + stopX);
       // canvas.drawText((float) (currentProgress / 10.0) + "%", stopX, (float) ((float) (getHeight() / 2.0) + (TextHeight / 2.0)), paint);
         canvas.drawText((float) (currentProgress / 10.0) + "%", stopX, stopY, paint);

    }


    public int getCurrentProgress() {
        return currentProgress;
    }

    public void setCurrentProgress(int currentProgress) {
        if (currentProgress > 1000) currentProgress = 1000;
        if (currentProgress < 0) currentProgress = 0;
        this.currentProgress = currentProgress;
        invalidate();

    }

    /**
     * dp 2 px
     *
     * @param dpVal
     */
    protected int dp2px(int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, getResources().getDisplayMetrics());
    }

    /**
     * sp 2 px
     *
     * @param spVal
     * @return
     */
    protected int sp2px(int spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, getResources().getDisplayMetrics());

    }
}
