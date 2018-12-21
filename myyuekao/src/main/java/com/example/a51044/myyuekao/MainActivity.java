package com.example.a51044.myyuekao;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.a51044.myyuekao.adapter.MyPagerAdapter;
import com.example.a51044.myyuekao.base.BaseActivity;
import com.example.a51044.myyuekao.ivew.IVew;
import com.example.a51044.myyuekao.presenter.PresenterImpl;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity  {

    private ViewPager vp;
    private String mUrl="http://www.zhaoapi.cn/ad/getAd";
    private List<View>mImage=new ArrayList<>();
    private PresenterImpl presenter;
    private int index=0;
    private MyPagerAdapter myPagerAdapter;
    private Button btn1;
    private Handler myHandler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(index!=mImage.size())
            {
                index++;
                vp.setCurrentItem(index);
                sendEmptyMessageDelayed(0,1000);
            }
            else
            {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }

        }
    };


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        vp=findViewById(R.id.vp);

    }

    @Override
    protected void setOnclick() {

    }

    @Override
    protected void logic() {
        initData();
        myPagerAdapter=new MyPagerAdapter(mImage,MainActivity.this);
        vp.setAdapter(myPagerAdapter);

        myHandler.sendEmptyMessageDelayed(0,1000);
    }

    private void initData() {
        mImage.add(View.inflate(MainActivity.this,R.layout.pager1,null));
        mImage.add(View.inflate(MainActivity.this,R.layout.pager2,null));
        mImage.add(View.inflate(MainActivity.this,R.layout.pager3,null));
    }

    @Override
    public void onClick(View v) {

    }


}
