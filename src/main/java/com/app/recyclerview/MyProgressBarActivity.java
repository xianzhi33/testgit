package com.app.recyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.app.diyzujian.MyProgress;
import com.app.diyzujian.MyProgressCircle;

public class MyProgressBarActivity extends AppCompatActivity {
    int i=0;
    MyProgress myProgress;
    MyProgressCircle MyProgressCircle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_progress_bar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        myProgress= (MyProgress) this.findViewById(R.id.MyProgress);
        MyProgressCircle= (com.app.diyzujian.MyProgressCircle) this.findViewById(R.id.MyProgressCircle);

        final Handler handler=new Handler();
          Runnable runnable=new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                //要做的事情
                i++;
                if(i>1000)
                {

                }else {
                    handler.postDelayed(this, 50);

                }

                    //i=0;
                myProgress.setCurrentProgress(i);
                MyProgressCircle.setCurrentProgress(i);
                //System.out.println("I---->"+i);
            }
        };
        handler.postDelayed(runnable, 50);//每两秒执行一次runnable.

    }

}
