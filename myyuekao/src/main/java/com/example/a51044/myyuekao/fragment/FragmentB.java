package com.example.a51044.myyuekao.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a51044.myyuekao.R;
import com.example.a51044.myyuekao.adapter.NewsDataAdpter;
import com.example.a51044.myyuekao.bean.LinData;
import com.example.a51044.myyuekao.bean.NewsData;
import com.example.a51044.myyuekao.ivew.IVew;
import com.example.a51044.myyuekao.presenter.PresenterImpl;
import java.util.ArrayList;
import java.util.List;

public class FragmentB<T> extends Fragment implements IVew<T> {
    private View view;
    private String mUrl="http://www.xieast.com/api/news/news.php?page=3";
    private List<LinData.DataBean> mList=new ArrayList<>();
    private PresenterImpl presenter;
    private RecyclerView recy_three;
    private NewsDataAdpter newsDataAdpter2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment2,null);
        recy_three=view.findViewById(R.id.recy_three);
        GridLayoutManager manager=new GridLayoutManager(getActivity(),2);
        recy_three.setLayoutManager(manager);
        newsDataAdpter2=new NewsDataAdpter(mList,getActivity());
        recy_three.setAdapter(newsDataAdpter2);
        presenter=new PresenterImpl(this);
        presenter.startData(mUrl,3);
        return view;
    }

    @Override
    public void getData(T data) {
        LinData data1= (LinData) data;
        mList.addAll(data1.getData());
        newsDataAdpter2.notifyDataSetChanged();
    }

    @Override
    public void getError(T error) {

    }
}
