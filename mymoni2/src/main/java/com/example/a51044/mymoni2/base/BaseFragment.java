package com.example.a51044.mymoni2.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment implements View.OnClickListener{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView(inflater,container);
    }
    protected abstract void initData();
    
    protected abstract void findViewById(View view);
    
    protected abstract void setOnclick();
    
    protected abstract void logic();
    View initView(LayoutInflater inflater, ViewGroup container)
    {
        View loading=getLoadView(inflater,container);
        if(loading!=null)
        {
            findViewById(loading);
            initData();
            setOnclick();
            logic();
        }
        return loading;
    }

    protected abstract View getLoadView(LayoutInflater inflater, ViewGroup container);
}
