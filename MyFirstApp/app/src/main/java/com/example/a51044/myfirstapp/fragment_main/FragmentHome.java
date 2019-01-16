package com.example.a51044.myfirstapp.fragment_main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.a51044.myfirstapp.MyFlowActivity;
import com.example.a51044.myfirstapp.R;
import com.example.a51044.myfirstapp.adapter.MyCircleAdapter;
import com.example.a51044.myfirstapp.adapter.MyListAdapter;
import com.example.a51044.myfirstapp.adapter.MyListAdapter2;
import com.example.a51044.myfirstapp.base.BaseFragment;
import com.example.a51044.myfirstapp.bean.MyListOne;
import com.example.a51044.myfirstapp.bean.MyListTwo;
import com.example.a51044.myfirstapp.fragment_child.FragmentSerch;
import com.example.a51044.myfirstapp.ivew.IVew;
import com.example.a51044.myfirstapp.presenter.PresenterImpl;
import com.example.a51044.myfirstapp.presenter.PresenterSlides;
import com.example.a51044.myfirstapp.utils.Contacts;
import com.example.a51044.myfirstapp.weight.MyDialog;
import com.xw.repo.XEditText;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome<T> extends BaseFragment implements View.OnClickListener, MyDialog.OnCenterItemClickListener,IVew<T> {
    private View view;
    private FragmentManager manager;
    private XEditText myhome_serchWidth;
    private ImageButton myhome_serch;
    private ImageButton myhome_classify;
    private RelativeLayout mylog;
    private boolean a=false;

    private List<MyListOne.ResultBean> mList1 = new ArrayList<>();
    private List<MyListTwo.ResultBean> mList2 = new ArrayList<>();

   // private String mUrl1 = "http://172.17.8.100/small/commodity/v1/findFirstCategory";
   // private String mUrl2 = "http://172.17.8.100/small/commodity/v1/findSecondCategory?firstCategoryId=";

    private MyListAdapter myListAdapter;
    private MyListAdapter2 myListAdapter2;

    private PresenterImpl presenter;
    private PresenterSlides presenterSlides;


    private MyDialog myDialog;
    private RecyclerView mydialog_layout_recy1;
    private RecyclerView mydialog_layout_recy2;


    @Override
    protected void initData() {

    }

    @Override
    protected void findViewById(View view) {
        mydialog_layout_recy1=view.findViewById(R.id.mydialog_layout_recy1);
        mylog=view.findViewById(R.id.mylog);
        mydialog_layout_recy2=view.findViewById(R.id.mydialog_layout_recy2);
        //实例化自定义的dialog
        /*myDialog = new MyDialog(getActivity(), R.layout.mydialog_layout);
        //绑定点击事件
        myDialog.setOnCenterItemClickListener((MyDialog.OnCenterItemClickListener) this);*/
        myhome_serch = view.findViewById(R.id.myhome_serch);
        myhome_classify = view.findViewById(R.id.myhome_classify);
        manager = getChildFragmentManager();
        manager.beginTransaction().replace(R.id.myhome_home_frg, new FragmentSerch()).commit();
        myhome_serchWidth = view.findViewById(R.id.myhome_serchWidth);


        LinearLayoutManager manager2 = new LinearLayoutManager(getActivity());
        manager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        mydialog_layout_recy1.setLayoutManager(manager2);

        LinearLayoutManager manager3 = new LinearLayoutManager(getActivity());
        manager3.setOrientation(LinearLayoutManager.HORIZONTAL);
        mydialog_layout_recy2.setLayoutManager(manager3);

        myListAdapter = new MyListAdapter(mList1, getActivity());
        mydialog_layout_recy1.setAdapter(myListAdapter);

        myListAdapter2 = new MyListAdapter2(mList2, getActivity());
        mydialog_layout_recy2.setAdapter(myListAdapter2);


        myListAdapter.setCheckItem(new MyListAdapter.ItemClick() {
            @Override
            public void setOnclick(View v, String id) {

                presenterSlides.startHomeShopping(Contacts.BASE_URL+Contacts.BASE_HomeTwo+id,10);
            }
        });


    }

    @Override
    protected void getNextData() {

    }


    @Override
    protected void setListener() {
        myhome_serch.setOnClickListener(this);
        myhome_serchWidth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyFlowActivity.class);
                startActivity(intent);
            }
        });

        myhome_classify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!a)
                {
                    mylog.setVisibility(View.VISIBLE);
                    a=true;
                }
                else
                {
                    mylog.setVisibility(View.GONE);
                    a=false;

                }

            }
        });

    }

    @Override
    protected void progressLogic() {
        presenter=new PresenterImpl(this);
        presenterSlides=new PresenterSlides(this);
        presenter.startHomeShopping(Contacts.BASE_URL+Contacts.BASE_HomeOne,9);

    }


    @Override
    protected View getLoadView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.myhome_home, null);
        return view;
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void OnCenterItemClick(MyDialog dialog, View view) {
        switch (view.getId()) {
            case 1:
                Toast.makeText(getActivity(), "点击了", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }


    @Override
    public void getSuccess(T data) {
        if(data instanceof MyListOne)
        {
            MyListOne myListOne= (MyListOne) data;
            mList1.addAll(myListOne.getResult());
            myListAdapter.notifyDataSetChanged();
        }
        if(data instanceof MyListTwo)
        {
            mList2.clear();
            MyListTwo myListTwo= (MyListTwo) data;
            mList2.addAll(myListTwo.getResult());
            myListAdapter2.notifyDataSetChanged();
        }

    }

    @Override
    public void getError(T error) {

    }
}
