package com.example.a51044.mymoni.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.a51044.mymoni.R;
import com.example.a51044.mymoni.base.BaseActivity;
import com.example.a51044.mymoni.fragment.MyList;
import com.example.a51044.mymoni.fragment.MyQRCode;

public class Main2Activity extends BaseActivity {


    private RadioButton rb1;
    private RadioButton rb2;
    private RadioGroup rg;
    private FragmentManager manager;

    @Override
    protected int getLayout() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initView() {
        rb1=findViewById(R.id.rb1);
        rb2=findViewById(R.id.rb2);
        rg=findViewById(R.id.rg);
        manager=getSupportFragmentManager();

    }

    @Override
    protected void setOnclick() {
        rb1.setOnClickListener(this);
        rb2.setOnClickListener(this);

    }

    @Override
    protected void logic() {
        manager.beginTransaction().replace(R.id.frg,new MyList()).commit();
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.rb1:
                        manager.beginTransaction().replace(R.id.frg,new MyList()).commit();
                        break;
                    case R.id.rb2:
                        manager.beginTransaction().replace(R.id.frg,new MyQRCode()).commit();
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

    }


}
