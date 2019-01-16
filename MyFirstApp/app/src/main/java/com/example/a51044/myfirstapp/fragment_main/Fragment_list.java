package com.example.a51044.myfirstapp.fragment_main;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.a51044.myfirstapp.R;
import com.example.a51044.myfirstapp.base.BaseFragment;
import com.example.a51044.myfirstapp.fragment_child.AllOrderFragment;
import com.example.a51044.myfirstapp.fragment_child.WaitGoodsFragment;
import com.example.a51044.myfirstapp.fragment_child.WaitPayFragment;
import com.example.a51044.myfirstapp.fragment_child.WaitTalkFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Fragment_list extends BaseFragment {
    @BindView(R.id.all_order)
    LinearLayout allOrder;
    @BindView(R.id.pay)
    LinearLayout pay;
    @BindView(R.id.wait_goods)
    LinearLayout waitGoods;
    @BindView(R.id.evaluate)
    LinearLayout evaluate;
    @BindView(R.id.myhome_list_finish)
    LinearLayout myhomeListFinish;
    Unbinder unbinder;

    private FragmentManager manager;

    private View view;

    @Override
    protected void initData() {

    }

    @Override
    protected void findViewById(View view) {

        manager=getChildFragmentManager();
        manager.beginTransaction().replace(R.id.list_frg,new AllOrderFragment()).commit();
    }

    @Override
    protected void getNextData() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void progressLogic() {

    }

    @Override
    protected View getLoadView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.myhome_list, null);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.all_order, R.id.pay, R.id.wait_goods, R.id.evaluate, R.id.myhome_list_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.all_order:
                manager.beginTransaction().replace(R.id.list_frg,new AllOrderFragment()).commit();
                break;
            case R.id.pay:
                manager.beginTransaction().replace(R.id.list_frg,new WaitPayFragment()).commit();
                break;
            case R.id.wait_goods:
                manager.beginTransaction().replace(R.id.list_frg,new WaitGoodsFragment()).commit();
                break;
            case R.id.evaluate:
                manager.beginTransaction().replace(R.id.list_frg,new WaitTalkFragment()).commit();
                break;
            case R.id.myhome_list_finish:
                manager.beginTransaction().replace(R.id.list_frg,new AllOrderFragment()).commit();
                break;
        }
    }
}
