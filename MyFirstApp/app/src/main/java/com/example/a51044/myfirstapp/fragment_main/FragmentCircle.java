package com.example.a51044.myfirstapp.fragment_main;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.a51044.myfirstapp.R;
import com.example.a51044.myfirstapp.adapter.MyCircleAdapter;
import com.example.a51044.myfirstapp.base.BaseFragment;
import com.example.a51044.myfirstapp.bean.MyCancelFabouls;
import com.example.a51044.myfirstapp.bean.MyCircle;
import com.example.a51044.myfirstapp.bean.MyFabouls;
import com.example.a51044.myfirstapp.ivew.IVew;
import com.example.a51044.myfirstapp.presenter.PresenterCancel;
import com.example.a51044.myfirstapp.presenter.PresenterImpl;
import com.example.a51044.myfirstapp.presenter.PresenterSlides;
import com.example.a51044.myfirstapp.utils.Contacts;
import com.jcodecraeer.xrecyclerview.XRecyclerView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FragmentCircle<T> extends BaseFragment implements IVew<T> {
    private View view;
    private XRecyclerView home_serch_recy;

    private List<MyCircle.ResultBean> mList = new ArrayList<>();

    private MyCircleAdapter myCircleAdapter;
    private PresenterImpl presenter;

    private PresenterSlides presenterSlides;
    private PresenterCancel presenterCancel;

    private int index = 1;
    private int userId;
    private String sessionId;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private HashMap<String, String> map = new HashMap<>();

    @Override
    protected void initData() {

    }

    @Override
    protected void findViewById(View view) {

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = sharedPreferences.edit();

        home_serch_recy = view.findViewById(R.id.myhome_circle_recy);

        GridLayoutManager manager = new GridLayoutManager(getActivity(), 1);
        home_serch_recy.setLayoutManager(manager);

        myCircleAdapter = new MyCircleAdapter(mList, getActivity());
        home_serch_recy.setAdapter(myCircleAdapter);

        home_serch_recy.setPullRefreshEnabled(true);
        home_serch_recy.setLoadingMoreEnabled(true);
        home_serch_recy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mList.clear();
                index = 1;
                myCircleAdapter.notifyDataSetChanged();
                presenter.startHomeShopping(Contacts.BASE_URL + Contacts.BASE_Circle + (index), 6);
            }

            @Override
            public void onLoadMore() {
                presenter.startHomeShopping(Contacts.BASE_URL + Contacts.BASE_Circle + (++index), 6);

            }
        });

        SharedPreferences lgq = getActivity().getSharedPreferences("lgq", Context.MODE_PRIVATE);
        userId = lgq.getInt("userId", 0);
        sessionId = lgq.getString("sessionId", "");
        myCircleAdapter.setCheckItem(new MyCircleAdapter.CheckItem() {
            @Override
            public void setItemCheck(View view, int i, int id, int j) {
                // Log.d("zzz",mList.get(i).getWhetherGreat()+"....");
                if (i == 2) {
                    map.put("userId", userId + "");
                    map.put("sessionId", sessionId);
                    map.put("circleId", String.valueOf(id));
                    mList.get(j).setWhetherGreat(1);
                    mList.get(j).setGreatNum(mList.get(j).getGreatNum()+1);
                    presenterSlides.startLogin(Contacts.BASE_URL+Contacts.BASE_DianZan, map, 7);
                } else {
                    map.put("userId", userId + "");
                    map.put("sessionId", sessionId);
                    map.put("circleId", String.valueOf(id));
                    mList.get(j).setWhetherGreat(2);
                    mList.get(j).setGreatNum(mList.get(j).getGreatNum()-1);
                    presenterCancel.startLogin(Contacts.BASE_URL+Contacts.BASE_BuZan, map, 8);
                }

            }
        });
    }

    @Override
    protected void getNextData() {

    }

    @Override
    protected void setListener() {
    }


    @Override
    protected void progressLogic() {
        presenter = new PresenterImpl(this);
        presenterCancel = new PresenterCancel(this);
        presenterSlides = new PresenterSlides(this);
        presenter.startHomeShopping(Contacts.BASE_URL + Contacts.BASE_Circle + (index), 6);


    }

    @Override
    protected View getLoadView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.myhome_circle, null);
        return view;
    }

    @Override
    public void getSuccess(T data) {
        if (data instanceof MyCircle) {
            MyCircle myCircle = (MyCircle) data;
            mList.addAll(myCircle.getResult());

            home_serch_recy.refreshComplete();
            home_serch_recy.loadMoreComplete();
            myCircleAdapter.notifyDataSetChanged();
        }
        if (data instanceof MyFabouls) {
            MyFabouls myFabouls = (MyFabouls) data;
            Toast.makeText(getContext(), myFabouls.getMessage(), Toast.LENGTH_SHORT).show();
            myCircleAdapter.notifyDataSetChanged();
        }
        if (data instanceof MyCancelFabouls) {
            MyCancelFabouls myCancelFabouls = (MyCancelFabouls) data;
            Toast.makeText(getContext(), myCancelFabouls.getMessage(), Toast.LENGTH_SHORT).show();
            myCircleAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void getError(T error) {

    }
}
