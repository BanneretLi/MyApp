package com.example.a51044.mymoni.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.a51044.mymoni.R;
import com.example.a51044.mymoni.adapter.MyListAdapter;
import com.example.a51044.mymoni.base.BaseFragment;
import com.example.a51044.mymoni.bean.MyData;
import com.example.a51044.mymoni.presenter.PresenterImpl;
import com.example.a51044.mymoni.ui.a;
import com.example.a51044.mymoni.view.IVewXlist;
import com.example.xlistviewflush.view.XListView;

import java.util.ArrayList;
import java.util.List;

public class MyList extends BaseFragment implements IVewXlist, XListView.IXListViewListener {
    private View view;
    private XListView xList;
    private MyListAdapter myListAdapter;
    private PresenterImpl presenter;
    private String mUrl = "http://www.xieast.com/api/news/news.php?page=";
    private int index = 1;
    private List<MyData.DataBean> mList = new ArrayList<>();


    @Override
    protected void initData() {

    }

    @Override
    protected void findById(View view) {
        xList = view.findViewById(R.id.xList);
    }

    @Override
    protected void setOnclick() {
        xList.setPullLoadEnable(true);
        xList.setXListViewListener(this);
    }

    @Override
    protected void logic() {
        presenter = new PresenterImpl(this);
        myListAdapter = new MyListAdapter(mList, getContext().getApplicationContext());
        xList.setAdapter(myListAdapter);
        presenter.startXList(mUrl + index);
        xList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), a.class);
                intent.putExtra("url", mList.get(position - 1).getUrl());
                startActivity(intent);
            }
        });
    }

    @Override
    protected View getLoadView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.xlist_view, null);
        return view;
    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public void getXList(List<MyData.DataBean> dataBeans) {
        mList.addAll(dataBeans);
        myListAdapter.notifyDataSetChanged();
        stop();
    }

    @Override
    public void getError(String error) {
        Toast.makeText(getContext().getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        index = 1;
        mList.clear();
        presenter.startXList(mUrl + index);
    }

    @Override
    public void onLoadMore() {
        presenter.startXList(mUrl + (++index));
    }

    private void stop() {
        xList.stopRefresh();
        xList.stopLoadMore();
        xList.setRefreshTime("刚刚");
    }
}
