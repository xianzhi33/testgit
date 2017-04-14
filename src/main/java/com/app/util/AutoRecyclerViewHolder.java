package com.app.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/2/9.
 */
public class AutoRecyclerViewHolder extends RecyclerView.ViewHolder{
    SparseArray mViews;
    View mConvertView;
    LayoutInflater layoutInflater;

    public AutoRecyclerViewHolder(View itemView) {
        super(itemView);
        mConvertView=itemView;
        mViews=new SparseArray();
    }

    public static AutoRecyclerViewHolder createViewHolder(View itemView)
    {
            return  new AutoRecyclerViewHolder(itemView);
    }
    public static  AutoRecyclerViewHolder createViewHolder(Context context,int LayoutId, ViewGroup parent, int viewType)
    {
       View  view = LayoutInflater.from(context).inflate(LayoutId,parent,false);
        return  new AutoRecyclerViewHolder(view);
    }

    //将View 放到容器中
      public <T extends View> T getView(int layoutId)
    {

        if(mViews.get(layoutId)==null)
        {
            mViews.put(layoutId, mConvertView.findViewById(layoutId));
        }

        return (T) mViews.get(layoutId);
    }

}
