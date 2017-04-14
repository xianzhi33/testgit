package com.app.recyclerview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.adapter.DecoratorAdapter;
import com.app.adapter.SelectStaggeredDecoratorAdapterGriddapter;
import com.app.adapter.SelectStaggeredGriddapter;
import com.app.util.myDivierItemDecoration;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class HFSelectStaggeredGridRecyclerViewActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<String> mDatas;
    private SelectStaggeredDecoratorAdapterGriddapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shang_l);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initData();
        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_widget);
        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
       // mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
      /*   mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,
                 StaggeredGridLayoutManager.HORIZONTAL));*/

        //System.out.println("Data"+mDatas.toString());
       /* mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));*/
        // mRecyclerView.addItemDecoration(new GridDividerItemDecoration(this));
        mRecyclerView.addItemDecoration(new myDivierItemDecoration(this, R.drawable.divider_bg));
        //mRecyclerView.setAdapter(mAdapter = new SelectStaggeredDecoratorAdapterGriddapter(this, mDatas));
        mAdapter = new SelectStaggeredDecoratorAdapterGriddapter(this, mDatas);
        DecoratorAdapter decoratorAdapter=new DecoratorAdapter(mAdapter,this);
        TextView tv=new TextView(this);
        tv.setText("xianzhiqiang");
        decoratorAdapter.AddHeaders(tv);
        TextView tv2=new TextView(this);
        tv2.setText("xianzhiqiang2");
        //decoratorAdapter.AddHeaders(tv2);

        View v= LayoutInflater.from(
                this).inflate(R.layout.recyclerview_item_manytv_layout, mRecyclerView,
                false);
        TextView tv3= (TextView) v.findViewById(R.id.textview);
        tv3.setText("llllllloooooooooTitle Head");
        tv3.getLayoutParams().height=50;

        Button tijiao= (Button) v.findViewById(R.id.tijiao);
        tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("提交");
            }
        });
        decoratorAdapter.AddHeaders(v);

        TextView tv4=new TextView(this);
        tv4.setText("Foot////******************************////");
        decoratorAdapter.AddFooters(tv4);
        mRecyclerView.setAdapter(decoratorAdapter);

        mAdapter.setOnItemClickLitener(new SelectStaggeredDecoratorAdapterGriddapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemLongClick(View view, int position) {
                System.out.println("longClick");
            }
        });



        // 这句话是为了，第一次进入页面的时候显示加载进度条
        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        swipeRefreshLayout.setRefreshing(false);
    }

    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }

    }

}
