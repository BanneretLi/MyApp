package com.example.a51044.mymoni2.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    protected abstract int getLayout();

    protected abstract void initViews();

    protected abstract void setOnclick();

    protected abstract void logic();

    void init()
    {
        if(getLayout()!=0)
        {
            setContentView(getLayout());
            initViews();
            setOnclick();
            logic();
        }

    }
}
