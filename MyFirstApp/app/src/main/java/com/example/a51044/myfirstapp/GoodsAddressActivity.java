package com.example.a51044.myfirstapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a51044.myfirstapp.adapter.MyHomeAdapter;
import com.example.a51044.myfirstapp.base.BaseActivity;
import com.example.a51044.myfirstapp.bean.MyAddress;
import com.example.a51044.myfirstapp.adapter.MyAddressAdapter;
import com.example.a51044.myfirstapp.bean.MyDefault;
import com.example.a51044.myfirstapp.ivew.IVew;
import com.example.a51044.myfirstapp.presenter.PresenterCancel;
import com.example.a51044.myfirstapp.presenter.PresenterImpl;
import com.example.a51044.myfirstapp.utils.Contacts;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * <p>文件描述：<p>
 * <p>作者：${小强}<p>
 * <p>创建时间：2019/1/1019:57<p>
 * <p>更改时间：2019/1/1019:57<p>
 * <p>版本号：1<p>
 */
public class GoodsAddressActivity<T> extends BaseActivity implements IVew<T> {


    @BindView(R.id.goods_address_finish)
    TextView goodsAddressFinish;
    @BindView(R.id.goods_address_add)
    Button goodsAddressAdd;
    private XRecyclerView goods_address_recy;

    private MyAddressAdapter myAddressAdapter;
    private HashMap<String,String>map=new HashMap<>();



    private PresenterImpl presenter;
    private List<MyAddress.ResultBean> mList = new ArrayList<>();

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int userId;
    private String sessionId;
    private PresenterCancel presenterCancel;
    private int j;
    private CheckBox Check;

    @Override
    protected int getLayout() {
        return R.layout.goods_address;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();

        SharedPreferences lgq = GoodsAddressActivity.this.getSharedPreferences("lgq", Context.MODE_PRIVATE);
        userId = lgq.getInt("userId", 0);
        sessionId = lgq.getString("sessionId", "");

        goods_address_recy=findViewById(R.id.goods_address_recy);
        goods_address_recy.setPullRefreshEnabled(true);
        goods_address_recy.setLoadingMoreEnabled(true);


        GridLayoutManager manager=new GridLayoutManager(this,1);
        goods_address_recy.setLayoutManager(manager);

        myAddressAdapter=new MyAddressAdapter(mList,this);
        goods_address_recy.setAdapter(myAddressAdapter);

    }

    @Override
    protected void setOnclick() {
        myAddressAdapter.setCheckItem(new MyAddressAdapter.CheckItem() {
            @Override
            public void setItemCheck(View view, int i) {
                Check=view.findViewById(R.id.my_address_radio);
                Log.d("zzz",i+"");
                map.put("id",String.valueOf(i));

                presenterCancel.startLogin(Contacts.BASE_URL+Contacts.BASE_Default_Address,map,15);

            }
        });

    }

    @Override
    protected void logic() {
        presenter=new PresenterImpl(this);
        presenterCancel=new PresenterCancel(this);
        map.put("userId",String.valueOf(userId));
        map.put("sessionId",sessionId);
        presenter.startLogin(Contacts.BASE_URL+Contacts.BASE_QueryAddress,map,14);
    }

    @Override
    public void onClick(View v) {

    }


    @OnClick({R.id.goods_address_finish, R.id.goods_address_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.goods_address_finish:
                finish();
                break;
            case R.id.goods_address_add:
                Intent intent = new Intent("com.example.a51044.AddGoodsAddressActivity.ACTION_START");
                startActivity(intent);
                break;
        }
    }


    @Override
    public void getSuccess(T data) {
        if(data instanceof  MyAddress)
        {
            MyAddress myAddress= (MyAddress) data;
            mList.addAll(myAddress.getResult());
            myAddressAdapter.notifyDataSetChanged();
        }
        if(data instanceof MyDefault)
        {
            MyDefault myDefault= (MyDefault) data;
            if(myDefault.getMessage().equals("设置成功"))
            {
                myAddressAdapter.notifyDataSetChanged();
                Toast.makeText(this, myDefault.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }


    }

    @Override
    public void getError(T error) {

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mList.clear();
        presenter.startLogin(Contacts.BASE_URL+Contacts.BASE_QueryAddress,map,14);
    }
}
