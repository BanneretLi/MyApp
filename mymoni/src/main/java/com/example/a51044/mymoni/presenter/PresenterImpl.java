package com.example.a51044.mymoni.presenter;

import com.example.a51044.mymoni.bean.MyData;
import com.example.a51044.mymoni.callback.MyCallBack;
import com.example.a51044.mymoni.callback.MyCallBackXList;
import com.example.a51044.mymoni.model.ModelImpl;
import com.example.a51044.mymoni.view.IVew;
import com.example.a51044.mymoni.view.IVewXlist;

import java.util.List;

public class PresenterImpl implements Presenter {
    private ModelImpl model;
    private IVew iVew;
    private IVewXlist iVewXlist;

    public PresenterImpl(IVew iVew) {
        this.iVew = iVew;
        model=new ModelImpl();
    }

    public PresenterImpl(IVewXlist iVewXlist) {
        this.iVewXlist = iVewXlist;
        model=new ModelImpl();
    }

    @Override
    public void start(String user, String pwd) {
        model.getData(user,pwd,new MyCallBack() {
            @Override
            public void success(String name, String pwd) {
                iVew.getData(name,pwd);
            }

            @Override
            public void error(String error) {
                iVew.getError(error);
            }


        });
    }

    @Override
    public void startXList(String url) {
        model.getXList(url, new MyCallBackXList() {
            @Override
            public void sucessXlist(List<MyData.DataBean> dataBeans) {
                iVewXlist.getXList(dataBeans);
            }

            @Override
            public void error(String error) {
                iVewXlist.getError(error);
            }
        });
    }
}
