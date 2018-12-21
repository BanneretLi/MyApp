package com.example.a51044.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.a51044.myapp.base.BaseActivity;

public class MainActivity extends BaseActivity {

    private TextView inte;
    private int index=4;
    private Handler myHandler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==0)
            {
                index--;
                inte.setText(index+"秒跳转");
                if(index==1)
                {
                    Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                    startActivity(intent);
                }
            }
            sendEmptyMessageDelayed(0,1000);
        }
    };


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        inte = (TextView) findViewById(R.id.inte);
    }

    @Override
    protected void setObclick() {
        inte.setOnClickListener(this);
    }

    @Override
    protected void logic() {
        myHandler.sendEmptyMessageDelayed(0,1000);
    }

    @Override
    public void onClick(View v) {

    }
}
