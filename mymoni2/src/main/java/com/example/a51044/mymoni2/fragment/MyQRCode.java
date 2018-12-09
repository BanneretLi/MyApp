package com.example.a51044.mymoni2.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.a51044.mymoni2.R;
import com.example.a51044.mymoni2.base.BaseFragment;
import com.yzq.zxinglibrary.encode.CodeCreator;

public class MyQRCode extends BaseFragment {

    private View view;
    private ImageView myQRCode;
    private Button back_login;
    private String contentEtString="12";

    @Override
    protected void initData() {

    }

    @Override
    protected void findViewById(View view) {
        myQRCode=view.findViewById(R.id.myQRCode);
        back_login=view.findViewById(R.id.back_login);
    }

    @Override
    protected void setOnclick() {
        back_login.setOnClickListener(this);
    }

    @Override
    protected void logic() {
        Bitmap logo=BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        Bitmap bitmap= null;
        try {
            bitmap = CodeCreator.createQRCode(contentEtString,400,400,logo);
            myQRCode.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected View getLoadView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.my_qr_code, null);
        return view;
    }


    @Override
    public void onClick(View v) {

    }
}
