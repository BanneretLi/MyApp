package com.example.a51044.mymoni2.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.a51044.mymoni2.R;
import com.example.a51044.mymoni2.base.BaseActivity;
import com.example.a51044.mymoni2.fragment.MyListFragment;
import com.example.a51044.mymoni2.fragment.MyQRCode;

public class LoginSuccessActivity extends BaseActivity {
    private ViewPager vp;
    private FrameLayout frg;
    private RadioButton myData;
    private RadioButton myQRCode;
    private RadioGroup rg;

    private FragmentManager manager;

    @Override
    protected int getLayout() {
        return R.layout.login_success;
    }

    @Override
    protected void initViews() {
        vp=findViewById(R.id.vp);
        frg=findViewById(R.id.frg);
        myData=findViewById(R.id.myData);
        myQRCode=findViewById(R.id.myQRCode);
        rg=findViewById(R.id.rg);
        manager=getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frg,new MyListFragment()).commit();
    }

    @Override
    protected void setOnclick() {
        myData.setOnClickListener(this);
        myQRCode.setOnClickListener(this);

    }

    @Override
    protected void logic() {
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.myData:
                        manager.beginTransaction().replace(R.id.frg,new MyListFragment()).commit();
                        break;

                    case R.id.myQRCode:
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
