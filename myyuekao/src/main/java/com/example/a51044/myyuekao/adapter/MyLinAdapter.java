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
import com.example.a51044.myyuekao.bean.LinData;

import java.util.List;

public class MyLinAdapter extends RecyclerView.Adapter<MyLinAdapter.ViewHolder> {
    private List<LinData.DataBean>mList;
    private Context mContext;

    public MyLinAdapter(List<LinData.DataBean> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyLinAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHolder holder=null;
        View view=View.inflate(mContext,R.layout.lin_item,null);
        holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyLinAdapter.ViewHolder viewHolder, int i) {
        Glide.with(mContext).load(mList.get(i).getThumbnail_pic_s()).into(viewHolder.lin_iv);
        viewHolder.lin_tv1.setText(mList.get(i).getTitle());
        viewHolder.lin_tv3.setText(mList.get(i).getAuthor_name());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView lin_iv;
        private TextView lin_tv1;
        private TextView lin_tv3;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lin_iv=itemView.findViewById(R.id.lin_iv);
            lin_tv1=itemView.findViewById(R.id.lin_tv1);
            lin_tv3=itemView.findViewById(R.id.lin_tv2);
        }
    }
}
