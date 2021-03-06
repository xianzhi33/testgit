package com.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.recyclerview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/15.
 */
public class StaggeredGriddapter extends RecyclerView.Adapter<StaggeredGriddapter.MyViewHolder>{


    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    private List<Integer> mHeights;

    private Context context;//用于接收传递过来的Context对象
    private List<String> mDatas;
    public StaggeredGriddapter(Context context, List<String> mDatas) {
        super();
        this.context = context;
        this.mDatas=mDatas;
        mHeights = new ArrayList<Integer>();
        for (int i = 0; i < mDatas.size(); i++)
        {
            mHeights.add( (int) (100 + Math.random() * 300));
        }
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
       // System.out.println("position" + position);
        int x=(int) (1 + Math.random() * 501);
       // holder.layout.setMinimumHeight(x);
        ViewGroup.LayoutParams lp = holder.tv.getLayoutParams();
        lp.height = mHeights.get(position);
      // lp.height =100;

        holder.tv.setLayoutParams(lp);
       /* ViewGroup.LayoutParams lp = holder.layout.getLayoutParams();
        lp.height = x;
        holder.layout.setLayoutParams(lp);
        System.out.println("setMinimumHeight:"+x);*/
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

        LinearLayout layout;
        public MyViewHolder(View view)
        {
            super(view);
            tv = (TextView) view.findViewById(R.id.textview);
            layout=(LinearLayout) view.findViewById(R.id.staggeredgridlayout);
        }
    }
}
