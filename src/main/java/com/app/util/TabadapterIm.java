package com.app.util;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.app.adapter.List_TabAdapter;
import com.app.recyclerview.R;

import java.lang.ref.Reference;
import java.util.List;

/**
 * Created by Administrator on 2016/11/30.
 */
public class TabadapterIm extends List_TabAdapter {

    Context context;
    public TabadapterIm(Context context, List list) {
        super(context, list);
        this.context=context;
    }

    @Override
    public Object getView(Context context, int index) {
        return null;
    }

    @Override
    public Object getView(int index)
    {
        TextView textView= (TextView) View.inflate(context, R.layout.textview_item,null);
        textView.setText((String)getItem(index));
        return textView;
    }
}
