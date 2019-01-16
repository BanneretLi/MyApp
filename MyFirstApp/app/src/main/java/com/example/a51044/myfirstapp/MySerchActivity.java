package com.example.a51044.myfirstapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a51044.myfirstapp.adapter.MyKeyWordAdapter;
import com.example.a51044.myfirstapp.base.BaseActivity;
import com.example.a51044.myfirstapp.bean.MyKeyWord;
import com.example.a51044.myfirstapp.bean.MyShopping;
import com.example.a51044.myfirstapp.ivew.IVew;
import com.example.a51044.myfirstapp.presenter.PresenterCancel;
import com.example.a51044.myfirstapp.presenter.PresenterImpl;
import com.example.a51044.myfirstapp.utils.Contacts;
import com.example.a51044.myfirstapp.weight.MySerchView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：${小强}<p>
 * <p>创建时间：2019/1/716:02<p>
 * <p>更改时间：2019/1/716:02<p>
 * <p>版本号：1<p>
 */
public class MySerchActivity<T> extends BaseActivity implements IVew<T> {

    private XRecyclerView home_serch_recy;
    private List<MyKeyWord.ResultBean> mList = new ArrayList<>();
    private PresenterImpl presenter;
    private PresenterCancel presenterCancel;
    private int index = 1;
    private MyKeyWordAdapter myKeyWordAdapter;
    private String name;
    private MySerchView mySerchView;
    private EditText editText;
    private int flag=0;
    private ImageView cannot_find_img;
    private TextView cannot_find_tv;
    private TextView home_serch_tv2;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int userId;
    private String sessionId;
    private HashMap<String,String>map=new HashMap<>();
    @Override
    protected int getLayout() {
        return R.layout.home_search;
    }

    @Override
    protected void initViews() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();

        SharedPreferences lgq = this.getSharedPreferences("lgq", Context.MODE_PRIVATE);
        userId = lgq.getInt("userId", 0);
        sessionId = lgq.getString("sessionId", "");
        Intent intent=getIntent();
        name = intent.getStringExtra("name");
        cannot_find_img=findViewById(R.id.cannot_find_img);
        home_serch_tv2=findViewById(R.id.home_serch_tv2);
        cannot_find_tv=findViewById(R.id.cannot_find_tv);
        editText=findViewById(R.id.my_view_zuhe_serch);
        mySerchView=findViewById(R.id.home_serch_view);
        home_serch_recy = findViewById(R.id.home_serch_recy);

        GridLayoutManager manager = new GridLayoutManager(MySerchActivity.this, 2);
        home_serch_recy.setLayoutManager(manager);

        myKeyWordAdapter = new MyKeyWordAdapter(mList, MySerchActivity.this);
        home_serch_recy.setAdapter(myKeyWordAdapter);
        home_serch_recy.setPullRefreshEnabled(true);
        home_serch_recy.setLoadingMoreEnabled(true);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void setOnclick() {
        mySerchView.getButton().setOnClickListener(this);
        home_serch_recy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mList.clear();
                myKeyWordAdapter.notifyDataSetChanged();
                index = 1;
                if(flag==1)
                {
                    home_serch_tv2.setVisibility(View.GONE);
                    presenter.startHomeShopping(Contacts.BASE_URL+Contacts.BASE_KEYWORD + (index) + "&keyword=" + editText.getText().toString().trim(), 5);
                }
                else
                {
                    home_serch_tv2.setVisibility(View.GONE);
                    presenter.startHomeShopping(Contacts.BASE_URL+Contacts.BASE_KEYWORD + (index) + "&keyword=" + name, 5);
                }



            }

            @Override
            public void onLoadMore() {
                home_serch_tv2.setVisibility(View.VISIBLE);
                home_serch_recy.loadMoreComplete();
            }
        });

        presenterCancel=new PresenterCancel(this);
        myKeyWordAdapter.setCheckItem(new MyKeyWordAdapter.CheckItem() {
            @Override
            public void setItemCheck(View view, String name, int price, String icon, int id) {
                map.put("userId",String.valueOf(userId));
                map.put("sessionId",sessionId);
                map.put("data","{commodityId:"+id+",count:"+1+"}");
                presenterCancel.startLogin(Contacts.BASE_URL+Contacts.BASE_Sync,map,11);
            }
        });


    }

    @Override
    protected void logic() {

        presenter = new PresenterImpl(this);
        presenter.startHomeShopping(Contacts.BASE_URL+Contacts.BASE_KEYWORD + (index++) + "&keyword=" + name, 5);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.my_view_zuhe_add:

                flag=1;
                presenter.startHomeShopping(Contacts.BASE_URL+Contacts.BASE_KEYWORD + (index++) + "&keyword=" + editText.getText().toString().trim(), 5);
                break;
        }
    }

    @Override
    public void getSuccess(T data) {
        if(data instanceof MyKeyWord) {
            mList.clear();

            MyKeyWord myKeyWord = (MyKeyWord) data;
            mList.addAll(myKeyWord.getResult());
            if (mList.size() == 0) {
                cannot_find_img.setVisibility(View.VISIBLE);
                cannot_find_tv.setVisibility(View.VISIBLE);
            } else {
                cannot_find_img.setVisibility(View.GONE);
                cannot_find_tv.setVisibility(View.GONE);
            }
            myKeyWordAdapter.notifyDataSetChanged();
            home_serch_recy.refreshComplete();
        }


            if(data instanceof MyShopping)
            {
                MyShopping shopping= (MyShopping) data;
                if(shopping.getStatus().equals("0000"))
                {
                    Toast.makeText(this, shopping.getMessage(), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this, shopping.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

    }

    @Override
    public void getError(T error) {

    }
}
