package com.example.a51044.myapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a51044.myapp.base.BaseActivity;
import com.example.a51044.myapp.bean.Login;
import com.example.a51044.myapp.ivew.IVew;
import com.example.a51044.myapp.presenter.PresenterImpl;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main2Activity<T> extends BaseActivity implements IVew<T> {


    private EditText login_name;
    private EditText login_pwd;
    private CheckBox remeber_pwd;
    private CheckBox remeber_login;
    private Button login;
    private Button register;
    private Button He_login;
    private PresenterImpl presenter;
    private String mUrl="http://120.27.23.105/user/login";

    private Map<String,String>map=new HashMap<>();
    private Context mContext;



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                String name=login_name.getText().toString().trim();
                String pwd=login_pwd.getText().toString().trim();
                if(name.isEmpty()||pwd.isEmpty())
                {
                    Toast.makeText(Main2Activity.this,"账号密码不能为空",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    map.put("mobile",name);
                    map.put("password",pwd);
                    presenter.startLogin(mUrl,map);
                }
                break;
            case R.id.register:
                Intent intent=new Intent(Main2Activity.this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.He_login:
                UMShareAPI.get(this).getPlatformInfo(Main2Activity.this, SHARE_MEDIA.QQ,authListener);
                break;
        }
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initViews() {
        login_name = (EditText) findViewById(R.id.login_name);
        login_pwd = (EditText) findViewById(R.id.login_pwd);
        remeber_pwd = (CheckBox) findViewById(R.id.remeber_pwd);
        remeber_login = (CheckBox) findViewById(R.id.remeber_login);
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        He_login = (Button) findViewById(R.id.He_login);
        mContext=Main2Activity.this;
    }

    @Override
    protected void setObclick() {
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        He_login.setOnClickListener(this);
    }

    @Override
    protected void logic() {
        presenter=new PresenterImpl(this);
    }

    @Override
    public void getLoginData(T data) {
        Login login= (Login) data;
        if(login.getCode().equals("1"))
        {
            Toast.makeText(Main2Activity.this,login.getMsg(),Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(Main2Activity.this,login.getMsg(),Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Main2Activity.this,HomeActivity.class));
        }


    }

    @Override
    public void getLoginError(T error) {

    }

    //登录的监听
    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {

            Toast.makeText(mContext, "成功了", Toast.LENGTH_LONG).show();

        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(mContext, "失败：" + t.getMessage(),Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(mContext, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}
