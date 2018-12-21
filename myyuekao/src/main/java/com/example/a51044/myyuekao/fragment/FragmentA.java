package com.example.a51044.myyuekao.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a51044.myyuekao.R;
import com.example.a51044.myyuekao.adapter.MyLinAdapter;
import com.example.a51044.myyuekao.bean.LinData;
import com.example.a51044.myyuekao.bean.MyData;
import com.example.a51044.myyuekao.ivew.IVew;
import com.example.a51044.myyuekao.presenter.PresenterImpl;

import java.util.ArrayList;
import java.util.List;

public class FragmentA<T> extends Fragment implements IVew<T> {
    private View view;
    private String mUrl="http://www.xieast.com/api/news/news.php?page=";
    private List<LinData.DataBean>mList=new ArrayList<>();
    private PresenterImpl presenter;
    private RecyclerView myRecycler;
    private MyLinAdapter myLinAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment,null);
        myRecycler=view.findViewById(R.id.recycle_two);
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        myRecycler.setLayoutManager(manager);
        myLinAdapter=new MyLinAdapter(mList,getActivity());
        myRecycler.setAdapter(myLinAdapter);
        presenter=new PresenterImpl(this);
        presenter.startData(mUrl,2);
        return view;
    }

    @Override
    public void getData(T data) {
        LinData linData= (LinData) data;
        mList.addAll(linData.getData());
        myLinAdapter.notifyDataSetChanged();
    }

    @Override
    public void getError(T error) {

    }
}
