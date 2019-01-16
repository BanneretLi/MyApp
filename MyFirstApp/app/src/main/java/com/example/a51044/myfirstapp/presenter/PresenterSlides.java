package com.example.a51044.myfirstapp.presenter;

import com.example.a51044.myfirstapp.callback.MyCallBack;
import com.example.a51044.myfirstapp.ivew.IVew;
import com.example.a51044.myfirstapp.model.ModelImpl;

import java.util.HashMap;

/**
 * <p>文件描述：<p>
 * <p>作者：${小强}<p>
 * <p>创建时间：2018/12/2919:44<p>
 * <p>更改时间：2018/12/2919:44<p>
 * <p>版本号：1<p>
 */
public class PresenterSlides implements Presenter {
    private ModelImpl model;
    private IVew iVew;

    public PresenterSlides(IVew iVew) {
        this.iVew = iVew;
        model=new ModelImpl();
    }

    @Override
    public void startLogin(String url, HashMap<String, String> map, int type) {
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
    public void startHomeShopping(String url, int type) {
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
