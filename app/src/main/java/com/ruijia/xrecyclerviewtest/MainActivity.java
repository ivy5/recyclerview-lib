package com.ruijia.xrecyclerviewtest;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ruijia.xrecyclerview.XRecyclerView;
import com.ruijia.xrecyclerview.listener.OnItemClickListener;

import java.util.ArrayList;


/**
 * Created by limxing on 16/7/23.
 * <p/>
 * https://github.com/limxing
 * Blog: http://www.leefeng.me
 */
public class MainActivity extends AppCompatActivity implements OnItemClickListener, XRecyclerView.XRecyclerViewListener, XRecyclerView.XRecyclerViewScrollChange {
    private XRecyclerView recycler;
    private boolean b;
    private ArrayList<String> list;
    private MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            list.add("leefeng.me" + i);
        }

        recycler = (XRecyclerView) findViewById(R.id.recyclerView);
        recycler.setLoadMore(true);
        recycler.setRefresh(true);
        recycler.setNoDateShow();
        recycler.setAutoLoadMore(true);
        recycler.setOnItemClickListener(this);
        recycler.setLFRecyclerViewListener(this);
        recycler.setScrollChangeListener(this);
        recycler.setItemAnimator(new DefaultItemAnimator());
        adapter = new MainAdapter(list);
        recycler.setAdapter(adapter);

        TextView tv = new TextView(MainActivity.this);
        tv.setText("这是头部");
        tv.setTextColor(Color.WHITE);
        tv.setGravity(Gravity.CENTER);
        tv.setBackgroundColor(Color.RED);
        recycler.setHeaderView(tv);
         tv = new TextView(MainActivity.this);
        tv.setText("这是底部");
        tv.setTextColor(Color.WHITE);
        tv.setGravity(Gravity.CENTER);
        tv.setBackgroundColor(Color.RED);
        recycler.setFootView(tv);
    }

    @Override
    public void onClick(int position) {
        Toast.makeText(this, "" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLongClick(int po) {
        Toast.makeText(this, "Long:" + po, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                b = !b;
                list.add(0, "leefeng.me" + "==onRefresh");
                recycler.stopRefresh(b);
                adapter.notifyItemInserted(0);
                adapter.notifyItemRangeChanged(0,list.size());

            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                recycler.stopLoadMore();
                list.add(list.size(), "leefeng.me" + "==onLoadMore");
//                list.add(list.size(), "leefeng.me" + "==onLoadMore");
                adapter.notifyItemRangeInserted(list.size()-1,1);

            }
        }, 2000);
    }

    @Override
    public void onRecyclerViewScrollChange(View view, int i, int i1) {

    }
}
