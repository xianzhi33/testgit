package com.app.diyzujian;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.Toast;

import com.app.recyclerview.R;

/**
 * Created by Administrator on 2016/12/9.
 */
public class arcMenuView_relayout extends ViewGroup {
    int percent_circle_gravity;
    int buttonMenu[];
    float Height_Size;
    int Height_MODE;
    float Width_Size;
    int Width_MODE;
    float radius;
    float PI = (float) Math.PI;
    boolean FLAG_OPEN = false;
    Animation rotateAnimation_405;
    Animation rotateAnimation_405_Nor;
    ScaleAnimation scaleAnimation;//缩放动画
    AnimationSet ScaleanimationSet;//缩放动画
    public arcMenuView_relayout(Context context) {
        super(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public arcMenuView_relayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        intData(context, attrs);

    }

    public arcMenuView_relayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        intData(context, attrs);

    }

    public arcMenuView_relayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        intAnimation();
        intData(context, attrs);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        System.out.println("This is Step by onLayout");
        final int childViews = getChildCount();
        float jiaodu90 = 90 / (childViews);
        View v_0 = getChildAt(0);

        if (percent_circle_gravity == 0) {//左上
            v_0.layout(0, 0, v_0.getMeasuredWidth(), v_0.getMeasuredHeight());
            for (int i = 1; i < childViews; i++) {
                View v = getChildAt(i);
                float y = (float) (radius * Math.sin((double) (jiaodu90 * (i) * PI / 180)));
                float x = (float) (radius * Math.cos((double) (jiaodu90 * (i) * PI / 180)));
                float view_width = v.getMeasuredWidth();
                float view_height = v.getMeasuredHeight();
                v.layout((int) (x - (view_width / 2)), (int) (y - (view_height / 2)), (int) (x + (view_width / 2)), (int) (y + (view_height / 2)));

            }
        }
        if (percent_circle_gravity == 1) {//右上
            v_0.layout(getMeasuredWidth() - v_0.getMeasuredWidth(), 0, getMeasuredWidth(), v_0.getMeasuredHeight());
            for (int i = 1; i < childViews; i++) {
                View v = getChildAt(i);
                float y = (float) (radius * Math.sin((double) (jiaodu90 * (i) * PI / 180)));
                float x = (float) (radius * Math.cos((double) (jiaodu90 * (i) * PI / 180)));
                float view_width = v.getMeasuredWidth();
                float view_height = v.getMeasuredHeight();
                v.layout(getMeasuredWidth() - (int) (x + (view_width / 2)), (int) (y - (view_height / 2)), getMeasuredWidth() - (int) (x - (view_width / 2)), (int) (y + (view_height / 2)));

            }
        }
        if (percent_circle_gravity == 2) {//左下
            v_0.layout(0, getMeasuredHeight() - v_0.getMeasuredHeight(), v_0.getMeasuredWidth(), getMeasuredHeight());
            for (int i = 1; i < childViews; i++) {
                View v = getChildAt(i);
                float y = (float) (radius * Math.sin((double) (jiaodu90 * (i) * PI / 180)));
                float x = (float) (radius * Math.cos((double) (jiaodu90 * (i) * PI / 180)));
                float view_width = v.getMeasuredWidth();
                float view_height = v.getMeasuredHeight();
                v.layout((int) (x - (view_width / 2)), getMeasuredHeight() - (int) (y + (view_height / 2)), (int) (x + (view_width / 2)), getMeasuredHeight() - (int) (y - (view_height / 2)));

            }
        }
        if (percent_circle_gravity == 3) {//右下
            v_0.layout(getMeasuredWidth() - v_0.getMeasuredWidth(), getMeasuredHeight() - v_0.getMeasuredHeight(), getMeasuredWidth(), getMeasuredHeight());
            for (int i = 1; i < childViews; i++) {
                View v = getChildAt(i);
                float y = (float) (radius * Math.sin((double) (jiaodu90 * (i) * PI / 180)));
                float x = (float) (radius * Math.cos((double) (jiaodu90 * (i) * PI / 180)));
                float view_width = v.getMeasuredWidth();
                float view_height = v.getMeasuredHeight();
                v.layout((int) (getMeasuredWidth() - x - (view_width / 2)), getMeasuredHeight() - (int) (y + (view_height / 2)), getMeasuredWidth() - (int) (x - (view_width / 2)), getMeasuredHeight() - (int) (y - (view_height / 2)));

            }
        }


