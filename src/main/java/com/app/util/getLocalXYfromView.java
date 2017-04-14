package com.app.util;

import android.view.View;

/**
 * Created by Administrator on 2016/11/24.
 */
public class getLocalXYfromView {

    View view;
    int width;
    int height;
    int x;
    int y;
    public getLocalXYfromView(View view, int x, int y) {
        this.view = view;
        this.x=x;
        this.y=y;
         width = view.getMeasuredWidth();
         height = view.getMeasuredHeight();

    }

    public int getLValue()
    {
        return    x-(width/2);
    }
    public int getTValue()
    {
        return    y-(height/2);
    }
    public int getRValue()
    {
        return    x+(width/2);
    }
    public int getbValue()
    {
        return   y+(height/2);
    }
}
