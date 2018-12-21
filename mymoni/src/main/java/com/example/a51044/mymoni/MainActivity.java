package com.example.a51044.mymoni;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.a51044.mymoni.base.BaseActivity;
import com.example.a51044.mymoni.fragment.MyQRCode;
import com.example.a51044.mymoni.presenter.PresenterImpl;
import com.example.a51044.mymoni.ui.Main2Activity;
import com.example.a51044.mymoni.view.IVew;
import com.squareup.picasso.Picasso;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

public class MainActivity extends BaseActivity implements IVew {

    private EditText My_Phone_Number;
    private EditText My_Phone_Number_pwd;
    private Button login;
    private Button myLogin;

    private CheckBox remeber_pwd;
    private CheckBox remeber_login;

    private PresenterImpl presenter;
    private String a;
    private String b;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        My_Phone_Number = (EditText) findViewById(R.id.My_Phone_Number);
        My_Phone_Number_pwd = (EditText) findViewById(R.id.My_Phone_Number_pwd);
        myLogin = findViewById(R.id.myLogin);
        login = findViewById(R.id.login);
        remeber_pwd = findViewById(R.id.remeber_pwd);
        remeber_login = findViewById(R.id.remeber_login);
        pref = getSharedPreferences("lgq", MODE_PRIVATE);
        editor = pref.edit();
    }

    @Override
    protected void setOnclick() {
        login.setOnClickListener(this);
        remeber_pwd.setOnClickListener(this);
        remeber_login.setOnClickListener(this);
        myLogin.setOnClickListener(this);
    }

    @Override
    protected void logic() {
        presenter = new PresenterImpl(this);
        String user = pref.getString("user", "");
        String pwd = pref.getString("pwd", "");
        My_Phone_Number.setText(user);
        boolean rember = pref.getBoolean("rember", false);
        boolean login = pref.getBoolean("login", false);
        if (rember) {
            remeber_pwd.setChecked(true);
            My_Phone_Number_pwd.setText(pwd);
        }
        if (login) {
            remeber_login.setChecked(true);
            presenter.start(user, pwd);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                a = My_Phone_Number.getText().toString().trim();
                b = My_Phone_Number_pwd.getText().toString().trim();
                presenter.start(a, b);
                break;

            case R.id.remeber_pwd:
                if (remeber_pwd.isChecked()) {
                    editor.putBoolean("rember", true);
                    a = My_Phone_Number.getText().toString().trim();
                    editor.putString("user", a);
                    editor.commit();
                } else {
                    editor.remove("rember");
                    editor.remove("login");
                    editor.remove("pwd");
                    editor.commit();
                    remeber_login.setChecked(false);
                }
                break;
            case R.id.remeber_login:
                if (remeber_login.isChecked()) {
                    editor.putBoolean("remeber", true);
                    editor.putBoolean("login", true);
                    editor.commit();
                    remeber_pwd.setChecked(true);
                } else {
                    editor.remove("login");
                    editor.commit();
                }
                break;

            case R.id.myLogin:
                UMShareAPI.get(this).getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ,authListener);
                break;
        }
    }

    @Override
    public void getData(String user, String pwd) {
        a = My_Phone_Number.getText().toString().trim();
        b = My_Phone_Number_pwd.getText().toString().trim();
        editor.putString("user", a);
        editor.putString("pwd", b);
        editor.commit();
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent("com.example.a51044.mymoni.ACTION_START");
        startActivity(intent);
        finish();
    }

    @Override
    public void getError(String error) {
        a = My_Phone_Number.getText().toString().trim();
        editor.putString("user", a);
        editor.putString("pwd", "");
        editor.remove("login");
        My_Phone_Number_pwd.setText("");
        editor.commit();
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }



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

            Toast.makeText(MainActivity.this, "成功了", Toast.LENGTH_LONG).show();

        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(MainActivity.this, "失败：" + t.getMessage(),Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(MainActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

}
