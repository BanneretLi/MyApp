package com.example.a51044.myfirstapp.presenter;

import com.example.a51044.myfirstapp.R;
import com.example.a51044.myfirstapp.callback.MyCallBack;
import com.example.a51044.myfirstapp.ivew.IVew;
import com.example.a51044.myfirstapp.model.ModelImpl;

import java.util.HashMap;

public class PresenterImpl implements Presenter {
    private ModelImpl model;
    private IVew iVew;

    public PresenterImpl(IVew iVew) {
        this.iVew = iVew;
        model=new ModelImpl();
    }

    @Override
    public void startLogin(String url, HashMap<String, String> map, final int type) {
            model.LoginSuccess(url, map, type, new MyCallBack() {
                @Override
                public void success(Object data) {
                            iVew.getSuccess(data);
                }

                @Override
                public void error(Object error) {
                    iVew.getError(error);
                }
            });


    }

    @Override
    public void startHomeShopping(String url, final int type) {

            model.HomeMessage(url, type, new MyCallBack() {
                @Override
                public void success(Object data) {

                            iVew.getSuccess(data);

                    }


                @Override
                public void error(Object error) {
                    iVew.getError(error);
                }
            });


    }
}
