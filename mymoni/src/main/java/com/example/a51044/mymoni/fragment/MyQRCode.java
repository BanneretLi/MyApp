package com.example.a51044.mymoni.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.a51044.mymoni.MainActivity;
import com.example.a51044.mymoni.R;
import com.example.a51044.mymoni.base.BaseFragment;
import com.yzq.zxinglibrary.encode.CodeCreator;

public class MyQRCode extends BaseFragment {
    private View view;
    private ImageView myQRCode;
    private Button back_login;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private String contentEtString ;

    @Override
    protected void initData() {
        /*Intent intent=new Intent();
        String a=intent.getStringExtra("key");
        Log.d("zzz",a+"李广强0");*/
    }

    @Override
    protected void findById(View view) {
        myQRCode=view.findViewById(R.id.myQRCode);
        back_login=view.findViewById(R.id.back_login);
    }

    @Override
    protected void setOnclick() {
        back_login.setOnClickListener(this);
    }

    @Override
    protected void logic() {
        pref =getActivity().getSharedPreferences("lgq",Context.MODE_PRIVATE);
        contentEtString =pref.getString("user","");
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
        switch (v.getId()){
            case R.id.back_login:
                editor = pref.edit();
                editor.remove("login");
                editor.commit();
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
                getActivity().finish();
                break;
        }
    }


}
