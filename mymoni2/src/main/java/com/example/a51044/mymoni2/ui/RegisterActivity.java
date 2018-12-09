package com.example.a51044.mymoni2.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a51044.mymoni2.R;
import com.example.a51044.mymoni2.base.BaseActivity;
import com.example.a51044.mymoni2.bean.Login;
import com.example.a51044.mymoni2.bean.RegisterBean;
import com.example.a51044.mymoni2.ivew.IVew;
import com.example.a51044.mymoni2.presenter.PresenterImpl;

public class RegisterActivity<T> extends BaseActivity implements IVew<T> {
    private EditText register_name;
    private EditText register_pwd;
    private EditText register_pwd_again;
    private Button register;

    private PresenterImpl presenter;

    private String mUrl="http://120.27.23.105/user/reg";

    @Override
    protected int getLayout() {
        return R.layout.register;
    }

    @Override
    protected void initViews() {
        register_name=findViewById(R.id.register_name);
        register_pwd=findViewById(R.id.register_pwd);
        register_pwd_again=findViewById(R.id.register_pwd_again);
        register=findViewById(R.id.register);
    }

    @Override
    protected void setOnclick() {
        register.setOnClickListener(this);
    }

    @Override
    protected void logic() {
        presenter=new PresenterImpl(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.register:
                String name=register_name.getText().toString().trim();
                String pwd=register_pwd.getText().toString().trim();
                String pwd_again=register_pwd_again.getText().toString().trim();
                if(pwd.equals(pwd_again))
                {
                    if(name.isEmpty()||pwd.isEmpty())
                    {
                        Toast.makeText(RegisterActivity.this,"账号和密码不能为空哦！",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        presenter.startLogin(mUrl,name,pwd);
                        finish();
                    }
                }
                else
                {
                    Toast.makeText(RegisterActivity.this,"两次的密码不正确哟！",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void success(T user) {
        Login b= (Login) user;
        if(b.getCode().equals("1"))
        {
            Toast.makeText(RegisterActivity.this,b.getMsg(),Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(RegisterActivity.this,b.getMsg(),Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void error(T error) {
        Login b= (Login) error;
        Toast.makeText(RegisterActivity.this,b.getMsg(),Toast.LENGTH_SHORT).show();
    }
}
