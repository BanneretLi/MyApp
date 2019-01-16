package com.example.a51044.myfirstapp.fragment_child;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.a51044.myfirstapp.R;
import com.example.a51044.myfirstapp.adapter.MyKeyWordAdapter;
import com.example.a51044.myfirstapp.base.BaseFragment;
import com.example.a51044.myfirstapp.bean.MyKeyWord;
import com.example.a51044.myfirstapp.eventbus.EventMessage;
import com.example.a51044.myfirstapp.fragment_main.FragmentMySelf;
import com.example.a51044.myfirstapp.ivew.IVew;
import com.example.a51044.myfirstapp.presenter.PresenterImpl;
import com.example.a51044.myfirstapp.utils.Contacts;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：${小强}<p>
 * <p>创建时间：2019/1/310:35<p>
 * <p>更改时间：2019/1/310:35<p>
 * <p>版本号：1<p>
 */
public class Fragment_Main<T> extends BaseFragment implements IVew<T> {
    private View view;
    private XRecyclerView home_serch_recy;
    private List<MyKeyWord.ResultBean> mList = new ArrayList<>();
    private PresenterImpl presenter;
    private int index = 1;
    private String mMessage;
    private MyKeyWordAdapter myKeyWordAdapter;


    @Override
    protected void initData() {

    }

    @Override
    protected void findViewById(View view) {
        home_serch_recy = view.findViewById(R.id.home_serch_recy);

        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        home_serch_recy.setLayoutManager(manager);

        myKeyWordAdapter = new MyKeyWordAdapter(mList, getActivity());
        home_serch_recy.setAdapter(myKeyWordAdapter);
        home_serch_recy.setPullRefreshEnabled(true);
        home_serch_recy.setLoadingMoreEnabled(true);

        home_serch_recy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mList.clear();
                myKeyWordAdapter.notifyDataSetChanged();
                index = 1;
                presenter.startHomeShopping(Contacts.BASE_URL+Contacts.BASE_KEYWORD + (index) + "&keyword=" + mMessage, 5);


            }

            @Override
            public void onLoadMore() {
                presenter.startHomeShopping(Contacts.BASE_URL+Contacts.BASE_KEYWORD + (++index) + "&keyword=" + mMessage, 5);
            }
        });
    }

    @Override
    protected void getNextData() {
        EventBus.getDefault().register(Fragment_Main.this);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void progressLogic() {
        presenter = new PresenterImpl(this);
        presenter.startHomeShopping(Contacts.BASE_URL+Contacts.BASE_KEYWORD + (index++) + "&keyword=" + mMessage, 5);

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getInfo(String message) {
        this.mMessage = message;
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected View getLoadView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.home_search, null);
        return view;
    }



    @Override
    public void getSuccess(T data) {
        MyKeyWord myKeyWord = (MyKeyWord) data;
        mList.addAll(myKeyWord.getResult());

        myKeyWordAdapter.notifyDataSetChanged();
        home_serch_recy.refreshComplete();
        home_serch_recy.loadMoreComplete();
    }

    @Override
    public void getError(T error) {

    }
}
