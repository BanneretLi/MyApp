package com.example.a51044.mymoni.model;

import android.content.Intent;
import android.os.AsyncTask;

import com.example.a51044.mymoni.bean.MyData;
import com.example.a51044.mymoni.callback.MyCallBack;
import com.example.a51044.mymoni.callback.MyCallBackXList;
import com.example.a51044.mymoni.utils.HttpUtils;
import com.google.gson.Gson;

import java.util.List;

public class ModelImpl implements Model {

    private MyCallBackXList callBack;

    @Override
    public void getData(String user, String pwd, MyCallBack callBack) {
        if(callBack!=null&&user.equals("13436712762")&&pwd.equals("2385638977lgq"))
        {
            callBack.success(user,pwd);
        }
        else
        {
            callBack.error("密码错误");
        }
    }

    @Override
    public void getXList(String url, MyCallBackXList callBack) {
        this.callBack=callBack;
        new MyTask().execute(url);
    }


    class MyTask extends AsyncTask<String,Void,List<MyData.DataBean>>
    {

        @Override
        protected List<MyData.DataBean> doInBackground(String... strings) {
            try {
                String string = HttpUtils.getString(strings[0]);
                Gson gson=new Gson();
                MyData data = gson.fromJson(string, MyData.class);
                return data.getData();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(List<MyData.DataBean> dataBeans) {
            super.onPostExecute(dataBeans);
            callBack.sucessXlist(dataBeans);
        }
    }
}
