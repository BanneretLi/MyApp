package com.example.a51044.myapp.model;

import android.os.Handler;
import android.os.Message;

import com.example.a51044.myapp.bean.Login;
import com.example.a51044.myapp.bean.MyData;
import com.example.a51044.myapp.callback.MyCallBack;
import com.example.a51044.myapp.utils.OkHttpUtils;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ModelImpl implements Model{

    private MyCallBack callBack;
    private Handler myHandler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Gson gson=new Gson();
            if(msg.what==0)
            {
                try {
                    String jsonStr= (String) msg.obj;

                    JSONObject jsonObject=new JSONObject(jsonStr);
                    String data=jsonObject.optString("data");
                    if(data!=null)
                    {
                        Login login=new Login();
                        login.setCode(jsonObject.optString("code"));
                        login.setMsg(jsonObject.optString("msg"));
                        callBack.getData(login);
                    }
                    else
                    {

                        Login login = gson.fromJson(jsonStr, Login.class);
                        callBack.getData(login);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if(msg.what==1)
            {
                String s= (String) msg.obj;
                MyData data = gson.fromJson(s, MyData.class);
                callBack.getData(data);
            }
        }
    };

    @Override
    public void setSuccessLogin(String url, final Map<String, String> map, MyCallBack callBack) {
        this.callBack=callBack;
        OkHttpUtils.getInstance().asyncPost(url, (HashMap<String, String>) map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                    String s=response.body().string();
                    myHandler.sendMessage(myHandler.obtainMessage(0,s));

            }
        });
    }

    @Override
    public void setErrorLogin(String error) {

    }

    @Override
    public void setData(String url, MyCallBack callBack) {
        this.callBack=callBack;
        OkHttpUtils.getInstance().getAsync(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s=response.body().string();
                myHandler.sendMessage(myHandler.obtainMessage(1,s));
            }
        });
    }
}
