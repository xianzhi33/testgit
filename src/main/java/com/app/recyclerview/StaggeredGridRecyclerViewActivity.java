package com.app.recyclerview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.app.adapter.StaggeredGriddapter;
import com.app.util.GridDividerItemDecoration;
import com.app.util.homeadapter;
import com.app.util.myDivierItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class StaggeredGridRecyclerViewActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private StaggeredGriddapter mAdapter;
    private List<Integer> mHeights;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shang_l);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
       // mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,
                 StaggeredGridLayoutManager.VERTICAL));

        //System.out.println("Data"+mDatas.toString());
       /* mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));*/
      //   mRecyclerView.addItemDecoration(new GridDividerItemDecoration(this));
        mRecyclerView.addItemDecoration(new myDivierItemDecoration(this,R.drawable.divider_bg));

        mRecyclerView.setAdapter(mAdapter = new StaggeredGriddapter(this, mDatas));


    }
    protected void initData()
    {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++)
        {
            mDatas.add("" + (char) i);
        }
    }


}
