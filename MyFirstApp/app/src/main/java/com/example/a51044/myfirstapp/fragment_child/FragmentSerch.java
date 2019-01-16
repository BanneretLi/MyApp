package com.example.a51044.myfirstapp.fragment_child;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.a51044.myfirstapp.R;
import com.example.a51044.myfirstapp.adapter.MyHomeAdapter;
import com.example.a51044.myfirstapp.adapter.MyMoliAdapter;
import com.example.a51044.myfirstapp.adapter.MyPinzhiAdapter;
import com.example.a51044.myfirstapp.adapter.MyQueryGoodsAdapter;
import com.example.a51044.myfirstapp.base.BaseFragment;
import com.example.a51044.myfirstapp.bean.MyHome;
import com.example.a51044.myfirstapp.bean.MyShopping;
import com.example.a51044.myfirstapp.bean.MySlideshow;
import com.example.a51044.myfirstapp.bean.QueryGoods;
import com.example.a51044.myfirstapp.ivew.IVew;
import com.example.a51044.myfirstapp.presenter.PresenterCancel;
import com.example.a51044.myfirstapp.presenter.PresenterImpl;
import com.example.a51044.myfirstapp.presenter.PresenterSlides;
import com.example.a51044.myfirstapp.utils.Contacts;
import com.example.a51044.myfirstapp.weight.FullyLinearLayoutManager;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;
import com.xw.repo.XEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：${小强}<p>
 * <p>创建时间：2019/1/220:58<p>
 * <p>更改时间：2019/1/220:58<p>
 * <p>版本号：1<p>
 */
public class FragmentSerch<T> extends BaseFragment implements View.OnClickListener,IVew<T> {
    private View view;

    private RecyclerView myhome_home_list;
    private RecyclerView myhome_home_moli_list;
    private RecyclerView myhome_home_pinzhi_list;

    private List<MyHome.ResultBean.RxxpBean.CommodityListBean>mRxxp=new ArrayList<>();
    private List<MyHome.ResultBean.PzshBean.CommodityListBeanX>mPzsh=new ArrayList<>();
    private List<MyHome.ResultBean.MlssBean.CommodityListBeanXX>mMlss=new ArrayList<>();

    private List<QueryGoods.ResultBean>mList=new ArrayList<>();


    private PresenterImpl presenter;
    private PresenterSlides presenterSlides;

    private PresenterCancel presenterCancel;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int userId;
    private String sessionId;

    private int goodsId;



    private XBanner xBanner;

    private MyHomeAdapter myHomeAdapter;
    private MyMoliAdapter myMoliAdapter;
    private MyPinzhiAdapter myPinzhiAdapter;

    private List<String> mList2=new ArrayList<>();
    private List<MySlideshow.ResultBean>myData=new ArrayList<>();

    private HashMap<String,String>map=new HashMap<>();
   // private String mUrl = "http://172.17.8.100/small/order/verify/v1/findShoppingCart";



    @Override
    protected void initData() {

    }

