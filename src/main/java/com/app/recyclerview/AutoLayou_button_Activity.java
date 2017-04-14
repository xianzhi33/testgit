package com.app.recyclerview;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.BaseAdapter;

import com.app.adapter.List_TabAdapter;
import com.app.util.TabadapterIm;

import java.util.ArrayList;
import java.util.List;

public class AutoLayou_button_Activity extends AppCompatActivity {

    com.app.diyzujian.autolayout_jicheng autolayout_jicheng;
    List_TabAdapter list_tabAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_button_layou);
        autolayout_jicheng = (com.app.diyzujian.autolayout_jicheng) this.findViewById(R.id.autolayout_jicheng);


        List list= new ArrayList();
        list.add("111111"); list.add("22222"); list.add("33333"); list.add("444444");
        list.add("aaaaaa");list.add("bbbbbbb");list.add("cccccc");list.add("ddddddd");
        list.add("eeeeeee");list.add("fffffff");list.add("ggggggg");list.add("hhhhhhh");
        list_tabAdapter=new TabadapterIm(this,list);
        autolayout_jicheng.setAdapter(list_tabAdapter);

    }
}
