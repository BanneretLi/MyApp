package com.example.a51044.mymoni2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.a51044.mymoni2.base.BaseActivity;
import com.example.a51044.mymoni2.bean.Login;
import com.example.a51044.mymoni2.ivew.IVew;
import com.example.a51044.mymoni2.presenter.PresenterImpl;
import com.example.a51044.mymoni2.ui.LoginSuccessActivity;
import com.example.a51044.mymoni2.ui.RegisterActivity;

public class MainActivity<T> extends BaseActivity implements IVew<T> {


    private EditText login_name;
    private EditText login_pwd;
    private Button login;
    private Button add_login;
    private PresenterImpl presenter;
    private Button heLogin;
    private String mUrl="http://120.27.23.105/user/login";

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        login_name=findViewById(R.id.login_name);
        login_pwd=findViewById(R.id.login_pwd);
        login=findViewById(R.id.login);
        add_login=findViewById(R.id.add_login);
        heLogin=findViewById(R.id.heLogin);
    }

    @Override
    protected void setOnclick() {
        login.setOnClickListener(this);
        add_login.setOnClickListener(this);
        heLogin.setOnClickListener(this);
    }

    @Override
    protected void logic() {
        presenter=new PresenterImpl(this);
    }

    @Override
    public void success(T user) {
        Login login= (Login) user;
        if(login.getCode().equals("1"))
        {
            Toast.makeText(MainActivity.this,login.getMsg(),Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(MainActivity.this,login.getMsg(),Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(MainActivity.this,LoginSuccessActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void error(T error) {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.login:
                String name=login_name.getText().toString().trim();
                String pwd=login_pwd.getText().toString().trim();
                if(name.isEmpty()||pwd.isEmpty())
                {
                    Toast.makeText(MainActivity.this,"账号或密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    presenter.startLogin(mUrl,name,pwd);
                }
                break;

            case R.id.add_login:
                Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}
