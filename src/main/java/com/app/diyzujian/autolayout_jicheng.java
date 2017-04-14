package com.app.diyzujian;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.app.adapter.List_TabAdapter;

/**
 * Created by Administrator on 2016/12/1.
 */
public class autolayout_jicheng extends autolayout {
    public autolayout_jicheng(Context context) {
        super(context);
    }

    public autolayout_jicheng(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public autolayout_jicheng(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public autolayout_jicheng(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public void setAdapter(List_TabAdapter adapter)
    {
       int countItem= adapter.getCountItem();
        for(int i=0;i<countItem;i++)
        {
           View view = (View) adapter.getView(i);
            addView(view);
        }
        requestLayout();
        invalidate();
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }
}
