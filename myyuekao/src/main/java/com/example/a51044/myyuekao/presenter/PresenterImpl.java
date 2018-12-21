package com.example.a51044.myyuekao.presenter;

import com.example.a51044.myyuekao.callback.MyCallBack;
import com.example.a51044.myyuekao.ivew.IVew;
import com.example.a51044.myyuekao.model.Model;
import com.example.a51044.myyuekao.model.ModelImpl;

public class PresenterImpl implements Presenter {
    private ModelImpl model;
    private IVew iVew;

    public PresenterImpl(IVew iVew) {
        this.iVew = iVew;
        model=new ModelImpl();
    }

    @Override
    public void startData(String url, int type) {
        model.setData(url, type, new MyCallBack() {
            @Override
            public void success(Object data) {
                iVew.getData(data);
            }

            @Override
            public void error(Object eror) {
                iVew.getError(eror);
            }
        });
    }
}
