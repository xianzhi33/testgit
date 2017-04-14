package com.app.util;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/2/9.
 */
public class AutoViewHolder {
    SparseArray mViews;
    View mConvertView;
    LayoutInflater layoutInflater;
    public AutoViewHolder(int position, View convertView, ViewGroup parent, int layoutId) {
        this.mViews = new SparseArray<View>();
        mConvertView=layoutInflater.inflate(layoutId,parent,false);
        mConvertView.setTag(this);
    }
    public static AutoViewHolder getAutoViewHolder(int position, View convertView, ViewGroup parent, int layoutId)
    {
        if(convertView==null)
        {
            return new AutoViewHolder(position,convertView,parent,layoutId);
        }
        return (AutoViewHolder) convertView.getTag();
    }
    public View getmConvertView() {

        return mConvertView;
    }

      public <T extends View> T getView( int layoutId)
    {

        if(mViews.keyAt(layoutId)==-1)
        {
            mViews.put(layoutId, mConvertView.findViewById(layoutId));
        }

        return (T) mViews.get(layoutId);
    }
}
