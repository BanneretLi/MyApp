package com.example.a51044.mymoni2.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a51044.mymoni2.R;
import com.example.a51044.mymoni2.adapter.MyListAdapter;
import com.example.a51044.mymoni2.base.BaseFragment;
import com.example.a51044.mymoni2.bean.MyData;
import com.example.a51044.mymoni2.ivew.IVew;
import com.example.a51044.mymoni2.presenter.PresenterImpl;
import com.example.xlistviewflush.view.XListView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyListFragment<T> extends BaseFragment implements XListView.IXListViewListener ,IVew<T> {
    private View view;
    private XListView myXList;
    private PresenterImpl presenter;
    private String mUrl="http://www.xieast.com/api/news/news.php?page=";
    private List<MyData.DataBean>mList=new ArrayList<>();
    private MyListAdapter myListAdapter;
    private int index=1;


    @Override
    protected void initData() {

    }

    @Override
    protected void findViewById(View view) {
        myXList=view.findViewById(R.id.xList);

    }

    @Override
    protected void setOnclick() {
        myXList.setXListViewListener(this);
        myXList.setPullLoadEnable(true);
    }

    @Override
    protected void logic() {
        presenter=new PresenterImpl(this);
        myListAdapter=new MyListAdapter(mList,getActivity().getApplicationContext());
        myXList.setAdapter(myListAdapter);
        presenter.startXlist(mUrl+index);

    }

    @Override
    protected View getLoadView(LayoutInflater inflater, ViewGroup container) {
        view=inflater.inflate(R.layout.xlist_view,null);
        return view;
    }

    @Override
    public void onRefresh() {
        mList.clear();
        presenter.startXlist(mUrl+index);
    }

    @Override
    public void onLoadMore() {
        presenter.startXlist(mUrl+(++index));
    }

    @Override
    public void success(T user) {
        List<MyData.DataBean>myData= (List<MyData.DataBean>) user;
        mList.addAll(myData);
        myListAdapter.notifyDataSetChanged();
        stop();
    }

    private void stop() {
        myXList.stopRefresh();
        myXList.stopLoadMore();
        myXList.setRefreshTime("刚刚");
    }

    @Override
    public void error(T error) {

    }

    @Override
    public void onClick(View v) {

    }
}
