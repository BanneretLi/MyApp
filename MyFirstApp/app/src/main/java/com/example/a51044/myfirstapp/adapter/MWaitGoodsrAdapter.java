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

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：${小强}<p>
 * <p>创建时间：2019/1/129:51<p>
 * <p>更改时间：2019/1/129:51<p>
 * <p>版本号：1<p>
 */
public class MWaitGoodsrAdapter extends RecyclerView.Adapter<MWaitGoodsrAdapter.ViewHolder> {
   private List<MyAllList.OrderListBean>mList;
   private List<MyAllList.OrderListBean.DetailListBean>mData;
   private List<String>mImage;
   private Context mContext;

    public MWaitGoodsrAdapter(List<MyAllList.OrderListBean> mList, List<MyAllList.OrderListBean.DetailListBean> mData, List<String> mImage, Context mContext) {
        this.mList = mList;
        this.mData = mData;
        this.mImage = mImage;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MWaitGoodsrAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHolder holder=null;
        View view=View.inflate(mContext, R.layout.wait_goods_item,null);
        holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MWaitGoodsrAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.wait_goods_num.setText("订单号   "+"WD"+mList.get(i).getOrderId());
        viewHolder.wait_goods_name.setText(mData.get(i).getCommodityName());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = dateFormat.format(mList.get(i).getOrderTime());
        viewHolder.wait_goods_date.setText(str);
        Glide.with(mContext).load(mImage.get(i)).into(viewHolder.wait_goods_img);
        viewHolder.wait_goods_price.setText("￥"+mData.get(i).getCommodityPrice()+".00");
        viewHolder.wait_goods_queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkItem!=null)
                {
                    checkItem.setItemCheck(v,mList.get(i).getOrderId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView wait_goods_num;
        private TextView wait_goods_date;
        private ImageView wait_goods_img;
        private TextView wait_goods_name;
        private TextView wait_goods_price;
        private Button wait_goods_queren;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wait_goods_num=itemView.findViewById(R.id.wait_goods_num);
            wait_goods_date=itemView.findViewById(R.id.wait_goods_date);
            wait_goods_img=itemView.findViewById(R.id.wait_goods_img);
            wait_goods_name=itemView.findViewById(R.id.wait_goods_name);
            wait_goods_price=itemView.findViewById(R.id.wait_goods_price);
            wait_goods_queren=itemView.findViewById(R.id.wait_goods_queren);
        }
    }

    public interface CheckItem {
        void setItemCheck(View view,String j);
    }

    private CheckItem checkItem;

    public void setCheckItem(CheckItem checkItem) {
        this.checkItem = checkItem;
    }
}
