package com.app.adapter;

import android.content.Context;

import java.util.List;

/**
 * Created by Administrator on 2016/11/30.
 */
public abstract class List_TabAdapter<T> {

    int Index;
    Context context;
    List<T> list = null;

    public List_TabAdapter(Context context, List<T> list) {
        this.list = list;
        this.context = context;
    }

    public void setAdapter(List<T> list) {
        this.list = list;
    }

    public int getCountItem() {

        return list.size();
    }

    public T getItem(int i) {
        return list.get(i);
    }
    public void setIndex( int index)
    {
               this.Index=index;
    }

    public T getCurrentItem()
    {
        return list.get(Index);
    }

    abstract public T getView(Context context,int index);

    abstract public T getView(int index);
}
