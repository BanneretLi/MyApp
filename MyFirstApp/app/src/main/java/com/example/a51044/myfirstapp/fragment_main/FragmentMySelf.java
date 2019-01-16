package com.example.a51044.myfirstapp.fragment_main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a51044.myfirstapp.MySelfActivity;
import com.example.a51044.myfirstapp.R;
import com.example.a51044.myfirstapp.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FragmentMySelf extends BaseFragment {
    @BindView(R.id.myhome_self_head)
    ImageView myhomeSelfHead;
    @BindView(R.id.myhome_self_name)
    TextView myhomeSelfName;
    @BindView(R.id.myhome_self_one)
    TextView myhomeSelfOne;
    @BindView(R.id.myhome_self_two)
    TextView myhomeSelfTwo;
    @BindView(R.id.myhome_self_three)
    TextView myhomeSelfThree;
    @BindView(R.id.myhome_self_four)
    TextView myhomeSelfFour;
    @BindView(R.id.myhome_self_five)
    TextView myhomeSelfFive;
    Unbinder unbinder;
    private View view;

    @Override
    protected void initData() {

    }

    @Override
    protected void findViewById(View view) {

    }

    @Override
    protected void getNextData() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void progressLogic() {

    }

    @Override
    protected View getLoadView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.myhome_self, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.myhome_self_head, R.id.myhome_self_name, R.id.myhome_self_one, R.id.myhome_self_two, R.id.myhome_self_three, R.id.myhome_self_four, R.id.myhome_self_five})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.myhome_self_head:
                break;
            case R.id.myhome_self_name:
                break;
            case R.id.myhome_self_one:
                break;
            case R.id.myhome_self_two:
                Intent intent=new Intent(getActivity(),MySelfActivity.class);
                startActivity(intent);
                break;
            case R.id.myhome_self_three:
                break;
            case R.id.myhome_self_four:
                break;
            case R.id.myhome_self_five:
                Intent intent1=new Intent("com.example.a51044.GoodsAddressActivity.ACTION_START");
                startActivity(intent1);
                break;
        }
    }
}
