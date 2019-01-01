package model;

import com.bwei.okhttp.OkHttpUtils;
import com.google.gson.Gson;

import beans.Goods;
import http.HttpConfig;

/**
 * 作者：穆佳琪
 * 时间：2018/12/30 9:07.
 */

public class MainModel {
    public void getData(String keywords, int page,final IMainModelCallBack iMainModelCallBack){
        //http://www.zhaoapi.cn/product/searchProducts?keywords=笔记本&page=1
        OkHttpUtils okHttpUtils = OkHttpUtils.getoKhttpUtils();
        okHttpUtils.doGet(HttpConfig.news_url + "?keywords=" + keywords + "&page=" + page, new OkHttpUtils.IOKhttpUtilsCallback() {
            @Override
            public void onFailure(String error) {
                if(iMainModelCallBack!=null) {
                    iMainModelCallBack.getFail(error);
                }
            }

            @Override
            public void onResponse(String json) {
                Goods goods = new Gson().fromJson(json, Goods.class);
                String code = goods.getCode();
                if(code.equals("0")){
                    if(iMainModelCallBack!=null){
                        iMainModelCallBack.getSuccess(goods);
                    }
                }else{
                    if(iMainModelCallBack!=null){
                        iMainModelCallBack.getFail("未获取到数据");
                    }
                }
            }
        });

    }

    public interface IMainModelCallBack{
        void getSuccess(Goods goods);
        void getFail(String error);
    }
}
