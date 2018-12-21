package com.example.a51044.myapp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.a51044.myapp.R;
import com.example.a51044.myapp.adapter.MyAdapter;
import com.example.a51044.myapp.bean.MyData;
import com.example.a51044.myapp.ivew.IVew;
import com.example.a51044.myapp.presenter.PresenterImpl;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FragmentA<T> extends Fragment implements IVew<T> {
    private View view;
    private MyAdapter myAdapter;
    private List<MyData.DataBean> mList = new ArrayList<>();
    private String mUrl="http://www.xieast.com/api/news/news.php?page=";
    private int index=1;

    private PresenterImpl presenter;
    private XRecyclerView recy;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recycle, null);

        initView(view);
        presenter.startData(mUrl);
        return view;
    }

    private void initView(View view) {
        recy = (XRecyclerView) view.findViewById(R.id.recy);
        StaggeredGridLayoutManager manager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recy.setLayoutManager(manager);
        recy.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recy.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        recy.setArrowImageView(R.drawable.ic_launcher_background);

        recy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mList.clear();
                index=1;
                presenter.startData(mUrl+index);
                recy.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                index++;
                presenter.startData(mUrl+index);
                recy.loadMoreComplete();
            }
        });
        presenter=new PresenterImpl(this);
        myAdapter=new MyAdapter(mList);
        recy.setAdapter(myAdapter);
        presenter.startData(mUrl);
    }

    @Override
    public void getLoginData(T data) {
        MyData data1= (MyData) data;
        mList.addAll(data1.getData());
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void getLoginError(T error) {

    }
}
