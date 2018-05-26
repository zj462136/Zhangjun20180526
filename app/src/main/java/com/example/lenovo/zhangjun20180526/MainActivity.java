package com.example.lenovo.zhangjun20180526;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.example.lenovo.zhangjun20180526.bean.BaseBean;
import com.example.lenovo.zhangjun20180526.component.DaggerHttpComponent;
import com.example.lenovo.zhangjun20180526.module.HttpModule;
import com.example.lenovo.zhangjun20180526.ui.base.BaseActivity;
import com.example.lenovo.zhangjun20180526.ui.main.MainContract;
import com.example.lenovo.zhangjun20180526.ui.main.MainPresenter;
import com.example.lenovo.zhangjun20180526.ui.music.MusicActivity;
import com.example.lenovo.zhangjun20180526.adapter.MyAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    private LinearLayout ll;
    private RecyclerView rv;
    private int type = 1;
    private int page = 1;
    private SmartRefreshLayout smart_refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mPresenter.onSuccess(type,page);
        smart_refresh.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mPresenter.onSuccess(type,page);
                smart_refresh.finishLoadmore(2000);
            }
        });
        smart_refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page++;
                mPresenter.onSuccess(type,page);
                smart_refresh.finishRefresh(2000);
            }
        });
    }

    private void initView() {
        ll = (LinearLayout) findViewById(R.id.ll);
        rv = (RecyclerView) findViewById(R.id.rv);
        smart_refresh = (SmartRefreshLayout) findViewById(R.id.smart_refresh);
    }

    @Override
    public void onSuccess(BaseBean baseBean) {
        List<BaseBean.DataBean> data = baseBean.getData();
        MyAdapter adapter = new MyAdapter(this, data);
        rv.setAdapter(adapter);
    }

    public void tiaoZhuan(View view) {
        Intent intent = new Intent(this, MusicActivity.class);
        startActivity(intent);
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }
}
