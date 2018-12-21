package com.example.a51044.mymoni.model;

import com.example.a51044.mymoni.callback.MyCallBack;
import com.example.a51044.mymoni.callback.MyCallBackXList;

public interface Model{
    void getData(String user,String pwd,MyCallBack callBack);
    void getXList(String url,MyCallBackXList callBack);
}
