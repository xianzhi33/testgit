package com.app.diyzujian;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.app.recyclerview.R;

/**
 * Created by Administrator on 2016/12/9.
 */
public class arcMenuView extends ViewGroup {
    int percent_circle_gravity;
    int buttonMenu[];
    float Height_Size;
    int Height_MODE;
    float Width_Size;
    int Width_MODE;
    float radius;
    float PI= (float) Math.PI;

    public arcMenuView(Context context) {
        super(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public arcMenuView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        intData(context, attrs);

    }

    public arcMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        intData(context, attrs);

    }

    public arcMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        intData(context, attrs);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {



        int childViews = getChildCount();
        float jiaodu90=90/(childViews+1);
        float jiaodu180=180/(childViews+1);
        for (int i = 0; i < childViews; i++) {
            View v = getChildAt(i);
            float y= (float) (radius*Math.sin((double)(jiaodu90*(i+1)*PI/180)));
            float x= (float) (radius*Math.cos((double) (jiaodu90 * (i + 1) * PI / 180)));
            float view_width=v.getMeasuredWidth();
            float view_height=v.getMeasuredHeight();
            System.out.println("view_width:" + view_width);
            System.out.println("view_height:" + view_height);
            v.layout((int) (x-(view_width/2)), (int) (y-(view_height/2)),(int) (x+(view_width/2)) ,(int) ( y+(view_height/2)));
           /* System.out.println("changed:" + changed);
            System.out.println("l:" + (x-(view_width/2)));
            System.out.println("t:" + (y-(view_height/2)));
            System.out.println("r:" + (x+(view_width/2)));
            System.out.println("b:" +  ( y+(view_height/2)));*/

        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Width_MODE = MeasureSpec.getMode(widthMeasureSpec);
        Width_Size = MeasureSpec.getSize(widthMeasureSpec);
        Height_MODE = MeasureSpec.getMode(heightMeasureSpec);
        Height_Size = MeasureSpec.getSize(heightMeasureSpec);

        // 计算出所有的childView的宽和高
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int ChildrenViewMaxWidth = 0;
        int ChildrenViewMaxHeight = 0;
        int ChildItemCount = getChildCount();
        for (int i = 0; i < ChildItemCount; i++) {
            View view = getChildAt(i);
            ChildrenViewMaxWidth = Math.max(ChildrenViewMaxWidth, view.getMeasuredWidth());
            ChildrenViewMaxHeight = Math.max(ChildrenViewMaxHeight, view.getMeasuredHeight());

        }

        if (Width_MODE == MeasureSpec.EXACTLY || Height_MODE == MeasureSpec.EXACTLY) {
            setMeasuredDimension((int) Width_Size, (int) Height_Size);
        } else {
            setMeasuredDimension((int) (radius / 2) + (ChildrenViewMaxWidth / 2), (int) (radius / 2) + ChildrenViewMaxHeight);
        }


    }

    private void intData(Context context, AttributeSet attrs) {

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.arcMenuView);
        percent_circle_gravity = ta.getInt(R.styleable.arcMenuView_percent_circle_gravity, 0);
        radius = ta.getFloat(R.styleable.arcMenuView_radius, 3);
        System.out.println("aaa-->" + percent_circle_gravity);
    }

    public int[] getButtonMenu() {
        return buttonMenu;
    }

    public void setButtonMenu(int[] buttonMenu) {
        this.buttonMenu = buttonMenu;
    }

}
