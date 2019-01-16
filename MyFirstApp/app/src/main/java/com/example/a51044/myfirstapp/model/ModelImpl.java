package com.example.a51044.myfirstapp.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.a51044.myfirstapp.bean.AddAddress;
import com.example.a51044.myfirstapp.bean.MyAddress;
import com.example.a51044.myfirstapp.bean.MyAllList;
import com.example.a51044.myfirstapp.bean.MyCancelFabouls;
import com.example.a51044.myfirstapp.bean.MyCircle;
import com.example.a51044.myfirstapp.bean.MyDefault;
import com.example.a51044.myfirstapp.bean.MyFabouls;
import com.example.a51044.myfirstapp.bean.MyListOne;
import com.example.a51044.myfirstapp.bean.MyListTwo;
import com.example.a51044.myfirstapp.bean.MyHome;
import com.example.a51044.myfirstapp.bean.MyKeyWord;
import com.example.a51044.myfirstapp.bean.MyLogin;
import com.example.a51044.myfirstapp.bean.MyRegister;
import com.example.a51044.myfirstapp.bean.MyShopping;
import com.example.a51044.myfirstapp.bean.MySlideshow;
import com.example.a51044.myfirstapp.bean.QueryGoods;
import com.example.a51044.myfirstapp.callback.MyCallBack;
import com.example.a51044.myfirstapp.utils.OkHttpUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

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
            if(msg.what==0)
            {
                Gson gson=new Gson();
                String s= (String) msg.obj;
                if(type==1)
                {
                    MyLogin login = gson.fromJson(s, MyLogin.class);
                    callBack.success(login);
                }
                if(type==2)
                {
                    MyRegister myRegister=gson.fromJson(s,MyRegister.class);
                    callBack.success(myRegister);
                }
                if(type==3)
                {
                    MyHome myHome=gson.fromJson(s,MyHome.class);
                    callBack.success(myHome);
                }

                if(type==4)
                {
                    MySlideshow slideshow = gson.fromJson(s, MySlideshow.class);
                    callBack.success(slideshow);
                }
                if(type==5)
                {
                    MyKeyWord myKeyWord = gson.fromJson(s, MyKeyWord.class);
                    callBack.success(myKeyWord);
                }
                if(type==6)
                {
                    MyCircle myCircle=gson.fromJson(s,MyCircle.class);
                    callBack.success(myCircle);
                }
                if(type==7)
                {
                    MyFabouls myFabouls=gson.fromJson(s,MyFabouls.class);
                    callBack.success(myFabouls);
                }
                if(type==8)
                {
                    MyCancelFabouls myCancelFabouls=gson.fromJson(s,MyCancelFabouls.class);
                    callBack.success(myCancelFabouls);
                }
                if(type==9)
                {
                    MyListOne myListOne = gson.fromJson(s, MyListOne.class);
                    callBack.success(myListOne);
                }
                if(type==10)
                {
                    MyListTwo myListTwo = gson.fromJson(s, MyListTwo.class);
                    callBack.success(myListTwo);
                }
                if(type==11)
                {
                    MyShopping myShopping=gson.fromJson(s,MyShopping.class);
                    callBack.success(myShopping);
                }
                if(type==12)
                {
                    QueryGoods goods = gson.fromJson(s, QueryGoods.class);
                    callBack.success(goods);
                }
                if(type==13)
                {
                    AddAddress addAddress=gson.fromJson(s,AddAddress.class);
                    callBack.success(addAddress);
                }
                if(type==14)
                {
                    MyAddress myAddress=gson.fromJson(s,MyAddress.class);
                    callBack.success(myAddress);
                }
                if(type==15)
                {
                    MyDefault myDefault=gson.fromJson(s,MyDefault.class);
                    callBack.success(myDefault);
                }
                if(type==16)
                {
                    MyAllList myAllList = gson.fromJson(s, MyAllList.class);
                    callBack.success(myAllList);
                }


            }
        }
    };
    @Override
    public void LoginSuccess(String url, HashMap<String, String> map, int type, MyCallBack callBack) {
        this.callBack=callBack;
        this.type=type;
        if(type==1||type==2)
        {
            OkHttpUtils.getInstance().asyncPost(url, map, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    myHandler.sendMessage(myHandler.obtainMessage(0,response.body().string()));
                }
            });
        }

        if(type==7||type==13||type==15)
        {
            OkHttpUtils.getInstance().asyncPost2(url, map, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    myHandler.sendMessage(myHandler.obtainMessage(0,response.body().string()));
                }
            });
        }
        if(type==8)
        {
            OkHttpUtils.getInstance().doDelete(url, map, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    myHandler.sendMessage(myHandler.obtainMessage(0,response.body().string()));
                }
            });
        }
        if(type==11)
        {
            OkHttpUtils.getInstance().doPut(url, map, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    myHandler.sendMessage(myHandler.obtainMessage(0,response.body().string()));
                }
            });
        }

        if(type==12||type==14||type==16)
        {
            OkHttpUtils.getInstance().getAsync2(url, map, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    myHandler.sendMessage(myHandler.obtainMessage(0,response.body().string()));
                }
            });
        }


    }

    @Override
    public void LoginError(String error) {

    }

    @Override
    public void HomeMessage(String url, int type, MyCallBack callBack) {
        this.type=type;
        this.callBack=callBack;
        if (type==3||type==4||type==5||type==6||type==9||type==10)
        {
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

    }
}
