package com.example.a51044.myfirstapp.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.a51044.myfirstapp.R;

/**
 * <p>文件描述：<p>
 * <p>作者：${小强}<p>
 * <p>创建时间：2019/1/715:27<p>
 * <p>更改时间：2019/1/715:27<p>
 * <p>版本号：1<p>
 */
public class MySerchView extends LinearLayout {
    private EditText my_view_zuhe_serch;
    private Button my_view_zuhe_add;

    public MySerchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.my_view_zuhe, this);
        initViews();
    }

    public MySerchView(Context context) {
        super(context);
    }

    private void initViews() {
        my_view_zuhe_serch = findViewById(R.id.my_view_zuhe_serch);
        my_view_zuhe_add = findViewById(R.id.my_view_zuhe_add);
    }

    public String getEdiText() {
        return my_view_zuhe_serch.getText().toString();
    }

    public Button getButton() {
        return my_view_zuhe_add;
    }

}