        v_0.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                taggle(v);
            }
        });
        intEvent();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        System.out.println("This is Step by onMeasure");
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
        System.out.println("aaa1-->" + percent_circle_gravity);
    }

    public int[] getButtonMenu() {
        return buttonMenu;
    }

    public void setButtonMenu(int[] buttonMenu) {
        this.buttonMenu = buttonMenu;
    }


    public void SetOnlickListen() {

    }

    private void intAnimation() {
        //rotateAnimation = new RotateAnimation(0f, 360f);
        rotateAnimation_405 = new RotateAnimation(0f, 405f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation_405.setDuration(300);
        rotateAnimation_405.setFillAfter(true);
        //this.startAnimation(rotateAnimation);

        rotateAnimation_405_Nor = new RotateAnimation(405f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation_405_Nor.setDuration(300);
        rotateAnimation_405_Nor.setFillAfter(true);

        scaleAnimation =new ScaleAnimation(1.0f, 1.8f, 1.0f, 1.8f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(500);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0f);
        ScaleanimationSet=new AnimationSet(true);
        ScaleanimationSet.addAnimation(scaleAnimation);
        ScaleanimationSet.addAnimation(alphaAnimation);
        ScaleanimationSet.setDuration(300);
        ScaleanimationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                taggle(getChildAt(0));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void intEvent()
    {
        for (int i = 1; i < getChildCount(); i++) {
            View vc = getChildAt(i);
            vc.setClickable(true);
            vc.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.startAnimation(ScaleanimationSet);

                    Toast.makeText(getContext(),"asdasdasd",Toast.LENGTH_SHORT).show();
                     System.out.println("animationSet-->");

                }
            });
        };
    }

    private TranslateAnimation getTranslateAnimation(float fromXDelta, float toXDelta, float fromYDelta, float toYDelta) {
        TranslateAnimation translateAnimation = new TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta);
        return translateAnimation;
    }

    private Delta getDelta(int percent_circle_gravity, View vc,View button) {
        Delta delta = new Delta();
        if (percent_circle_gravity == 0) {//左上
            delta.setFromXDelta((button.getMeasuredWidth()/2)-(vc.getMeasuredWidth()/2));
            delta.setFromYDelta((button.getMeasuredHeight()/2)-(vc.getMeasuredHeight()/2));
            delta.setToXDelta(vc.getLeft());
            delta.setToYDelta(vc.getTop());
        }
        if (percent_circle_gravity == 1) {//右上
            delta.setFromXDelta(getMeasuredWidth()-button.getMeasuredWidth()+((button.getMeasuredWidth()/2)-(vc.getMeasuredWidth()/2)));
            delta.setFromYDelta((button.getMeasuredHeight()/2)-(vc.getMeasuredHeight()/2));
            delta.setToXDelta(vc.getLeft());
            delta.setToYDelta(vc.getTop());
        }
        if (percent_circle_gravity == 2) {//左下
            delta.setFromXDelta((button.getMeasuredWidth()/2)-(vc.getMeasuredWidth()/2));
            delta.setFromYDelta(getMeasuredHeight()-((button.getMeasuredHeight()/2)-(vc.getMeasuredHeight()/2))-button.getMeasuredHeight()/2);
            delta.setToXDelta(vc.getLeft());
            delta.setToYDelta(vc.getTop());
        }
        if (percent_circle_gravity == 3) {//右下
            delta.setFromXDelta(getMeasuredWidth()-button.getMeasuredWidth()+((button.getMeasuredWidth()/2)-(vc.getMeasuredWidth()/2)));
            delta.setFromYDelta(getMeasuredHeight()-((button.getMeasuredHeight()/2)-(vc.getMeasuredHeight()/2))-button.getMeasuredHeight()/2);
            delta.setToXDelta(vc.getLeft());
            delta.setToYDelta(vc.getTop());
        }
        return delta;
    }

    private class Delta {
        float fromXDelta;
        float toXDelta;
        float fromYDelta;
        float toYDelta;

        public float getFromXDelta() {
            return fromXDelta;
        }

        public void setFromXDelta(float fromXDelta) {
            this.fromXDelta = fromXDelta;
        }

        public float getToXDelta() {
            return toXDelta;
        }

        public void setToXDelta(float toXDelta) {
            this.toXDelta = toXDelta;
        }

        public float getFromYDelta() {
            return fromYDelta;
        }

        public void setFromYDelta(float fromYDelta) {
            this.fromYDelta = fromYDelta;
        }

        public float getToYDelta() {
            return toYDelta;
        }

        public void setToYDelta(float toYDelta) {
            this.toYDelta = toYDelta;
        }


    }
 /*   public boolean dispatchTouchEvent(MotionEvent ev){
       //其他处理，在此不管
        System.out.println("dispatchTouchEvent");
        return onTouchEvent(ev);
    }
*/


    public  void taggle(View v)
    {
        if (FLAG_OPEN == false) {
            v.startAnimation(rotateAnimation_405);
            for (int i = 1; i < getChildCount(); i++) {
                View vc = getChildAt(i);
                vc.setVisibility(View.VISIBLE);
                Delta delta = getDelta(percent_circle_gravity, vc,getChildAt(0));
                TranslateAnimation TranslateAnimation=getTranslateAnimation(delta.getFromXDelta()-delta.getToXDelta(),0,delta.getFromYDelta()-delta.getToYDelta(),0);
                TranslateAnimation.setInterpolator(new OvershootInterpolator());
                TranslateAnimation.setDuration(500);
                //TranslateAnimation.setStartOffset(100);
                TranslateAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        System.out.println("onAnimationEnd");
                    }
                });

                AnimationSet animationSet = new AnimationSet(true);//动画集
                AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1f);
                RotateAnimation rotateAnimation_405 = new RotateAnimation(360f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                // rotateAnimation_405.setDuration(500);
                animationSet.setDuration(300);
                animationSet.addAnimation(alphaAnimation);
                animationSet.addAnimation(TranslateAnimation);
                // animationSet.addAnimation(rotateAnimation_405);
                animationSet.setStartOffset(100+(50*i));//菜单按顺序弹出
                animationSet.setInterpolator(new OvershootInterpolator());
                vc.startAnimation(animationSet);

            }
        } else {
                v.startAnimation(rotateAnimation_405_Nor);
            for (int i = 1; i < getChildCount(); i++) {
                View vc = getChildAt(i);
                Delta delta = getDelta(percent_circle_gravity, vc,getChildAt(0));
                TranslateAnimation TranslateAnimation=getTranslateAnimation(0, delta.getFromXDelta()-delta.getToXDelta(),0, delta.getFromYDelta()-delta.getToYDelta());
                // TranslateAnimation.setInterpolator(new OvershootInterpolator());
                TranslateAnimation.setDuration(200);
                //  TranslateAnimation.setStartOffset(100);
                TranslateAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        System.out.println("onAnimationEnd");
                    }
                });

                AnimationSet animationSet = new AnimationSet(true);//动画集
                AlphaAnimation alphaAnimation = new AlphaAnimation(1f, 0f);
                RotateAnimation rotateAnimation_405 = new RotateAnimation(0f, 90f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation_405.setDuration(200);
                animationSet.setDuration(200);
                animationSet.addAnimation(alphaAnimation);
                animationSet.addAnimation(rotateAnimation_405);
                animationSet.addAnimation(TranslateAnimation);
                vc.startAnimation(animationSet);
                vc.setVisibility(View.GONE);
            }
        }
        FLAG_OPEN = (!FLAG_OPEN);
    }


}
