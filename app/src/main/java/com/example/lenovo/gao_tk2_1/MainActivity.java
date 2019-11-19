package com.example.lenovo.gao_tk2_1;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.lenovo.adapters.RecyAdapter;
import com.example.lenovo.beans.Bean;
import com.example.lenovo.beans.MtBroadcast;
import com.example.lenovo.persenter.MainPresenter;
import com.example.lenovo.view.MainView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {

    private RecyclerView mRecy;
    private MainPresenter mainPresenter;
    private RecyAdapter adapter;
    private SmartRefreshLayout mSmart;
    int page = 1;
    private MtBroadcast mtBroadcast;
    private int posi;
    private List<Bean.ResultsBean> results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresenter = new MainPresenter(this);
        initView();
        initData();
    }

    private void initData() {
        mainPresenter.getData();
    }

    private void initView() {
        mRecy = (RecyclerView) findViewById(R.id.recy);
        mSmart = (SmartRefreshLayout) findViewById(R.id.smart);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mtBroadcast = new MtBroadcast();
        IntentFilter intentFilter = new IntentFilter("com.example.lenovo.gao_tk2_1");
        registerReceiver(mtBroadcast,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mtBroadcast);
    }

    @Override
    public void setData(Bean bean) {
        results = bean.getResults();
        adapter = new RecyAdapter(this, results);
        mRecy.setLayoutManager(new LinearLayoutManager(this));
        mRecy.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecy.setAdapter(adapter);

        mSmart.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initData();
                adapter.notifyDataSetChanged();
                mSmart.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                adapter.getResults().clear();
                initData();
                adapter.notifyDataSetChanged();
                mSmart.finishRefresh();
            }
        });

        adapter.setAonClick(new RecyAdapter.AonClick() {
            @Override
            public void onClick(int position) {
                posi = position;
                Broadcast();
                Toast.makeText(MainActivity.this, adapter.getResults().get(posi).get_id(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void Broadcast() {
        Intent intent = new Intent("com.example.lenovo.gao_tk2_1");
        intent.putExtra("name",results.get(posi).getDesc());
        sendBroadcast(intent);
    }
}
