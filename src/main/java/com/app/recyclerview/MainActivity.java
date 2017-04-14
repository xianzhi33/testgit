package com.app.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.app.util.DividerItemDecoration;
import com.app.util.homeadapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private homeadapter mAdapter;
    int a=0;
   //修改测试第一次  已经完成功能1  已经完成功能2
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button button = (Button) this.findViewById(R.id.ceshishangxiala);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ShangLA.class);
                startActivity(intent);
            }
        });

        Button button2 = (Button) this.findViewById(R.id.ceshishangxiala2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ScrollingActivity.class);
                startActivity(intent);
            }
        });


        Button button3 = (Button) this.findViewById(R.id.ceshishangxiala3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent();
                intent.setClass(MainActivity.this, shouqianActioBar.class);
                startActivity(intent);
            }
        });

        Button button4 = (Button) this.findViewById(R.id.zhuanpan);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent();
                intent.setClass(MainActivity.this, ZhuanPanActivity.class);
                startActivity(intent);
            }
        });

        Button button5 = (Button) this.findViewById(R.id.autolayou);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent();
                intent.setClass(MainActivity.this, AutoLayouActivity.class);
                startActivity(intent);
            }
        });
        Button button6 = (Button) this.findViewById(R.id.cehuaActivity);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent();
                intent.setClass(MainActivity.this, cehuaActivity.class);
                startActivity(intent);
            }
        });
        Button button7 = (Button) this.findViewById(R.id.autolayou_button);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent();
                intent.setClass(MainActivity.this, AutoLayou_button_Activity.class);
                startActivity(intent);

            }
        });

        Button button8 = (Button) this.findViewById(R.id.arc_menu);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent();
                intent.setClass(MainActivity.this, arcmenu_Activity.class);
                startActivity(intent);

            }
        });
        Button arc_menu_layout = (Button) this.findViewById(R.id.arc_menu_layout);
        arc_menu_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent();
                intent.setClass(MainActivity.this, arcmenu_layout_Activity.class);
                startActivity(intent);

            }
        });




        Button FragmentViewPagerActivity_Button = (Button) this.findViewById(R.id.FragmentViewPagerActivity);
        FragmentViewPagerActivity_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, FragmentPagerViewActivity.class);
                startActivity(intent);

            }
        });



        Button MyProgressActivity = (Button) this.findViewById(R.id.MyProgressActivity);
        MyProgressActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("MyProgressActivity");
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MyProgressBarActivity.class);
                startActivity(intent);

            }
        });


        Button GridRecyclerViewActivity=(Button) this.findViewById(R.id.GridRecyclerViewActivity);
        GridRecyclerViewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, GridRecyclerActivity.class);
                startActivity(intent);
            }
        });

        Button StaggeredGridRecyclerViewActivity=(Button) this.findViewById(R.id.StaggeredGridRecyclerViewActivity);
        StaggeredGridRecyclerViewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, StaggeredGridRecyclerViewActivity.class);
                startActivity(intent);
            }
        });


        Button SelectStaggeredGridRecyclerViewActivity=(Button) this.findViewById(R.id.SelectStaggeredGridRecyclerViewActivity);
        SelectStaggeredGridRecyclerViewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SelectStaggeredGridRecyclerViewActivity.class);
                startActivity(intent);
            }
        });

        Button HFSelectStaggeredGridRecyclerViewActivity=(Button) this.findViewById(R.id.HFSelectStaggeredGridRecyclerViewActivity);
        HFSelectStaggeredGridRecyclerViewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, HFSelectStaggeredGridRecyclerViewActivity.class);
                startActivity(intent);
            }
        });

        Button SwipRefreshLayoutRecyclerViewActivity=(Button) this.findViewById(R.id.SwipRefreshLayoutRecyclerViewActivity);
        SwipRefreshLayoutRecyclerViewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SwipRefreshLayoutRecyclerViewActivity.class);
                startActivity(intent);
            }
        });

        Button IntentDataSwipRefreshLayoutRecyclerViewActivity=(Button) this.findViewById(R.id.IntentDataSwipRefreshLayoutRecyclerViewActivity);
        IntentDataSwipRefreshLayoutRecyclerViewActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, IntentDataSwipRefreshLayoutRecyclerViewActivity.class);
                startActivity(intent);
            }
        });


        Button BehaviorActivity=(Button) this.findViewById(R.id.Behavior);
        BehaviorActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Behavior.class);
                startActivity(intent);
            }
        });


        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "FAB", Snackbar.LENGTH_LONG)
                        .setAction("cancel", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //这里的单击事件代表点击消除Action后的响应事件

                            }
                        })
                        .show();
            }
        });

        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this)); //添加布局
        //System.out.println("Data"+mDatas.toString());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));//添加分隔线
        mRecyclerView.setAdapter(mAdapter = new homeadapter(this, mDatas));//添加数据

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

}
