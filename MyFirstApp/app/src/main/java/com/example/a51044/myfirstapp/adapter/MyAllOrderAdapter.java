package com.example.a51044.myfirstapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a51044.myfirstapp.R;
import com.example.a51044.myfirstapp.bean.MyAllList;
import com.example.a51044.myfirstapp.bean.MyAllOrder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：${小强}<p>
 * <p>创建时间：2019/1/129:51<p>
 * <p>更改时间：2019/1/129:51<p>
 * <p>版本号：1<p>
 */
public class MyAllOrderAdapter extends RecyclerView.Adapter<MyAllOrderAdapter.ViewHolder> {
   private List<MyAllList.OrderListBean>mList;
   private List<MyAllList.OrderListBean.DetailListBean>mData;
   private Context mContext;

    public MyAllOrderAdapter(List<MyAllList.OrderListBean> mList, List<MyAllList.OrderListBean.DetailListBean> mData, Context mContext) {
        this.mList = mList;
        this.mData = mData;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyAllOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHolder holder=null;
        View view=View.inflate(mContext, R.layout.all_order_item,null);
        holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAllOrderAdapter.ViewHolder viewHolder, int i) {
        viewHolder.all_order_num.setText("订单号   "+"WD"+mList.get(i).getOrderId());
        viewHolder.all_order_name.setText(mData.get(i).getCommodityName());
        java.text.SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = dateFormat.format(mList.get(i).getOrderTime());
        viewHolder.all_order_date.setText(str);
        String img=mData.get(i).getCommodityPic();
        String[] split = img.split("\\,");
        Glide.with(mContext).load(split[0]).into(viewHolder.all_order_img);
        viewHolder.all_order_price.setText("￥"+mData.get(i).getCommodityPrice()+".00");
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView all_order_num;
        private TextView all_order_date;
        private ImageView all_order_img;
        private TextView all_order_name;
        private TextView all_order_price;
        private Button all_order_jian;
        private Button all_order_phone;
        private Button all_order_add;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            all_order_num=itemView.findViewById(R.id.all_order_num);
            all_order_date=itemView.findViewById(R.id.all_order_date);
            all_order_img=itemView.findViewById(R.id.all_order_img);
            all_order_name=itemView.findViewById(R.id.all_order_name);
            all_order_price=itemView.findViewById(R.id.all_order_price);
            all_order_jian=itemView.findViewById(R.id.all_order_jian);
            all_order_phone=itemView.findViewById(R.id.all_order_phone);
            all_order_add=itemView.findViewById(R.id.all_order_add);
        }
    }
}
