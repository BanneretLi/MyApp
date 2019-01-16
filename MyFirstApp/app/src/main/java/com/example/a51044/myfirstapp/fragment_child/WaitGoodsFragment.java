package com.example.a51044.myfirstapp.fragment_child;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.a51044.myfirstapp.R;
import com.example.a51044.myfirstapp.adapter.MWaitGoodsrAdapter;
import com.example.a51044.myfirstapp.adapter.MyAllOrderAdapter;
import com.example.a51044.myfirstapp.base.BaseFragment;
import com.example.a51044.myfirstapp.bean.MyAllList;
import com.example.a51044.myfirstapp.bean.MyShopping;
import com.example.a51044.myfirstapp.ivew.IVew;
import com.example.a51044.myfirstapp.presenter.PresenterImpl;
import com.example.a51044.myfirstapp.utils.Contacts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：${小强}<p>
 * <p>创建时间：2019/1/1417:00<p>
 * <p>更改时间：2019/1/1417:00<p>
 * <p>版本号：1<p>
 */
public class WaitGoodsFragment<T> extends BaseFragment implements IVew<T> {
    private View view;
    private PresenterImpl presenter;
    private RecyclerView all_order_recy;
    private HashMap<String, String> map = new HashMap<>();
    private List<MyAllList.OrderListBean>mList=new ArrayList<>();
    private List<MyAllList.OrderListBean.DetailListBean>mData=new ArrayList<>();
    private List<String> mImage=new ArrayList<>();
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private MWaitGoodsrAdapter myAllOrderAdapter;
    private int userId;
    private String sessionId;
    @Override
    protected void initData() {

    }

    @Override
    protected void findViewById(View view) {
        all_order_recy=view.findViewById(R.id.wait_goods_recy);



        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),1);
        all_order_recy.setLayoutManager(gridLayoutManager);

        myAllOrderAdapter=new MWaitGoodsrAdapter(mList,mData,mImage,getActivity());
        all_order_recy.setAdapter(myAllOrderAdapter);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = sharedPreferences.edit();
        SharedPreferences lgq = getActivity().getSharedPreferences("lgq", Context.MODE_PRIVATE);
        userId = lgq.getInt("userId", 0);
        sessionId = lgq.getString("sessionId", "");

    }

    @Override
    protected void getNextData() {

    }

    @Override
    protected void setListener() {
        myAllOrderAdapter.setCheckItem(new MWaitGoodsrAdapter.CheckItem() {
            @Override
            public void setItemCheck(View view, String j) {
                presenter.startLogin(Contacts.BASE_URL+Contacts.BASE_OkGoods+j,map,11);
            }
        });

    }

    @Override
    protected void progressLogic() {
        presenter=new PresenterImpl(this);
        map.put("userId",String.valueOf(userId));
        map.put("sessionId",sessionId);
        presenter.startLogin(Contacts.BASE_URL+Contacts.BASE_WaitGoods,map,16);

    }

    @Override
    protected View getLoadView(LayoutInflater inflater, ViewGroup container) {
        view=inflater.inflate(R.layout.wait_goods,null);
        return view;
    }

    @Override
    public void getSuccess(T data) {
        if(data instanceof MyAllList)
        {
            MyAllList myAllList= (MyAllList) data;
            mList.addAll(myAllList.getOrderList());
            for(int i=0;i<mList.size();i++)
            {
                mData.addAll(myAllList.getOrderList().get(i).getDetailList());
                String images=mData.get(i).getCommodityPic();
                String[] split=images.split(",");
                mImage.add(split[i]);
            }

            myAllOrderAdapter.notifyDataSetChanged();

        }
        if(data instanceof MyShopping)
        {
            MyShopping myShopping= (MyShopping) data;
            if(myShopping.getStatus().equals("0000"))
            {
                Toast.makeText(getActivity(), myShopping.getMessage(), Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getActivity(), myShopping.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void getError(T error) {

    }
}
