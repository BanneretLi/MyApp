package com.example.a51044.mymoni2.callback;

public interface MyCallBack <T> {
    void setLoginSuccess(T user);
    void setLoginError(T error);
}
