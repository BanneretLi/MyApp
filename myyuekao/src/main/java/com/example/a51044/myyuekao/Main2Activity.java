package com.example.a51044.myyuekao;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.a51044.myyuekao.adapter.MyGrideAdapter;
import com.example.a51044.myyuekao.base.BaseActivity;
import com.example.a51044.myyuekao.bean.GrideData;
import com.example.a51044.myyuekao.fragment.FragmentA;
import com.example.a51044.myyuekao.fragment.FragmentB;
import com.example.a51044.myyuekao.fragment.FragmentC;
import com.example.a51044.myyuekao.ivew.IVew;
import com.example.a51044.myyuekao.presenter.PresenterImpl;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity<T> extends BaseActivity implements IVew<T> {

    private RecyclerView recycl_one;
    private TextView tv1;
    private TextView tv2;
    private List<GrideData.DataBean>mList=new ArrayList<>();
    private String mUrl="http://www.zhaoapi.cn/product/getCatagory";
    private MyGrideAdapter myGrideAdapter;
    private PresenterImpl presenter;
    private FragmentManager fmanager;
    private ImageView my_code;
    private boolean boo=true;

    @Override
    protected int getLayout() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initViews() {

        my_code=findViewById(R.id.my_code);
        recycl_one = (RecyclerView) findViewById(R.id.recycl_one);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);


        GridLayoutManager manager=new GridLayoutManager(this,5);
        recycl_one.setLayoutManager(manager);

        myGrideAdapter=new MyGrideAdapter(mList,Main2Activity.this);
        recycl_one.setAdapter(myGrideAdapter);

    }

    @Override
    protected void setOnclick() {
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        my_code.setOnClickListener(this);

    }

    @Override
    protected void logic() {
        presenter=new PresenterImpl(this);
        presenter.startData(mUrl,1);
        fmanager=getSupportFragmentManager();
        fmanager.beginTransaction().replace(R.id.frg,new FragmentA()).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.tv1:
                Toast.makeText(Main2Activity.this,"111",Toast.LENGTH_SHORT).show();
                fmanager.beginTransaction().replace(R.id.frg,new FragmentA()).commit();
                break;
            case R.id.tv2:
                fmanager.beginTransaction().replace(R.id.frg,new FragmentB()).commit();
                break;
            case R.id.my_code:

                if(boo==true)
                {
                    fmanager.beginTransaction().replace(R.id.frg,new FragmentC()).commit();
                    boo=false;
                }
                else if(boo==false)
                {
                    fmanager.beginTransaction().replace(R.id.frg,new FragmentA()).commit();
                    boo=true;
                }

                break;
        }
    }

    @Override
    public void getData(T data) {

        GrideData m= (GrideData) data;
        mList.addAll(m.getData());
        myGrideAdapter.notifyDataSetChanged();
    }

    @Override
    public void getError(T error) {

    }
}
