package com.app.diyzujian;


import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.recyclerview.R;
import com.app.util.getLocalXYfromView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/22.
 */
public class zhuanpanmenu extends ViewGroup {

    int centreX;
    int centreY;
    int mRadius = 350;
    int[] icos;//图标资源
    String[] TitleNames;//图标对应的标题名称
    Context context;
    List viewList = new ArrayList();
    float X;
    float Y;
    float lastX;
    float lastY;
    double bianyijiaodu;
    double bianyijiaodu_count=0;
    public zhuanpanmenu(Context context) {
        super(context);
        this.context = context;
    }


    public zhuanpanmenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

    }

    public zhuanpanmenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

    }

    /**
     * 设置布局的宽高，并策略menu item宽高
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int height = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        System.out.println("width:" + width + "height" + height);
        System.out.println("widthMode:" + widthMode + "heightMode" + heightMode);

        if(widthMode!=MeasureSpec.EXACTLY||heightMode!=MeasureSpec.EXACTLY)
        {
            width=getSuggestedMinimumWidth();
            width=width==0?MeasureSpec.getSize(widthMeasureSpec):width;

            height=getSuggestedMinimumHeight();
            height=height==0?MeasureSpec.getSize(heightMeasureSpec):height;

        }else
        {
            setMeasuredDimension(width, height);
        }
        setMeasuredDimension(width, height);
/*
        for (int i = 0; i < viewList.size(); i++) {
            View view = (View) viewList.get(i);
            // view.layout(50*i,50,100+(50*i),200);
            //view.measure(widthMode,heightMode);

            if (view != null) {
                measureChild(view, widthMeasureSpec, heightMeasureSpec);
            } else {
                System.out.println("view is null");
            }

        }*/

        // menu item数量
        final int count = getChildCount();
        // 迭代测量
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);

            if (child.getVisibility() == GONE) {
                continue;
            }

            // 计算menu item的尺寸；以及和设置好的模式，去对item进行测量
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        /*System.out.println("changed:" + changed);
        System.out.println("l:" + l);
        System.out.println("t:" + t);
        System.out.println("r:" + r);
        System.out.println("b:" + b);*/

        int jiaodu = 360 / (viewList.size() - 1);


        centreX = (r / 2);
        centreY = (b / 2);
        System.out.println("半径X--->" + centreX);
        System.out.println("半径Y--->" + centreY);
        for (int i = 0; i < viewList.size(); i++) {
            /*double x = mRadius * Math.cos(((90 + jiaodu * i) / (2 * Math.PI)));
            double y = mRadius * Math.sin(((90 + jiaodu * i) / (2 * Math.PI)));*/
            bianyijiaodu_count=bianyijiaodu_count+bianyijiaodu;

            double x = mRadius * Math.cos(((jiaodu * i+bianyijiaodu_count) * (2 * Math.PI)) / 360);
            double y = mRadius * Math.sin(((jiaodu * i+bianyijiaodu_count) * (2 * Math.PI)) / 360);
            // System.out.println("半径Y--->" + y);
            // System.out.println("半径X--->" + x);
            View view = (View) viewList.get(i);

            int Width = r / 2;
            int height = b / 2;

            int Cx = (int) x + centreX;
            int Cy = (int) (centreY + y);
            getLocalXYfromView getLocalXYfromView = new getLocalXYfromView(view, Cx, Cy);
            view.layout(getLocalXYfromView.getLValue(), getLocalXYfromView.getTValue(), getLocalXYfromView.getRValue(), getLocalXYfromView.getbValue());
            //view.layout((int) x + centreX, (int) (centreY + y), (int) (10 + x) + centreX, (int) (10 + centreY + y));
            TextView tv = (TextView) view.findViewById(R.id.TitleName);
            String str = tv.getText().toString();
            // tv.setText("X->" + (int) (x + centreX) + "| Y->" + (int) (centreY - y));
            //  view.layout(50 + (150 * i), 50, 250 + (150 * i), 500);

          /*  if (i == (viewList.size() - 1)) {
                view.layout(Width, height, (int) 10 + Width, 10 + height);

            }*/
            System.out.println("llll-->" + (int) x + Width);
            System.out.println("tttt-->" + (int) y + height);
            System.out.println("rrrr-->" + (int) (350 + x) + Width);
            System.out.println("bbbb-->" + (int) (500 + y) + height);

        }
        //  View view = (View) viewList.get(0);
        //  view.layout(r/2,b/2, r/2+50,b/2+50);
        //view.layout(0,0, 10,10);
        System.out.println("getPaddingLeft()" + getPaddingLeft());
        // System.out.println("getPaddingLeft()"+getm());

    }


    public void setIcoAndTextTitle(int[] ico, String[] TitleName) {
        icos = ico;
        TitleNames = TitleName;
        intDATA();
    }

    public void intDATA() {

        System.out.println("intData");
        int length = icos.length > TitleNames.length ? TitleNames.length : icos.length;
        for (int i = 0; i < length; i++) {
            //View view = View.inflate(context, R.layout.bank_menu_layout, null);
            LayoutInflater mInflater = LayoutInflater.from(getContext());
            View view = mInflater.inflate(R.layout.bank_menu_layout, this, false);
            TextView TitleName = (TextView) view.findViewById(R.id.TitleName);
            ImageView imageView = (ImageView) view.findViewById(R.id.icon);
            TitleName.setText(TitleNames[i]);
            imageView.setImageResource(icos[i]);
            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("sadasdas");
                }
            });
            viewList.add(view);
            // 添加view到容器中
            addView(view);
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        float X = 0;
        float Y = 0;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                System.out.println("ACTION_DOWN");
                X =  event.getX();
                Y =  event.getY();
                lastX =X;
                lastY=Y;
                break;

            case MotionEvent.ACTION_MOVE:
                System.out.println("ACTION_MOVE");
                //计算偏移量 且把新的坐标更新到上一次坐标
                X =  event.getX();
                Y =  event.getY();
                double bianyiliang_last =  Math.asin((lastY - centreY) / Math.hypot(lastX - centreX, lastY - centreY));

                double bianyiliang =  Math.asin((Y - centreY) / Math.hypot(X - centreX, Y - centreY));
                System.out.println("计算偏移量" + (bianyiliang-bianyiliang_last));

                lastX=X;
                lastY=Y;
                if(X>centreX)
                {
                    bianyijiaodu=bianyiliang-bianyiliang_last;

                }else
                {
                    bianyijiaodu=bianyiliang_last-bianyiliang;

                }
                requestLayout();
                break;

            case MotionEvent.ACTION_UP:
                //进行结束操作例如 再转一段时间
                System.out.println("ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                System.out.println("ACTION_CANCEL");
                break;
        }
       //return false;
        return super.dispatchTouchEvent(event);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {

        int action = ev.getAction();
        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                Log.e("TAG", "onInterceptTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("TAG", "onInterceptTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("TAG", "onInterceptTouchEvent ACTION_UP");
                break;

            default:
                break;
        }

        return super.onInterceptTouchEvent(ev);

    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {

        int action = event.getAction();

        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                Log.e("TAG", "onTouchEvent ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("TAG", "onTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("TAG", "onTouchEvent ACTION_UP");
                break;

            default:
                break;
        }

        return super.onTouchEvent(event);
    }


}
