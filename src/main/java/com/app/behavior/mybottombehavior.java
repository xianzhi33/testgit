package com.app.behavior;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2017/3/29.
 */
public class mybottombehavior extends CoordinatorLayout.Behavior<View> {

    public mybottombehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //确定所提供的子视图是否有另一个特定的同级视图作为布局从属。
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
//这个方法是说明这个子控件是依赖AppBarLayout的
        return dependency instanceof AppBarLayout;
       // return dependency instanceof RecyclerView;
    }

    //用于响应从属布局的变化
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {

        float translationY = Math.abs(dependency.getTop());//获取更随布局的顶部位置

        System.out.println("dependency:"+dependency.getTop());
        System.out.println("translationY:"+translationY);

        child.setTranslationY(translationY);
        return true;
    }

    @Override
    public boolean onTouchEvent(CoordinatorLayout parent, View child, MotionEvent ev) {

        System.out.println("MotionEvent:"+ev.getAction());
        return super.onTouchEvent(parent, child, ev);


    }
}