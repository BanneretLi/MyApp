package com.example.a51044.myapp.presenter;

import com.example.a51044.myapp.callback.MyCallBack;
import com.example.a51044.myapp.ivew.IVew;
import com.example.a51044.myapp.model.ModelImpl;

import java.util.Map;

public class PresenterImpl implements Presenter {
    private ModelImpl model;
    private IVew iVew;

    public PresenterImpl(IVew iVew) {
        this.iVew = iVew;
        model=new ModelImpl();
    }

    @Override
    public void startLogin(String url, Map<String, String> map) {
        model.setSuccessLogin(url, map, new MyCallBack() {
            @Override
            public void getData(Object data) {
                iVew.getLoginData(data);
            }

            @Override
            public void getError(Object error) {
                iVew.getLoginError(error);
            }
        });
    }

    @Override
    public void startData(String url) {
        model.setData(url, new MyCallBack() {
            @Override
            public void getData(Object data) {
                iVew.getLoginData(data);
            }

            @Override
            public void getError(Object error) {
                iVew.getLoginError(error);
            }
        });
    }
}
