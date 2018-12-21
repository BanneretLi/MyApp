package com.example.a51044.myapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a51044.myapp.R;
import com.example.a51044.myapp.bean.MyData;

import java.util.List;
import java.util.Random;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<MyData.DataBean>mList;
    private Context mContext;

    public MyAdapter(List<MyData.DataBean> mList) {
        this.mList = mList;
    }

    public void setmList(List<MyData.DataBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        this.mContext=viewGroup.getContext();
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recy_item,viewGroup,false);
        ViewHolder holder=new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        MyData.DataBean bean=mList.get(i);
        ViewGroup.LayoutParams params=viewHolder.itemView.getLayoutParams();
        Random random=new Random();
        int height=random.nextInt(300)+300;
        params.height=height;
        viewHolder.itemView.setLayoutParams(params);
        Glide.with(mContext).load(mList.get(i).getThumbnail_pic_s()).into(viewHolder.iv);
        viewHolder.tv.setText(mList.get(i).getAuthor_name());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.item_iv);
            tv=itemView.findViewById(R.id.item_tv);
        }
    }
}
