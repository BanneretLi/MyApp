package com.example.a51044.myfirstapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a51044.myfirstapp.base.BaseActivity;
import com.example.a51044.myfirstapp.bean.AddAddress;
import com.example.a51044.myfirstapp.ivew.IVew;
import com.example.a51044.myfirstapp.presenter.PresenterImpl;
import com.example.a51044.myfirstapp.utils.Contacts;
import com.lljjcoder.citypickerview.widget.CityPickerView;

import java.util.HashMap;

/**
 * <p>文件描述：<p>
 * <p>作者：${小强}<p>
 * <p>创建时间：2019/1/1021:21<p>
 * <p>更改时间：2019/1/1021:21<p>
 * <p>版本号：1<p>
 */
public class AddGoodsAddressActivity<T> extends BaseActivity implements IVew<T> {
    private TextView add_address_finish;
    private EditText add_address_name;
    private EditText add_address_num;
    private EditText add_address_where;
    private EditText add_address_detailed;
    private EditText add_address_code;
    private Button add_address_add;
    private String code;
    private String province;
    private String city;
    private String district;

    private PresenterImpl presenter;
    private HashMap<String,String>map=new HashMap<>();

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int userId;
    private String sessionId;


    @Override
    protected int getLayout() {
        return R.layout.add_address;
    }

    @Override
    protected void initViews() {
        add_address_finish = findViewById(R.id.add_address_finish);
        add_address_finish.setOnClickListener(this);
        add_address_name = findViewById(R.id.add_address_name);
        add_address_name.setOnClickListener(this);
        add_address_num = findViewById(R.id.add_address_num);
        add_address_num.setOnClickListener(this);
        add_address_where = findViewById(R.id.add_address_where);
        add_address_detailed = findViewById(R.id.add_address_detailed);
        add_address_detailed.setOnClickListener(this);
        add_address_code = findViewById(R.id.add_address_code);
        add_address_code.setOnClickListener(this);
        add_address_add = findViewById(R.id.add_address_add);
        add_address_add.setOnClickListener(this);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();

        SharedPreferences lgq = this.getSharedPreferences("lgq", Context.MODE_PRIVATE);
        userId = lgq.getInt("userId", 0);
        sessionId = lgq.getString("sessionId", "");
    }

    @Override
    protected void setOnclick() {

        add_address_where.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CityPickerView cityPickerView = new CityPickerView(AddGoodsAddressActivity.this);
                cityPickerView.setOnCityItemClickListener(new CityPickerView.OnCityItemClickListener() {
                    @Override
                    public void onSelected(String... citySelected) {
                        //省份
                         province = citySelected[0];
                        //城市
                         city = citySelected[1];
                        //区县
                         district = citySelected[2];
                        //邮编
                         code = citySelected[3];
                        add_address_where.setText(province+" "+city+" "+district);
                        add_address_code.setText(code);

                    }
                });
                cityPickerView.show();
            }
        });


            }

            @Override
            protected void logic() {
                presenter=new PresenterImpl(this);
            }

            @Override
            public void onClick(View v) {
                switch (v.getId())
                {
                    case R.id.add_address_finish:
                        finish();
                        break;
                    case R.id.add_address_detailed:
                        break;

                    case R.id.add_address_name:
                        break;

                    case R.id.add_address_num:
                        break;
                    case R.id.add_address_add:
                        String realName=add_address_name.getText().toString().trim();
                        String phone=add_address_num.getText().toString().trim();
                        String address=add_address_where.getText().toString().trim();
                        String zipCode=add_address_code.getText().toString().trim();

                        map.put("userId",String.valueOf(userId));
                        map.put("sessionId",sessionId);

                        map.put("realName",realName);
                        map.put("phone",phone);
                        map.put("address",address);
                        map.put("zipCode",zipCode);
                        presenter.startLogin(Contacts.BASE_URL+Contacts.BASE_AddAddress,map,13);
                        finish();
                        break;
                }
            }

    @Override
    public void getSuccess(T data) {
        AddAddress addAddress= (AddAddress) data;
        if(addAddress.getMessage().equals("添加成功"))
        {
            Toast.makeText(this, addAddress.getMessage()+"1", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, addAddress.getMessage()+"2", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getError(T error) {

    }
}
