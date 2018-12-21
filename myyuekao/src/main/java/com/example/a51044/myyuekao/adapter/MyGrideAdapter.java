package com.example.a51044.myyuekao.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a51044.myyuekao.R;
import com.example.a51044.myyuekao.bean.GrideData;

import java.util.List;

public class MyGrideAdapter extends RecyclerView.Adapter<MyGrideAdapter.ViewHolder> {
    private List<GrideData.DataBean>mList;
    private Context mContext;

    public MyGrideAdapter(List<GrideData.DataBean> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyGrideAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHolder holder=null;
        View view=View.inflate(mContext,R.layout.gride_item,null);
        holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyGrideAdapter.ViewHolder viewHolder, int i) {
        Glide.with(mContext).load(mList.get(i).getIcon()).into(viewHolder.gride_iv);
        viewHolder.gride_tv.setText(mList.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView gride_iv;
        private TextView gride_tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            gride_iv=itemView.findViewById(R.id.gride_iv);
            gride_tv=itemView.findViewById(R.id.gride_tv);
        }
    }
}
