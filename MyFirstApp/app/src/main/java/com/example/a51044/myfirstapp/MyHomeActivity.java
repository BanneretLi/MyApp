package com.example.a51044.myfirstapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a51044.myfirstapp.base.BaseActivity;
import com.example.a51044.myfirstapp.fragment_main.FragmentCircle;
import com.example.a51044.myfirstapp.fragment_main.FragmentHome;
import com.example.a51044.myfirstapp.fragment_main.FragmentMySelf;
import com.example.a51044.myfirstapp.fragment_main.FragmentShopping;
import com.example.a51044.myfirstapp.fragment_main.Fragment_list;
import com.xw.repo.XEditText;

import java.util.ArrayList;
import java.util.List;

public class MyHomeActivity extends BaseActivity {

    private RadioButton myhome_home;
    private RadioButton myhome_sircle;
    private ImageView myhome_shopping;
    private RadioButton myhome_list;
    private RadioButton myhome_self;
    private FragmentManager manager;
    private  FragmentHome fragmentHome;
    private FragmentCircle fragmentCircle;
    private Fragment_list fragment_list;
    private FragmentMySelf fragmentMySelf;
    private FragmentShopping fragmentShopping;

    private FragmentHome home=new FragmentHome();

    @Override
    protected int getLayout() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 实现透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        return R.layout.myhome;
    }

    @Override
    protected void initViews() {
        myhome_home=findViewById(R.id.myhome_home);
        myhome_sircle=findViewById(R.id.myhome_sircle);
        myhome_shopping=findViewById(R.id.myhome_shopping);
        myhome_list=findViewById(R.id.myhome_list);
        myhome_self=findViewById(R.id.myhome_self);
        manager = getSupportFragmentManager();

       /* fragmentHome = new FragmentHome();
        fragmentCircle = new FragmentCircle();
        fragment_list = new Fragment_list();
        fragmentMySelf = new FragmentMySelf();
        fragmentShopping = new FragmentShopping();*/
        manager.beginTransaction().replace(R.id.myhome_frg, new FragmentHome()).commit();
    }

    @Override
    protected void setOnclick() {
        myhome_home.setOnClickListener(this);
        myhome_sircle.setOnClickListener(this);
        myhome_shopping.setOnClickListener(this);
        myhome_self.setOnClickListener(this);
        myhome_list.setOnClickListener(this);
    }

    @Override
    protected void logic() {

    }

    private boolean isCircel;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.myhome_home:
                manager.beginTransaction().replace(R.id.myhome_frg, new FragmentHome()).addToBackStack(null).commit();
                break;
            case R.id.myhome_sircle:
                manager.beginTransaction().replace(R.id.myhome_frg,new FragmentCircle()).addToBackStack(null).commit();
                break;
            case R.id.myhome_list:
                manager.beginTransaction().replace(R.id.myhome_frg,new Fragment_list()).addToBackStack(null).commit();
                break;
            case R.id.myhome_self:
                manager.beginTransaction().replace(R.id.myhome_frg,new FragmentMySelf()).addToBackStack(null).commit();
                break;
            case R.id.myhome_shopping:
                manager.beginTransaction().replace(R.id.myhome_frg,new FragmentShopping()).addToBackStack(null).commit();
                break;
        }
    }

}
