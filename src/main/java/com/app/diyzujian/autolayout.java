package com.app.diyzujian;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/28.
 */
public class autolayout extends ViewGroup {

    List Row_Height = new ArrayList();

    public autolayout(Context context) {
        super(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public autolayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public autolayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public autolayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int childCount = getChildCount();
        int childCountWidth = 0;
        int childwidthIndex = 0;
        int childhightIndex = 0;
        int getHeightIndex = 0;
        for (int i = 0; i < childCount; i++) {

            View view = getChildAt(i);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("autolayout");
                }
            });
            // 得到child的lp
          //  MarginLayoutParams lp = (MarginLayoutParams) view.getLayoutParams();

            MarginLayoutParams lp = null;
            lp = new MarginLayoutParams(android.widget.RelativeLayout.LayoutParams.WRAP_CONTENT,android.widget.RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(5,5,5,5);
            int width_lenght = childwidthIndex + view.getMeasuredWidth()+lp.leftMargin+lp.rightMargin;
            if (width_lenght > r) {

                childwidthIndex = lp.leftMargin;
                // childhightIndex += view.getHeight();
                childhightIndex += (int) Row_Height.get(getHeightIndex);
                getHeightIndex = getHeightIndex + 1;
                // System.out.println("index"+getHeightIndex+"getHeightIndex"+(int)Row_Height.get(getHeightIndex));
            }
            if(i==0) childwidthIndex=lp.leftMargin;
            view.layout(childwidthIndex, childhightIndex+lp.topMargin, childwidthIndex + view.getMeasuredWidth(), childhightIndex+lp.bottomMargin + view.getMeasuredHeight());
            childwidthIndex += view.getMeasuredWidth()+lp.leftMargin+lp.rightMargin;

        }

    }

    /**
     * 设置布局的宽高，并策略menu item宽高
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int hight = MeasureSpec.getSize(heightMeasureSpec);
        int width_mode = MeasureSpec.getMode(widthMeasureSpec);
        int height_mode = MeasureSpec.getMode(heightMeasureSpec);
        int childCount = getChildCount();
        int childCountWidth = 0;
        int childHeight = 0;
        int childwidthIndex = 0;
        int MaxHeight = 0;
        int CountHeight = 0;
        System.out.println("childCount->"+childCount);
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
           // MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();


            MarginLayoutParams lp = null;
            lp = new MarginLayoutParams(android.widget.RelativeLayout.LayoutParams.WRAP_CONTENT,android.widget.RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(5,5,5,5);
            measureChild(childView, heightMeasureSpec, widthMeasureSpec);
            childHeight = childView.getMeasuredHeight();
            childCountWidth += childView.getMeasuredWidth()+lp.rightMargin+lp.leftMargin;
            childwidthIndex += childView.getMeasuredWidth()+lp.rightMargin+lp.leftMargin;
            if (childwidthIndex >= width ) {
                  System.out.println("下一行" + i + "MaxHeight" + MaxHeight);
                childwidthIndex = childView.getMeasuredWidth()+lp.rightMargin+lp.leftMargin;
                CountHeight += MaxHeight;
                Row_Height.add(MaxHeight);
                MaxHeight = 0;
                MaxHeight = Math.max(MaxHeight, childView.getMeasuredHeight()+lp.topMargin+lp.bottomMargin);

            } else {
                MaxHeight = Math.max(MaxHeight, childView.getMeasuredHeight()+lp.topMargin+lp.bottomMargin);
            }

            if ((i == (childCount - 1))) {
                System.out.println("下一行" + i + "MaxHeight" + MaxHeight);
                childwidthIndex = childView.getMeasuredWidth()+lp.rightMargin+lp.leftMargin;
                CountHeight += MaxHeight;
                Row_Height.add(MaxHeight);
                MaxHeight = 0;
                MaxHeight = Math.max(MaxHeight, childView.getMeasuredHeight()+lp.topMargin+lp.bottomMargin);

            }
        }
        if (width_mode == MeasureSpec.EXACTLY && height_mode == MeasureSpec.EXACTLY) {
            setMeasuredDimension(width, hight);
        } else {
            int measurewidth = 0;
            int measureheight = 0;
            if (childCountWidth > width) {
                measurewidth = width;
            } else {
                measurewidth = childCountWidth;
            }

            // measureheight = childCountHeightFoot * childHeight;
            setMeasuredDimension(measurewidth, CountHeight);
        }
    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs)
    {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
