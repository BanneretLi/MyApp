package com.example.a51044.mymoni.callback;

import com.example.a51044.mymoni.bean.MyData;

import java.util.List;

public interface MyCallBackXList {
    void sucessXlist(List<MyData.DataBean> dataBeans);
    void error(String error);
}
