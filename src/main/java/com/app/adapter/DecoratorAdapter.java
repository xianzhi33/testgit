package com.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.recyclerview.R;
import com.app.util.AutoRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/8.
 */
public class DecoratorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    int headers_number_start = 100000;
    int footers_number_start = 500000;
    SparseArray<View> headers = new SparseArray();
    SparseArray<View> footers = new SparseArray();
    RecyclerView.Adapter adapter;
    Context context;

    public DecoratorAdapter(RecyclerView.Adapter adapter, Context context) {
        this.adapter = adapter;
        this.context = context;
    }

    public void AddHeaders(View v) {
        headers.put(headers_number_start + headers.size(), v);
    }

    public void AddFooters(View v) {
        footers.put(footers_number_start + footers.size(), v);
    }

    public View getHeader(int i) {
        if (i < headers.size()) {
            return headers.get(i);
        } else {
            return null;
        }
    }

    public View getFooter(int i) {
        if (i < footers.size()) {
            return footers.get(i);
        } else {
            return null;
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType < headers.size()) {

            //获取头部布局
         /*   MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    parent.getContext()).inflate(R.layout.recyclerview_item_layout, parent,
                    false));
            System.out.println("viewType" + viewType);
            System.out.println("headers" + headers.size());
            holder.tv.setText("xianzhiqiang333");*/

            System.out.println("viewType-->" + viewType);
            System.out.println("headers viewType" + headers.get(viewType));
             AutoRecyclerViewHolder autoRecyclerViewHolder=AutoRecyclerViewHolder.createViewHolder(headers.get(viewType+headers_number_start));
          /*  AutoRecyclerViewHolder autoRecyclerViewHolder = AutoRecyclerViewHolder.createViewHolder(context, R.layout.recyclerview_item_layout, parent, viewType);
            TextView tv = autoRecyclerViewHolder.getView(R.id.textview);*/
            //tv.setText("sdasdasd");

            return autoRecyclerViewHolder;
        } else if (viewType >= headers.size() + adapter.getItemCount()) {
            //获取内容布局
            /*
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    parent.getContext()).inflate(R.layout.recyclerview_item_layout, parent,
                    false));
            holder.tv = (TextView) footers.get(viewType);*/
            System.out.println("footNumber--->"+String.valueOf(viewType-headers.size()-adapter.getItemCount()+footers_number_start));
            View view= footers.get(viewType-headers.size()-adapter.getItemCount()+footers_number_start);
            AutoRecyclerViewHolder autoRecyclerViewHolder=AutoRecyclerViewHolder.createViewHolder(view);
            return autoRecyclerViewHolder;
        }


        return adapter.onCreateViewHolder(parent, viewType - headers.size());


    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (position < headers.size()) {

        } else if (position >= headers.size() + adapter.getItemCount()) {

        } else {
            adapter.onBindViewHolder(holder, position - headers.size());
        }
    }

    @Override
    public int getItemCount() {
        return adapter.getItemCount() + headers.size() + footers.size();
    }

    //设置该ItemView 的种类 Type
    @Override
    public int getItemViewType(int position) {

        return position;

    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        LinearLayout layout;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.textview);
            layout = (LinearLayout) view.findViewById(R.id.staggeredgridlayout);
        }
    }


}
