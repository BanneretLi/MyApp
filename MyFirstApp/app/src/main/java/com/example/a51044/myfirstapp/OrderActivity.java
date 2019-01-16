package com.example.a51044.myfirstapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a51044.myfirstapp.adapter.MyQueryGoodsAdapter;
import com.example.a51044.myfirstapp.adapter.MypopAddress;
import com.example.a51044.myfirstapp.base.BaseActivity;
import com.example.a51044.myfirstapp.bean.MyAddress;
import com.example.a51044.myfirstapp.bean.MyFabouls;
import com.example.a51044.myfirstapp.bean.MyRegister;
import com.example.a51044.myfirstapp.bean.QueryGoods;
import com.example.a51044.myfirstapp.eventbus.EventMessage;
import com.example.a51044.myfirstapp.ivew.IVew;
import com.example.a51044.myfirstapp.presenter.PresenterCancel;
import com.example.a51044.myfirstapp.presenter.PresenterImpl;
import com.example.a51044.myfirstapp.utils.Contacts;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：${小强}<p>
 * <p>创建时间：2019/1/1015:26<p>
 * <p>更改时间：2019/1/1015:26<p>
 * <p>版本号：1<p>
 */
public class OrderActivity<T> extends BaseActivity implements IVew<T> {
    private List<QueryGoods.ResultBean> mList = new ArrayList<>();

    private RecyclerView order_layout_recy;
    private RecyclerView address_pop_recy;
    private TextView order_layout_tv;
    private Button order_layout_btn;
    private MyQueryGoodsAdapter myQueryGoodsAdapter;

    private MypopAddress myAddressAdapter;
    private HashMap<String, String> map = new HashMap<>();
    private PresenterImpl presenter;
    private List<MyAddress.ResultBean> mData = new ArrayList<>();
    private PresenterCancel presenterCancel;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int userId;
    private String sessionId;


    private int addressId;
    private int goodsId;
    private int goodsNum;
    private LinearLayout order_layout_visbility;
    private LinearLayout order_gone;
    private View inflate;
    private PopupWindow popupWindow;
    private double price=0.00;

    private RelativeLayout order_layout_lin;
    private TextView order_layout_name;
    private TextView order_layout_phone;
    private TextView order_layout_count;
    private TextView order_layout_id;

    @Override
    protected int getLayout() {
        return R.layout.order_layout;
    }

    @Override
    protected void initViews() {
        order_layout_name=findViewById(R.id.order_layout_name);
        order_layout_phone=findViewById(R.id.order_layout_phone);
        order_layout_count=findViewById(R.id.order_layout_count);
        order_layout_id=findViewById(R.id.order_layout_id);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();

        inflate = LayoutInflater.from(OrderActivity.this).inflate(R.layout.address_pop, null);
        address_pop_recy = inflate.findViewById(R.id.address_pop_recy);
        LinearLayoutManager manager2 = new LinearLayoutManager(this);
        address_pop_recy.setLayoutManager(manager2);

        myAddressAdapter = new MypopAddress(mData, this);
        address_pop_recy.setAdapter(myAddressAdapter);
        SharedPreferences lgq = OrderActivity.this.getSharedPreferences("lgq", Context.MODE_PRIVATE);
        userId = lgq.getInt("userId", 0);
        sessionId = lgq.getString("sessionId", "");
        order_layout_lin = findViewById(R.id.order_layout_lin);
        order_layout_recy = findViewById(R.id.order_layout_recy);


        EventBus.getDefault().register(this);
        order_layout_visbility = findViewById(R.id.order_layout_visbility);
        order_gone = findViewById(R.id.order_gone);

        order_layout_tv = findViewById(R.id.order_layout_tv);
        order_layout_tv.setText("共" + mList.size() + "件商品，需付款"+price+"元");
        order_layout_btn = findViewById(R.id.order_layout_btn);
        order_layout_btn.setOnClickListener(this);

        GridLayoutManager manager = new GridLayoutManager(this, 1);
        order_layout_recy.setLayoutManager(manager);

        myQueryGoodsAdapter = new MyQueryGoodsAdapter(mList, this);
        order_layout_recy.setAdapter(myQueryGoodsAdapter);


        for(int i=0;i<mList.size();i++)
        {
            price+=mList.get(i).getCount()*mList.get(i).getPrice();
        }


    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getInfo(Object message) {
        this.mList.addAll((List<QueryGoods.ResultBean>) message);
    }


    @Override
    protected void setOnclick() {
        order_layout_visbility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.startLogin(Contacts.BASE_URL+Contacts.BASE_QueryAddress, map, 14);
                popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, 400, true);
                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAsDropDown(order_layout_lin);
                order_layout_visbility.setVisibility(View.GONE);
                order_gone.setVisibility(View.VISIBLE);
            }
        });

        order_gone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.startLogin(Contacts.BASE_URL+Contacts.BASE_QueryAddress, map, 14);
                popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, 400, true);
                popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAsDropDown(order_layout_lin);
                order_layout_visbility.setVisibility(View.GONE);
                order_gone.setVisibility(View.VISIBLE);
            }
        });

        myAddressAdapter.setCheckItem(new MypopAddress.CheckItem() {
            @Override
            public void setItemCheckItem(View view, int i,int j) {
                addressId=j;
                popupWindow.dismiss();
                order_layout_name.setText(mData.get(i).getRealName());
                order_layout_phone.setText(mData.get(i).getPhone());
                order_layout_count.setText(mData.get(i).getAddress());
            }
        });


    }

    @Override
    protected void logic() {
        presenter = new PresenterImpl(this);
        map.put("userId", String.valueOf(userId));
        map.put("sessionId", sessionId);
        presenter.startLogin(Contacts.BASE_URL+Contacts.BASE_QueryAddress, map, 14);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.order_layout_btn:
                JSONArray jsonArray=new JSONArray();
                for(int i=0;i<mList.size();i++)
                {
                    try {
                        JSONObject jsonObject=null;
                        jsonObject=new JSONObject();
                        jsonObject.put("commodityId",mList.get(i).getCommodityId());
                        jsonObject.put("amount",mList.get(i).getCount());
                        jsonArray.put(jsonObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                map.put("userId",String.valueOf(userId));
                map.put("sessionId",sessionId);
                map.put("orderInfo",jsonArray.toString());
                map.put("totalPrice",price+"");
                map.put("addressId", String.valueOf(addressId));
                presenter.startLogin(Contacts.BASE_URL+Contacts.BASE_CreateList, map, 7);
                break;
        }
    }

    @Override
    public void getSuccess(T data) {
        if (data instanceof MyAddress) {
            MyAddress myAddress = (MyAddress) data;
            mData.addAll(myAddress.getResult());
            myAddressAdapter.notifyDataSetChanged();
        }
        if(data instanceof MyFabouls)
        {
            MyFabouls myRegister= (MyFabouls) data;
            Log.d("zzz", "getSuccess() returned: " );
            if(myRegister.getStatus().equals("0000"))
            {
                Toast.makeText(this, myRegister.getMessage(), Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, myRegister.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void getError(T error) {

    }

}
