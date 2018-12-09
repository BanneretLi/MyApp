package com.example.a51044.mymoni2.presenter;

import com.example.a51044.mymoni2.callback.MyCallBack;
import com.example.a51044.mymoni2.ivew.IVew;
import com.example.a51044.mymoni2.model.ModelImpl;

public class PresenterImpl implements LoginPresenter{

    private ModelImpl model;
    private IVew iVew;

    public PresenterImpl(IVew iVew) {
        this.iVew = iVew;
        model=new ModelImpl();
    }




    @Override
    public void startLogin(String url, String name, String pwd) {
        model.getLogin(url, name, pwd, new MyCallBack() {
            @Override
            public void setLoginSuccess(Object user) {
                iVew.success(user);
            }

            @Override
            public void setLoginError(Object error) {
                iVew.error(error);
            }
        });
    }

    @Override
    public void startXlist(String url) {
        model.setData(url, new MyCallBack() {
            @Override
            public void setLoginSuccess(Object user) {
                iVew.success(user);
            }

            @Override
            public void setLoginError(Object error) {
                iVew.error(error);
            }
        });
    }
}
