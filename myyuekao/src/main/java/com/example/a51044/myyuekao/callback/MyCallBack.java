package com.example.a51044.myyuekao.callback;

public interface MyCallBack<T> {
    void success(T data);
    void error(T eror);
}
