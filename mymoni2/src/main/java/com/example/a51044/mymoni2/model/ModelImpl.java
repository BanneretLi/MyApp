package com.example.a51044.mymoni2.model;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.example.a51044.mymoni2.bean.Login;
import com.example.a51044.mymoni2.bean.MyData;
import com.example.a51044.mymoni2.callback.MyCallBack;
import com.example.a51044.mymoni2.ivew.IVew;
import com.example.a51044.mymoni2.utils.HttpUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ModelImpl implements LoginModel {
    private MyCallBack callBack;

    private Handler myHandler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            try {
            String jsonStr= (String) msg.obj;
            Gson gson=new Gson();
            JSONObject object = new JSONObject(jsonStr);
            String data=object.optString("data");
            if(data!=null)
            {
                Login login=new Login();
                login.setCode(object.optString("code"));
                login.setMsg(object.optString("msg"));
                callBack.setLoginSuccess(login);
            }
            else
            {
                Login login=gson.fromJson(jsonStr,Login.class);
                callBack.setLoginSuccess(login);
            }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };

    @Override
    public void getLogin(final String url, final String name, final String pwd, final MyCallBack callBack) {
        this.callBack=callBack;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String post = HttpUtils.post(url, name, pwd);
                    myHandler.sendMessage(myHandler.obtainMessage(0,post));
                } catch (Exception e) {
                    Looper.prepare();
                    callBack.setLoginError("异常");
                    Looper.loop();
                }
            }
        }).start();
    }

    @Override
    public void setData(String url, MyCallBack callBack) {
        this.callBack=callBack;
        new MyTask().execute(url);
    }

    @Override
    public void setError(String error) {
        callBack.setLoginError(error);
    }

    class MyTask extends AsyncTask<String,Void,List<MyData.DataBean>>
    {

        @Override
        protected List<MyData.DataBean> doInBackground(String... strings) {
            try {
                String s = HttpUtils.get(strings[0]);
                Gson gson=new Gson();
                MyData data = gson.fromJson(s, MyData.class);
                return data.getData();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<MyData.DataBean> dataBeans) {
            super.onPostExecute(dataBeans);
            callBack.setLoginSuccess(dataBeans);
        }
    }

}
