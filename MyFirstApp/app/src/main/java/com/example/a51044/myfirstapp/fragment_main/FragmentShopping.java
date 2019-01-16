package com.example.a51044.myfirstapp.fragment_main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a51044.myfirstapp.OrderActivity;
import com.example.a51044.myfirstapp.R;
import com.example.a51044.myfirstapp.adapter.MyQueryGoodsAdapter;
import com.example.a51044.myfirstapp.base.BaseFragment;
import com.example.a51044.myfirstapp.bean.QueryGoods;
import com.example.a51044.myfirstapp.eventbus.EventMessage;
import com.example.a51044.myfirstapp.ivew.IVew;
import com.example.a51044.myfirstapp.presenter.PresenterImpl;
import com.example.a51044.myfirstapp.utils.Contacts;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FragmentShopping<T> extends BaseFragment implements IVew<T>,View.OnClickListener {
    private View view;
    private XRecyclerView myhome_shopping_recy;

    private List<QueryGoods.ResultBean> mList = new ArrayList<>();
    private List<QueryGoods.ResultBean>mData=new ArrayList<>();

    private MyQueryGoodsAdapter myQueryGoodsAdapter;
    private PresenterImpl presenter;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String sessionId;
    private int userId;
    private Button shopping_item_num;
    private HashMap<String, String> map = new HashMap<>();


    //private String mUrl = "http://172.17.8.100/small/order/verify/v1/findShoppingCart";

    private CheckBox myhome_shopping_btn;
    private TextView myhome_shopping_price;
    private Button myhome_shopping_settlement;


    @Override
    protected void initData() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = sharedPreferences.edit();
        SharedPreferences lgq = getActivity().getSharedPreferences("lgq", Context.MODE_PRIVATE);
        userId = lgq.getInt("userId", 0);
        sessionId = lgq.getString("sessionId", "");

    }

    @Override
    protected void findViewById(View view) {
        myhome_shopping_btn=view.findViewById(R.id.myhome_shopping_btn);
        myhome_shopping_price=view.findViewById(R.id.myhome_shopping_price);
        myhome_shopping_btn.setOnClickListener(this);
        myhome_shopping_settlement=view.findViewById(R.id.myhome_shopping_settlement);
        myhome_shopping_settlement.setOnClickListener(this);
        myhome_shopping_recy = view.findViewById(R.id.myhome_shopping_recy);
        myhome_shopping_recy.setPullRefreshEnabled(true);

        GridLayoutManager manager = new GridLayoutManager(getActivity(), 1);
        myhome_shopping_recy.setLayoutManager(manager);

        myQueryGoodsAdapter = new MyQueryGoodsAdapter(mList, getActivity());
        myhome_shopping_recy.setAdapter(myQueryGoodsAdapter);
    }

    @Override
    protected void getNextData() {

    }

    @Override
    protected void setListener() {
        myhome_shopping_recy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mList.clear();
                myQueryGoodsAdapter.notifyDataSetChanged();
                map.put("userId", String.valueOf(userId));
                map.put("sessionId", sessionId);
                presenter.startLogin(Contacts.BASE_URL+Contacts.BASE_Shopping, map, 12);
            }

            @Override
            public void onLoadMore() {
                map.put("userId", String.valueOf(userId));
                map.put("sessionId", sessionId);
                presenter.startLogin(Contacts.BASE_URL+Contacts.BASE_Shopping, map, 12);
            }
        });

    }

    @Override
    protected void progressLogic() {
        presenter = new PresenterImpl(this);
        map.put("userId", String.valueOf(userId));
        map.put("sessionId", sessionId);
        presenter.startLogin(Contacts.BASE_URL+Contacts.BASE_Shopping, map, 12);

        myQueryGoodsAdapter.setCallback(new MyQueryGoodsAdapter.AdapterCallback() {
            @Override
            public void setChildCheck(View v,int i) {
                boolean checked = myQueryGoodsAdapter.isChecked(i);
                myQueryGoodsAdapter.setChildCheck(i,!checked);
                flushBottomLayout();
                myQueryGoodsAdapter.notifyDataSetChanged();
            }



        });
    }

    @Override
    protected View getLoadView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.myhome_shopping, null);
        return view;
    }

    @Override
    public void getSuccess(T data) {
        QueryGoods queryGoods = (QueryGoods) data;
        mList.addAll(queryGoods.getResult());
        myQueryGoodsAdapter.notifyDataSetChanged();
    }

    @Override
    public void getError(T error) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.myhome_shopping_btn:
                boolean checked = myQueryGoodsAdapter.isCheckAll();
                myQueryGoodsAdapter.checkAll(!checked);
                flushBottomLayout();
                myQueryGoodsAdapter.notifyDataSetChanged();

                break;
            case R.id.myhome_shopping_settlement:
                Intent intent=new Intent(getActivity(),OrderActivity.class);
                mData.clear();
                for(int i=0;i<mList.size();i++)
                {
                    if(myQueryGoodsAdapter.isChecked(i))
                    {
                        mData.add(mList.get(i));
                    }

                }
                EventBus.getDefault().postSticky(mData);

                startActivity(intent);
                break;
        }
    }


    public void flushBottomLayout()
    {
        boolean allGoods=myQueryGoodsAdapter.isCheckAll();
        myhome_shopping_btn.setChecked(allGoods);

        float allGoodsPrice=myQueryGoodsAdapter.getShopPrice();
        myhome_shopping_price.setText("￥合计："+allGoodsPrice+".00");

    }
}
