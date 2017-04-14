package com.app.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.app.util.DividerItemDecoration;
import com.app.util.homeadapter;

import java.util.ArrayList;
import java.util.List;

public class Behavior extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private homeadapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //System.out.println("Data"+mDatas.toString());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setAdapter(mAdapter = new homeadapter(this, mDatas));

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
