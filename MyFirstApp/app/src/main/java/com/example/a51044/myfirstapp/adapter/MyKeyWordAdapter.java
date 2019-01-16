package com.example.a51044.myfirstapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a51044.myfirstapp.R;
import com.example.a51044.myfirstapp.bean.MyKeyWord;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：${小强}<p>
 * <p>创建时间：2019/1/314:04<p>
 * <p>更改时间：2019/1/314:04<p>
 * <p>版本号：1<p>
 */
public class MyKeyWordAdapter extends RecyclerView.Adapter<MyKeyWordAdapter.ViewHolder> {
    private List<MyKeyWord.ResultBean>mList;
    private Context mContext;

    public MyKeyWordAdapter(List<MyKeyWord.ResultBean> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyKeyWordAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHolder holder=null;


            View view=View.inflate(mContext, R.layout.home_serch_item,null);
            holder=new ViewHolder(view);



        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyKeyWordAdapter.ViewHolder viewHolder, final int i) {


            Glide.with(mContext).load(mList.get(i).getMasterPic()).into(viewHolder.home_serch_item_img);
            viewHolder.home_serch_item_tv.setText(mList.get(i).getCommodityName());
            viewHolder.home_serch_item_price.setText("￥"+mList.get(i).getPrice()+".00");
            viewHolder.home_serch_item_yishou.setText("已售"+mList.get(i).getSaleNum()+"件");
            viewHolder.home_serch_item_card.setRadius(20);
            viewHolder.home_serch_item_card.setCardElevation(8);
            viewHolder.home_serch_item_card.setContentPadding(5,5,5,5);

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
        private ImageView home_serch_item_img;
        private TextView home_serch_item_tv;
        private TextView home_serch_item_price;
        private TextView home_serch_item_yishou;
        private CardView home_serch_item_card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            home_serch_item_img=itemView.findViewById(R.id.home_serch_item_img);
            home_serch_item_tv=itemView.findViewById(R.id.home_serch_item_tv);
            home_serch_item_price=itemView.findViewById(R.id.home_serch_item_price);
            home_serch_item_yishou=itemView.findViewById(R.id.home_serch_item_yishou);
            home_serch_item_card=itemView.findViewById(R.id.home_serch_item_card);

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
