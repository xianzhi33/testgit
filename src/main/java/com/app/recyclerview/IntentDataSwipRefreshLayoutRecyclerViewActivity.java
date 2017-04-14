package com.app.recyclerview;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.adapter.DecoratorAdapter;
import com.app.adapter.SelectStaggeredDecoratorAdapterGriddapter;
import com.app.util.myDivierItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class IntentDataSwipRefreshLayoutRecyclerViewActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<String> mDatas =new ArrayList<String>();
    ;
    private SelectStaggeredDecoratorAdapterGriddapter mAdapter;
    DecoratorAdapter decoratorAdapter;
    Handler handler;
    private int Q_ListView_Item_Success = 1;//网络请求成功
    private int Q_ListView_Item_ERR = 2;////网络请求出现错误
    private int Q_ListView_Item_Failue = 3;//网络请求失败
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shang_l);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_widget);
        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new myDivierItemDecoration(this, R.drawable.divider_bg));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.currentThread().sleep(2000);//阻断2秒
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        queryforListviewData();

                    }
                };
                Thread t = new Thread(runnable);
                t.start();

            }
        });


        mRecyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {

            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                System.out.println("View-->"+((TextView)v.findViewById(R.id.textview)).getText().toString());
            }
        });
        // 这句话是为了，第一次进入页面的时候显示加载进度条
        swipeRefreshLayout.setProgressViewOffset(true, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        swipeRefreshLayout.setRefreshing(true);


         handler=new Handler(){
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.arg1 == Q_ListView_Item_Success)
                {

                    try {
                        JSONArray jsonArra=new JSONArray(msg.obj.toString());//;

                        for(int i=0;i<jsonArra.length();i++)
                        {
                           JSONObject jsonObject=new JSONObject(jsonArra.getString(i));
                            mDatas.add(jsonObject.get("name").toString());
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    System.out.println("hello--->"+mDatas.toString());
                if(mAdapter==null)
                {
                    intAdapterData();

                }else
                {
                    decoratorAdapter.notifyDataSetChanged();
                }


                    swipeRefreshLayout.setRefreshing(false);

                }


            }
        };

        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.currentThread().sleep(2000);//阻断2秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queryforListviewData();

            }
        };
        Thread t=new Thread(runnable);
        t.start();

    }

    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }

    }




    public void queryforListviewData() {


        //buildUrlString();
        // 创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        // 创建一个Request
        //final Request request = new
        /* Request.Builder().url("http://61.142.211.75:8081/QueryStateTest.jsp").build();*/
        final Request request = new Request.Builder()
                .url("http://guolin.tech/api/china")
                .build();


        // new call
        Call call = mOkHttpClient.newCall(request);
        // 请求加入调度
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call arg0, IOException arg1) {
                // TODO Auto-generated method stub

                Message msg = new Message();
                msg.arg1 = Q_ListView_Item_ERR;
                msg.obj = arg1.toString();
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call arg0, Response arg1) throws IOException {
                // TODO Auto-generated method stub

                if (arg1.isSuccessful()) {

                    String htmlStr = arg1.body().string();
                    System.out.println("htmlStr" + htmlStr);

                    Message msg = new Message();
                    msg.arg1 = Q_ListView_Item_Success;
                    msg.obj = htmlStr;
                    handler.sendMessage(msg);
                } else {
                    Message msg = new Message();
                    msg.arg1 = Q_ListView_Item_Failue;
                    msg.obj = arg1.toString();
                    handler.sendMessage(msg);
                }
            }
        });
    }


 public void    intAdapterData()
    {
        mAdapter = new SelectStaggeredDecoratorAdapterGriddapter(this, mDatas);
         decoratorAdapter=new DecoratorAdapter(mAdapter,this);
        TextView tv=new TextView(this);
        tv.setText("xianzhiqiang");
        decoratorAdapter.AddHeaders(tv);
        TextView tv2=new TextView(this);
        tv2.setText("xianzhiqiang2");

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

                swipeRefreshLayout.setRefreshing(false);
            }
        });


    }

}
