package com.example.a51044.myyuekao.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a51044.myyuekao.R;
import com.example.a51044.myyuekao.bean.LinData;

import java.util.List;
import java.util.Random;

public class MyPuAdapter extends RecyclerView.Adapter<MyPuAdapter.ViewHolder> {
    private List<LinData.DataBean>mList;
    private Context mContex;

    public MyPuAdapter(List<LinData.DataBean> mList, Context mContex) {
        this.mList = mList;
        this.mContex = mContex;
    }

    @NonNull
    @Override
    public MyPuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContex).inflate(R.layout.pu_item,viewGroup,false);
        ViewHolder holder=new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyPuAdapter.ViewHolder viewHolder, int i) {
        Glide.with(mContex).load(mList.get(i).getThumbnail_pic_s()).into(viewHolder.pu_iv);
        viewHolder.pu_tv.setText(mList.get(i).getTitle());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView pu_iv;
        private TextView pu_tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pu_iv=itemView.findViewById(R.id.pu_iv);
            pu_tv=itemView.findViewById(R.id.pu_tv);
        }
    }
}
