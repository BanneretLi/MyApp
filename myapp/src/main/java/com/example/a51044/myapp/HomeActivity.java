package com.example.a51044.myapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.a51044.myapp.adapter.MyPagerAdapter;
import com.example.a51044.myapp.base.BaseActivity;
import com.example.a51044.myapp.fragment.FragmentA;
import com.example.a51044.myapp.fragment.FragmentB;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity {
    private ImageView back;
    private EditText serch_Edit;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private RadioGroup rg;
    private ViewPager vp;

    private MyPagerAdapter myPagerAdapter;

    private List<Fragment>mList=new ArrayList<>();



    @Override
    protected int getLayout() {
        return R.layout.activity_main3;
    }

    @Override
    protected void initViews() {
        back = findViewById(R.id.back);
        serch_Edit = findViewById(R.id.serch_Edit);
        rb1 = (RadioButton) findViewById(R.id.rb1);
        rb2 = (RadioButton) findViewById(R.id.rb2);
        rb3 = (RadioButton) findViewById(R.id.rb3);
        rb4 = (RadioButton) findViewById(R.id.rb4);
        rg = (RadioGroup) findViewById(R.id.rg);
        vp = (ViewPager) findViewById(R.id.vp);
        initData();

    }

    @Override
    protected void setObclick() {
        rg.setOnClickListener(this);
    }

    @Override
    protected void logic() {

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.rb1:
                        vp.setCurrentItem(0);
                        break;

                    case R.id.rb2:
                        vp.setCurrentItem(1);
                        break;

                    case R.id.rb3:
                        vp.setCurrentItem(2);
                        break;

                    case R.id.rb4:
                        vp.setCurrentItem(4);
                        break;
                }
            }
        });

        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if(i==0)
                {
                    rg.check(R.id.rb1);

                }
                else if(i==1)
                {
                    rg.check(R.id.rb2);
                }
                else if(i==2)
                {
                    rg.check(R.id.rb3);
                }
                else
                {
                    rg.check(R.id.rb4);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    private void initData()
    {
        mList.add(new FragmentA());
        mList.add(new FragmentB());
        myPagerAdapter=new MyPagerAdapter(getSupportFragmentManager(),mList);
        vp.setAdapter(myPagerAdapter);
    }

}
