package com.example.a51044.mymoni2.model;

import com.example.a51044.mymoni2.callback.MyCallBack;

public interface LoginModel {
    void getLogin(String url,String name,String pwd,MyCallBack callBack);
    void setData(String url, MyCallBack callBack);
    void setError(String error);
}
