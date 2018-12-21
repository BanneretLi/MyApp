package com.example.a51044.myyuekao.model;

import android.os.Handler;
import android.os.Message;

import com.example.a51044.myyuekao.bean.GrideData;
import com.example.a51044.myyuekao.bean.LinData;
import com.example.a51044.myyuekao.bean.MyData;
import com.example.a51044.myyuekao.bean.NewsData;
import com.example.a51044.myyuekao.callback.MyCallBack;
import com.example.a51044.myyuekao.utils.OkHttpUtils;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ModelImpl implements Model {
    private MyCallBack callBack;
    private int type;
    private Handler myHandler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String s= (String) msg.obj;
            Gson gson=new Gson();
            switch (type)
            {
                case 1:
                    GrideData data = gson.fromJson(s, GrideData.class);
                    callBack.success(data);
                    break;
                case 2:
                    LinData data1=gson.fromJson(s,LinData.class);
                    callBack.success(data1);
                case 3:
                    LinData data2=gson.fromJson(s,LinData.class);
                    callBack.success(data2);
                    break;
            }
        }
    };
    @Override
    public void setData(String url, int type, MyCallBack callBack) {
        this.callBack=callBack;
        this.type=type;
        OkHttpUtils.getInstance().getAsync(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                myHandler.sendMessage(myHandler.obtainMessage(0,response.body().string()));
            }
        });
    }

    @Override
    public void setError(String error) {

    }
}
