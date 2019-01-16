package com.example.a51044.myfirstapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a51044.myfirstapp.R;
import com.example.a51044.myfirstapp.bean.MyHome;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：${小强}<p>
 * <p>创建时间：2019/1/120:03<p>
 * <p>更改时间：2019/1/120:03<p>
 * <p>版本号：1<p>
 */
public class MyPinzhiAdapter extends RecyclerView.Adapter<MyPinzhiAdapter.ViewHolder> {
    private List<MyHome.ResultBean.MlssBean.CommodityListBeanXX>mList;
    private Context mContext;

    public MyPinzhiAdapter(List<MyHome.ResultBean.MlssBean.CommodityListBeanXX> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyPinzhiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHolder holder = null;
        View view = LayoutInflater.from(mContext).inflate(R.layout.myhome_home_item,null);
        holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyPinzhiAdapter.ViewHolder viewHolder, final int i) {
        Glide.with(mContext).load(mList.get(i).getMasterPic()).into(viewHolder.myhome_home_item_img);
        viewHolder.myhome_home_item_tv.setText(mList.get(i).getCommodityName());
        viewHolder.myhome_home_item_price.setText("￥"+mList.get(i).getPrice()+".00");

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkItem!=null)
                {
                    checkItem.setItemCheck(v,mList.get(i).getCommodityName(),mList.get(i).getPrice(),mList.get(i).getMasterPic(),mList.get(i).getCommodityId());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView myhome_home_item_img;
        private TextView myhome_home_item_tv;
        private TextView myhome_home_item_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            myhome_home_item_img = itemView.findViewById(R.id.myhome_home_item_img);
            myhome_home_item_tv = itemView.findViewById(R.id.myhome_home_item_tv);
            myhome_home_item_price = itemView.findViewById(R.id.myhome_home_item_price);
        }
    }


    public interface CheckItem {
        void setItemCheck(View view, String name, int price, String icon,int id);
    }

    private CheckItem checkItem;

    public void setCheckItem(CheckItem checkItem) {
        this.checkItem = checkItem;
    }
}
