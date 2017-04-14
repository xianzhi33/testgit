package com.app.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.recyclerview.R;

import java.util.List;

/**
 * Created by Administrator on 2016/8/15.
 */
public class homeadapter  extends RecyclerView.Adapter<homeadapter.MyViewHolder>{


    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }



    private Context context;//用于接收传递过来的Context对象
    private List<String> mDatas;
    public homeadapter(Context context,List<String> mDatas) {
        super();
        this.context = context;
        this.mDatas=mDatas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.recyclerview_item_layout, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position)
    {
        System.out.println("position"+position);
        holder.tv.setText(mDatas.get(position));

        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("position"+position);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView tv;

        public MyViewHolder(View view)
        {
            super(view);
            tv = (TextView) view.findViewById(R.id.textview);
        }
    }
}
