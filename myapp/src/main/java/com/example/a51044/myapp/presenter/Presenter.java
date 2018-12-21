package com.example.a51044.myapp.presenter;

import java.util.Map;

public interface Presenter {
    void startLogin(String url, Map<String,String>map);

    void startData(String url);
}
