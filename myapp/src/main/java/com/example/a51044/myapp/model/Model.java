package com.example.a51044.myapp.model;

import com.example.a51044.myapp.callback.MyCallBack;

import java.util.Map;

public interface Model {
    void setSuccessLogin(String url, Map<String,String>map, MyCallBack callBack);
    void setErrorLogin(String error);

    void setData(String url,MyCallBack callBack);
}
