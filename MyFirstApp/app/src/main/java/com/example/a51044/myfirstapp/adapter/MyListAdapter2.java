package com.example.a51044.myfirstapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a51044.myfirstapp.R;
import com.example.a51044.myfirstapp.bean.MyListTwo;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：${小强}<p>
 * <p>创建时间：2019/1/815:34<p>
 * <p>更改时间：2019/1/815:34<p>
 * <p>版本号：1<p>
 */
public class MyListAdapter2 extends RecyclerView.Adapter<MyListAdapter2.ViewHolder> {
    private List<MyListTwo.ResultBean>mList;
    private Context mContext;

    public MyListAdapter2(List<MyListTwo.ResultBean> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyListAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHolder holder=null;
        View view=View.inflate(mContext,R.layout.second_layout,null);
        holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyListAdapter2.ViewHolder viewHolder, int i) {
        viewHolder.second_tv.setText(mList.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView second_tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            second_tv=itemView.findViewById(R.id.second_tv);
        }
    }
}
