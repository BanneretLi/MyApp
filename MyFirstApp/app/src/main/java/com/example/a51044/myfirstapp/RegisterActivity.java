package com.example.a51044.myfirstapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a51044.myfirstapp.base.BaseActivity;
import com.example.a51044.myfirstapp.bean.MyRegister;
import com.example.a51044.myfirstapp.ivew.IVew;
import com.example.a51044.myfirstapp.presenter.PresenterImpl;
import com.example.a51044.myfirstapp.utils.Contacts;
import com.xw.repo.XEditText;

import java.util.HashMap;

public class RegisterActivity<T> extends BaseActivity implements IVew<T> {

    private XEditText register_pager_name;
    private XEditText register_pager_verify;
    private XEditText register_pager_pwd;

    private TextView register_pager_get_verify;
    private TextView register_pager_back;
    private Button register_pager_register;

    private PresenterImpl presenter;
    private HashMap<String,String>map=new HashMap<>();

    @Override
    protected int getLayout() {
        return R.layout.register_pager;
    }

    @Override
    protected void initViews() {

        register_pager_name=findViewById(R.id.register_pager_name);
        register_pager_pwd=findViewById(R.id.register_pager_pwd);
        register_pager_get_verify=findViewById(R.id.register_pager_get_verify);
        register_pager_verify=findViewById(R.id.register_pager_verify);

        register_pager_back=findViewById(R.id.register_pager_back);
        register_pager_register=findViewById(R.id.register_pager_register);
    }

    @Override
    protected void setOnclick() {
        register_pager_get_verify.setOnClickListener(this);
        register_pager_back.setOnClickListener(this);
        register_pager_register.setOnClickListener(this);
    }

    @Override
    protected void logic() {
        presenter=new PresenterImpl(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.register_pager_get_verify:
                break;

            case R.id.register_pager_back:
                finish();
                break;

            case R.id.register_pager_register:
                String register_name=register_pager_name.getTrimmedString().trim();
                String register_pwd=register_pager_pwd.getTrimmedString().trim();
                map.put("phone",register_name);
                map.put("pwd",register_pwd);
                presenter.startLogin(Contacts.BASE_URL+Contacts.BASE_Register,map,2);
                break;
        }
    }


    @Override
    public void getSuccess(T data) {
        MyRegister myRegister= (MyRegister) data;
        if(myRegister.getMessage().equals("注册成功"))
        {
            Toast.makeText(this,myRegister.getMessage(), Toast.LENGTH_SHORT).show();
            finish();
        }
        else
        {
            Toast.makeText(this, myRegister.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getError(T error) {

    }
}
