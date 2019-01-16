package com.example.a51044.myfirstapp.model;

import com.example.a51044.myfirstapp.callback.MyCallBack;

import java.util.HashMap;
import java.util.Map;

public interface Model {
    void LoginSuccess(String url, HashMap<String,String> map, int type, MyCallBack callBack);
    void LoginError(String error);
    void HomeMessage(String url,int type,MyCallBack callBack);


}
