package com.example.a51044.myapp.callback;

public interface MyCallBack<T> {
    void getData(T data);
    void getError(T error);
}
