package com.example.a51044.myfirstapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a51044.myfirstapp.R;
import com.example.a51044.myfirstapp.bean.MyListOne;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：${小强}<p>
 * <p>创建时间：2019/1/811:53<p>
 * <p>更改时间：2019/1/811:53<p>
 * <p>版本号：1<p>
 */
public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {
    private List<MyListOne.ResultBean>mList=new ArrayList<>();
    private Context mContext;

    public MyListAdapter(List<MyListOne.ResultBean> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHolder holder=null;
        View view=View.inflate(mContext, R.layout.first_layout,null);
        holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyListAdapter.ViewHolder viewHolder, final int i) {


            viewHolder.tv1.setText(mList.get(i).getName());
            viewHolder.tv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(itemClick!=null)
                    {
                        itemClick.setOnclick(v,mList.get(i).getId());
                    }
                }
            });



    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1=itemView.findViewById(R.id.first_tv1);
        }
    }


    public interface ItemClick
    {
        void setOnclick(View v,String id);
    }

    private ItemClick itemClick;

    public void setCheckItem(ItemClick checkItem) {
        this.itemClick = checkItem;
    }

}
