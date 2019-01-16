package com.example.a51044.myfirstapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a51044.myfirstapp.base.BaseActivity;
import com.example.a51044.myfirstapp.ivew.IVew;
import com.example.a51044.myfirstapp.presenter.Presenter;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * <p>文件描述：<p>
 * <p>作者：${小强}<p>
 * <p>创建时间：2019/1/1421:23<p>
 * <p>更改时间：2019/1/1421:23<p>
 * <p>版本号：1<p>
 */
public class GoTalkActivity<T> extends BaseActivity implements IVew<T> {
    @BindView(R.id.go_to_talk_img)
    ImageView goToTalkImg;
    @BindView(R.id.go_to_talk_name)
    TextView goToTalkName;
    @BindView(R.id.go_to_talk_price)
    TextView goToTalkPrice;
    @BindView(R.id.go_to_talk_edit)
    EditText goToTalkEdit;
    @BindView(R.id.go_to_talk_cream)
    ImageView goToTalkCream;
    @BindView(R.id.go_to_talk_check)
    CheckBox goToTalkCheck;
    @BindView(R.id.go_to_talk_send)
    Button goToTalkSend;

    private int userId;
    private String sessionId;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Presenter presenter;
    private HashMap<String,String>map=new HashMap<>();


    @Override
    protected int getLayout() {
        return R.layout.go_to_talk;
    }

    @Override
    protected void initViews() {
        ButterKnife.bind(this);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        SharedPreferences lgq = this.getSharedPreferences("lgq", Context.MODE_PRIVATE);
        userId = lgq.getInt("userId", 0);
        sessionId = lgq.getString("sessionId", "");
    }

    @Override
    protected void setOnclick() {

    }

    @Override
    protected void logic() {

    }

    @Override
    public void onClick(View v) {

    }


    @OnClick({R.id.go_to_talk_cream, R.id.go_to_talk_check, R.id.go_to_talk_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.go_to_talk_cream:
                break;
            case R.id.go_to_talk_check:
                break;
            case R.id.go_to_talk_send:
                //map.put("")
                break;
        }
    }

    @Override
    public void getSuccess(T data) {

    }

    @Override
    public void getError(T error) {

    }
}
