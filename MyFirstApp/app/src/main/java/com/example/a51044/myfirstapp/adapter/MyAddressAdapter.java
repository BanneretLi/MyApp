package com.example.a51044.myfirstapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.a51044.myfirstapp.R;
import com.example.a51044.myfirstapp.bean.MyAddress;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：${小强}<p>
 * <p>创建时间：2019/1/1111:59<p>
 * <p>更改时间：2019/1/1111:59<p>
 * <p>版本号：1<p>
 */
public class MyAddressAdapter extends RecyclerView.Adapter<MyAddressAdapter.ViewHolder> {
    private List<MyAddress.ResultBean>mList;
    private Context mContext;

    public MyAddressAdapter(List<MyAddress.ResultBean> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public MyAddressAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHolder holder = null;
        View view = View.inflate(mContext, R.layout.my_address, null);
        holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAddressAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.my_address_name.setText(mList.get(i).getRealName()+"1");
        viewHolder.my_address_num.setText(mList.get(i).getPhone()+"2");
        viewHolder.my_address_address.setText(mList.get(i).getAddress()+"3");
        viewHolder.my_address_radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkItem!=null)
                {
                    checkItem.setItemCheck(v,mList.get(i).getId());
                }
            }
        });
        if(mList.get(i).getWhetherDefault()==1)
        {
            viewHolder.my_address_radio.setChecked(true);
        }
        else
        {
            viewHolder.my_address_radio.setChecked(false);
        }


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView my_address_name;
        private TextView my_address_num;
        private TextView my_address_address;
        private CheckBox my_address_radio;
        private Button my_address_update;
        private Button my_address_delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            my_address_name=itemView.findViewById(R.id.my_address_name);
            my_address_num=itemView.findViewById(R.id.my_address_num);
            my_address_address=itemView.findViewById(R.id.my_address_address);
            my_address_update=itemView.findViewById(R.id.my_address_update);
            my_address_delete=itemView.findViewById(R.id.my_address_delete);
            my_address_radio=itemView.findViewById(R.id.my_address_radio);
        }
    }

    public interface CheckItem {
        void setItemCheck(View view, int i);
    }

    private CheckItem checkItem;

    public void setCheckItem(CheckItem checkItem) {
        this.checkItem = checkItem;
    }
}