    @Override
    protected void findViewById(View view) {

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = sharedPreferences.edit();

        SharedPreferences lgq = getActivity().getSharedPreferences("lgq", Context.MODE_PRIVATE);
        userId = lgq.getInt("userId", 0);
        sessionId = lgq.getString("sessionId", "");

        myhome_home_list=view.findViewById(R.id.myhome_home_rexiao_list);
        myhome_home_moli_list=view.findViewById(R.id.myhome_home_moli_list);
        myhome_home_pinzhi_list=view.findViewById(R.id.myhome_home_pinzhi_list);
        xBanner=view.findViewById(R.id.myhome_vp);





        GridLayoutManager manager=new GridLayoutManager(getActivity(),3);
        myhome_home_list.setLayoutManager(manager);

        myHomeAdapter=new MyHomeAdapter(mRxxp,getContext());
        myhome_home_list.setAdapter(myHomeAdapter);

        FullyLinearLayoutManager manager2=new FullyLinearLayoutManager(getActivity());
        myhome_home_moli_list.setLayoutManager(manager2);

        myMoliAdapter=new MyMoliAdapter(mPzsh,getContext());
        myhome_home_moli_list.setAdapter(myMoliAdapter);

        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),2);
        myhome_home_pinzhi_list.setLayoutManager(gridLayoutManager);

        myPinzhiAdapter=new MyPinzhiAdapter(mMlss,getContext());
        myhome_home_pinzhi_list.setAdapter(myPinzhiAdapter);
    }

    @Override
    protected void getNextData() {

    }

    @Override
    protected void setListener() {
        presenterCancel=new PresenterCancel(this);

        myHomeAdapter.setCheckItem(new MyHomeAdapter.CheckItem() {
            @Override
            public void setItemCheck(View view, String name, int price, String icon,int id) {
                goodsId=id;
                map.put("userId",String.valueOf(userId));
                map.put("sessionId",sessionId);
                map.put("data","[{commodityId:"+id+",count:"+1+"}]");
                presenterCancel.startLogin(Contacts.BASE_URL+Contacts.BASE_Shopping,map,12);


            }
        });

        myMoliAdapter.setCheckItem(new MyMoliAdapter.CheckItem() {
            @Override
            public void setItemCheck(View view, String name, int price, String icon, int id) {
              //  presenterCancel.startLogin();
                map.put("userId",String.valueOf(userId));
                map.put("sessionId",sessionId);
                map.put("data","[{commodityId:"+id+",count:"+1+"}]");
                presenterCancel.startLogin(Contacts.BASE_URL+Contacts.BASE_Sync,map,11);
            }
        });

        myPinzhiAdapter.setCheckItem(new MyPinzhiAdapter.CheckItem() {
            @Override
            public void setItemCheck(View view, String name, int price, String icon, int id) {
                map.put("userId",String.valueOf(userId));
                map.put("sessionId",sessionId);
                map.put("data","[{commodityId:"+id+",count:"+1+"}]");
                presenterCancel.startLogin(Contacts.BASE_URL+Contacts.BASE_Sync,map,11);
            }
        });

    }

    @Override
    protected void progressLogic() {
        presenter=new PresenterImpl(this);
        presenterSlides=new PresenterSlides(this);
        presenter.startHomeShopping(Contacts.BASE_URL+Contacts.BASE_HOME,3);
        presenterSlides.startHomeShopping(Contacts.BASE_URL+Contacts.BASE_Banner,4);

    }

    @Override
    protected View getLoadView(LayoutInflater inflater, ViewGroup container) {
        view=inflater.inflate(R.layout.home_main,null);
        return view;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void getSuccess(T data) {
        if(data instanceof MyHome)
        {
            MyHome home= (MyHome) data;
            mRxxp.addAll(home.getResult().getRxxp().get(0).getCommodityList());
            mPzsh.addAll(home.getResult().getPzsh().get(0).getCommodityList());
            mMlss.addAll(home.getResult().getMlss().get(0).getCommodityList());
            myHomeAdapter.notifyDataSetChanged();
        }
        if(data instanceof MySlideshow)
        {
            MySlideshow mySlideshow= (MySlideshow) data;
            myData.addAll(mySlideshow.getResult());
            for (int i=0;i<myData.size();i++)
            {
                mList2.add(myData.get(i).getImageUrl());
            }

            xBanner.setData(mList2,null);
            xBanner.loadImage(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    Glide.with(getActivity()).load(mList2.get(position)).into((ImageView) view);
                }
            });

            xBanner.setPageChangeDuration(1000);
            xBanner.setPageTransformer(Transformer.Default);
        }

        if(data instanceof QueryGoods)
        {
            QueryGoods queryGoods= (QueryGoods) data;

            mList.addAll(queryGoods.getResult());
            if(mList.size()!=0)
            {
                JSONArray jsonArray=new JSONArray();
                for(int i=0;i<mList.size();i++)
                {
                    JSONObject jsonObject=null;
                    jsonObject=new JSONObject();
                    try {
                        jsonObject.put("commodityId",mList.get(i).getCommodityId());
                        jsonObject.put("count",1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    jsonArray.put(jsonObject);
                }
                try {
                    JSONObject jsonObject=null;
                    jsonObject=new JSONObject();
                    jsonObject.put("commodityId",goodsId);
                    jsonObject.put("count",1);
                    jsonArray.put(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                map.put("data",jsonArray.toString());
                presenterCancel.startLogin(Contacts.BASE_URL+Contacts.BASE_Sync,map,11);

            }
            else
            {
                Toast.makeText(getActivity(), "sc", Toast.LENGTH_SHORT).show();
                presenterCancel.startLogin(Contacts.BASE_URL+Contacts.BASE_Sync,map,11);
            }
        }

        if(data instanceof MyShopping)
        {
            MyShopping shopping= (MyShopping) data;
            if(shopping.getStatus().equals("0000"))
            {
                Toast.makeText(getActivity(), shopping.getMessage(), Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getActivity(), shopping.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void getError(T error) {

    }
}
