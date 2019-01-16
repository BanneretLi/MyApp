package com.example.a51044.myfirstapp;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.example.a51044.myfirstapp.adapter.MyCircleAdapter;
import com.example.a51044.myfirstapp.base.BaseActivity;
import com.example.a51044.myfirstapp.bean.MyCircle;
import com.example.a51044.myfirstapp.ivew.IVew;
import com.example.a51044.myfirstapp.presenter.PresenterImpl;
import com.example.a51044.myfirstapp.utils.Contacts;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：${小强}<p>
 * <p>创建时间：2019/1/115:25<p>
 * <p>更改时间：2019/1/115:25<p>
 * <p>版本号：1<p>
 */
public class MySelfActivity<T> extends BaseActivity implements IVew<T> {
    //private String mUrl="http://172.17.8.100/small/circle/verify/v1/findMyCircleById?count=10&pager=";
    private int index=1;
    private List<MyCircle.ResultBean>mList=new ArrayList<>();

    private XRecyclerView my_self_circle_recy;
    private MyCircleAdapter myCircleAdapter;
    private PresenterImpl presenter;
    private HashMap<String, String> map = new HashMap<>();

    @Override
    protected int getLayout() {
        return R.layout.my_self_circle;
    }

    @Override
    protected void initViews() {

        my_self_circle_recy=findViewById(R.id.my_self_circle_recy);

        GridLayoutManager manager = new GridLayoutManager(this, 1);
        my_self_circle_recy.setLayoutManager(manager);

        myCircleAdapter = new MyCircleAdapter(mList, MySelfActivity.this);
        my_self_circle_recy.setAdapter(myCircleAdapter);

        my_self_circle_recy.setPullRefreshEnabled(true);
        my_self_circle_recy.setLoadingMoreEnabled(true);
        my_self_circle_recy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mList.clear();
                index = 1;
                myCircleAdapter.notifyDataSetChanged();
                presenter.startHomeShopping(Contacts.BASE_URL+Contacts.BASE_MySelf + (index), 6);
            }

            @Override
            public void onLoadMore() {
                presenter.startHomeShopping(Contacts.BASE_URL+Contacts.BASE_MySelf + (++index), 6);
            }
        });

    }

    @Override
    protected void setOnclick() {

    }

    @Override
    protected void logic() {
        presenter = new PresenterImpl(this);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void getSuccess(T data) {
        if(data instanceof MyCircle)
        {
            MyCircle myCircle = (MyCircle) data;
            mList.addAll(myCircle.getResult());

            my_self_circle_recy.refreshComplete();
            my_self_circle_recy.loadMoreComplete();
            myCircleAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getError(T error) {

    }
}
