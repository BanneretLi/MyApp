package com.example.a51044.myfirstapp;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a51044.myfirstapp.base.BaseActivity;
import com.example.a51044.myfirstapp.bean.MySerch;
import com.example.a51044.myfirstapp.dao.MyDao;
import com.example.a51044.myfirstapp.weight.MyFlowLayout;
import com.example.a51044.myfirstapp.weight.MySerchView;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：${小强}<p>
 * <p>创建时间：2019/1/713:56<p>
 * <p>更改时间：2019/1/713:56<p>
 * <p>版本号：1<p>
 */
public class MyFlowActivity extends BaseActivity {
    private String[] data = {"流感", "咳嗽", "过敏", "发烧", "感冒", "湿疹", "便秘", "痔疮", "协和", "鼻炎", "失眠", "痛风", "上火", "脚气", "抑郁症", "性欲", "乳腺增生", "头晕", "腰痛"};
    private MySerchView mySerchView;
    private MyDao dao;
    private List<String> mList = new ArrayList<>();
    private List<String> mHistory = new ArrayList<>();
    private TextView delete;
    private MyFlowLayout myFlowLayoyt1;
    private MyFlowLayout myFlowLayoyt2;

    @Override
    protected int getLayout() {
        return R.layout.myflow_layout;
    }

    @Override
    protected void initViews() {

        dao = new MyDao(MyFlowActivity.this);

        mHistory = dao.selSql();
        for (int i = 0; i < data.length; i++) {
            mList.add(data[i]);
        }

        delete = findViewById(R.id.delete);
        delete.setOnClickListener(this);
        myFlowLayoyt1 = findViewById(R.id.myFlowLayout1);
        myFlowLayoyt2 = findViewById(R.id.myFlowLayout2);
        myFlowLayoyt2.setData(mList);
        mySerchView = findViewById(R.id.mySerch);
        mySerchView.getButton().setOnClickListener(this);

        if (!mHistory.isEmpty()) {
            myFlowLayoyt1.setData(mHistory);
        }


    }

    @Override
    protected void setOnclick() {

    }

    @Override
    protected void logic() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_view_zuhe_add:
                String name = mySerchView.getEdiText().trim().toString();
                if (!name.isEmpty()) {
                    Intent intent=new Intent(MyFlowActivity.this,MySerchActivity.class);
                    intent.putExtra("name",name);
                    startActivity(intent);
                    dao.addSql(name);
                    mHistory.add(name);
                    myFlowLayoyt1.removeAllViews();
                    myFlowLayoyt1.setData(mHistory);
                }
                /*else
                {
                    Intent intent=new Intent(MyFlowActivity.this,CannotFind.class);
                    startActivity(intent);
                }*/

                break;
            case R.id.delete:
                dao.delSql();
                myFlowLayoyt1.removeAllViews();
                mHistory.clear();
                break;
        }

    }
}
