package com.example.a51044.myfirstapp;

import android.content.Context;
import android.content.Intent;


import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import android.widget.TextView;
import android.widget.Toast;

import com.example.a51044.myfirstapp.base.BaseActivity;
import com.example.a51044.myfirstapp.bean.MyLogin;
import com.example.a51044.myfirstapp.ivew.IVew;
import com.example.a51044.myfirstapp.presenter.PresenterImpl;
import com.example.a51044.myfirstapp.utils.Contacts;
import com.xw.repo.XEditText;


import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class LoginActivity<T> extends BaseActivity implements IVew<T> {

    private XEditText login_pager_name;
    private XEditText login_pager_pwd;
    private CheckBox login_pager_check;
    private TextView login_pager_register;
    private Button login_pager_login;

    public SharedPreferences shared;
    public SharedPreferences.Editor editor;
    private PresenterImpl presenter;
    private HashMap<String ,String>map=new HashMap<>();



    @Override
    protected int getLayout() {
        return R.layout.login_pager;
    }

    @Override
    protected void initViews() {
        shared=getSharedPreferences("lgq",Context.MODE_PRIVATE);
        editor=shared.edit();
        login_pager_name = findViewById(R.id.login_pager_name);
        login_pager_pwd =  findViewById(R.id.login_pager_pwd);
        login_pager_check = (CheckBox) findViewById(R.id.login_pager_check);
        login_pager_register = (TextView) findViewById(R.id.login_pager_register);
        login_pager_login = (Button) findViewById(R.id.login_pager_login);
        boolean a=shared.getBoolean("remeber",false);
        if(a)
        {
            String getName = shared.getString("name", "");
            String getPwd=shared.getString("pwd","");
            login_pager_name.setText(getName);
            login_pager_pwd.setText(getPwd);
            login_pager_check.setChecked(true);
        }


    }

    @Override
    protected void setOnclick() {
        login_pager_register.setOnClickListener(this);
        login_pager_login.setOnClickListener(this);



        login_pager_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    String name=login_pager_name.getTrimmedString().trim();
                    boolean isOk=isChinaPhoneLegal(name);
                    if(name.length()!=11&&isOk!=true)
                    {
                        Toast.makeText(LoginActivity.this, "不合格", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



        login_pager_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    String name1=login_pager_name.getTrimmedString().trim();
                    String pwd1=login_pager_pwd.getTrimmedString().trim();
                    editor.putBoolean("remeber",true);
                    editor.putString("name",name1);
                    editor.putString("pwd",pwd1);
                    editor.commit();

                }
                else
                {
                    editor.clear();
                    editor.commit();
                }
            }
        });


    }

    @Override
    protected void logic() {
        presenter=new PresenterImpl(this);

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_pager_login:
                String name=login_pager_name.getTrimmedString().trim();
                String pwd=login_pager_pwd.getTrimmedString().trim();
                map.put("phone",name);
                map.put("pwd",pwd);
                presenter.startLogin(Contacts.BASE_URL+Contacts.BASE_Login,map,1);
                break;

            case R.id.login_pager_register:
                startActivity(new Intent("com.example.a51044.myfirstapp.ACTION_START"));
                break;

        }
    }



    @Override
    public void getSuccess(T data) {
        MyLogin myLogin= (MyLogin) data;
        if(myLogin.getMessage().equals("登录成功"))
        {
            Toast.makeText(this,myLogin.getResult().getSessionId()+"",Toast.LENGTH_SHORT).show();
            editor.putInt("userId",myLogin.getResult().getUserId());
            editor.putString("sessionId",myLogin.getResult().getSessionId());
            Log.d("zzz",myLogin.getResult().getUserId()+"");
            Log.d("zzz",myLogin.getResult().getSessionId()+"");
            editor.commit();
            startActivity(new Intent("com.example.a51044.MyHomeActivity.ACTION_START"));
        }
        else
        {
            Toast.makeText(this,myLogin.getResult().getUserId(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getError(T error) {

    }



    public static boolean isChinaPhoneLegal(String str)
            throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(147,145))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
