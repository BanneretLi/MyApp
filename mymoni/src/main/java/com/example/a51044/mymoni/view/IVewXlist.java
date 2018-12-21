package com.example.a51044.mymoni.view;

import com.example.a51044.mymoni.bean.MyData;

import java.util.List;

public interface IVewXlist {
    void getXList(List<MyData.DataBean> dataBeans);
    void getError(String error);
}
