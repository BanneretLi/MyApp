package com.example.a51044.myyuekao.model;

import com.example.a51044.myyuekao.callback.MyCallBack;

public interface Model {
    void setData(String url, int type, MyCallBack callBack);
    void setError(String error);
}
