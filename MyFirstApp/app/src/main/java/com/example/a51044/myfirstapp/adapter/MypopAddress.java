package com.example.a51044.myfirstapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a51044.myfirstapp.R;
import com.example.a51044.myfirstapp.bean.MyAddress;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：${小强}<p>
 * <p>创建时间：2019/1/1313:38<p>
 * <p>更改时间：2019/1/1313:38<p>
 * <p>版本号：1<p>
 */
public class MypopAddress extends RecyclerView.Adapter<MypopAddress.ViewHolder> {
    private List<MyAddress.ResultBean> mList;
    private Context mContext;

    public MypopAddress(List<MyAddress.ResultBean> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }
    @NonNull
    @Override
    public MypopAddress.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHolder holder = null;
        View view = View.inflate(mContext, R.layout.address_pop_item, null);
        holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MypopAddress.ViewHolder viewHolder, final int i) {
        viewHolder.address_pop_item_name.setText(mList.get(i).getRealName());
        viewHolder.address_pop_item_phone.setText(mList.get(i).getPhone());
        viewHolder.address_pop_item_count.setText(mList.get(i).getAddress());
        viewHolder.address_pop_item_chooes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkItem!=null)
                {
                    checkItem.setItemCheckItem(v,i,mList.get(i).getId());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView address_pop_item_name;
        private TextView address_pop_item_phone;
        private TextView address_pop_item_count;
        private TextView address_pop_item_chooes;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            address_pop_item_name=itemView.findViewById(R.id.address_pop_item_name);
            address_pop_item_phone=itemView.findViewById(R.id.address_pop_item_phone);
            address_pop_item_count=itemView.findViewById(R.id.address_pop_item_count);
            address_pop_item_chooes=itemView.findViewById(R.id.address_pop_item_chooes);
        }
    }


    public interface CheckItem
    {
        void setItemCheckItem(View view,int i,int j);
    }

    private CheckItem checkItem;

    public void setCheckItem(CheckItem checkItem)
    {
        this.checkItem=checkItem;
    }
}
