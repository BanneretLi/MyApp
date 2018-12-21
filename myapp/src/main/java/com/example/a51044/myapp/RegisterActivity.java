package com.example.a51044.myapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a51044.myapp.base.BaseActivity;
import com.example.a51044.myapp.bean.Login;
import com.example.a51044.myapp.ivew.IVew;
import com.example.a51044.myapp.presenter.PresenterImpl;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity<T> extends BaseActivity implements IVew<T> {
    private String mUrl = "http://120.27.23.105/user/reg";
    private PresenterImpl presenter;
    private Map<String, String> map = new HashMap<>();
    private EditText register_name;
    private EditText register_pwd;
    private Button register_btn;


    @Override
    protected int getLayout() {
        return R.layout.register;
    }

    @Override
    protected void initViews() {
        register_name=findViewById(R.id.register_name);
        register_pwd=findViewById(R.id.register_pwd);
        register_btn=findViewById(R.id.register_btn);

    }

    @Override
    protected void setObclick() {
        register_btn.setOnClickListener(this);
    }

    @Override
    protected void logic() {
        presenter=new PresenterImpl(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.register_btn:
                String name=register_name.getText().toString().trim();
                String pwd=register_pwd.getText().toString().trim();
                if(name.isEmpty()||pwd.isEmpty())
                {
                    Toast.makeText(RegisterActivity.this,"注册的账号或密码不能为空",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    map.put("mobile",name);
                    map.put("password",pwd);
                    presenter.startLogin(mUrl,map);
                }
                break;

        }
    }

    @Override
    public void getLoginData(T data) {
        Login login= (Login) data;
        if(login.getCode().equals("1"))
        {
            Toast.makeText(RegisterActivity.this,login.getMsg()+"122",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(RegisterActivity.this,login.getMsg()+"522",Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    @Override
    public void getLoginError(T error) {

    }

}
