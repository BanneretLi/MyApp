package com.example.a51044.myfirstapp.presenter;

import java.util.HashMap;
import java.util.Map;

public interface Presenter {
    void startLogin(String url, HashMap<String ,String> map, int type);
    void startHomeShopping(String url,int type);
}
