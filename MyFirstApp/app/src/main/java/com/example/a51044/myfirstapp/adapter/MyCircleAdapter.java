package com.example.a51044.myfirstapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a51044.myfirstapp.MySelfActivity;
import com.example.a51044.myfirstapp.R;
import com.example.a51044.myfirstapp.bean.MyCircle;
import com.example.a51044.myfirstapp.bean.MyFabouls;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：${小强}<p>
 * <p>创建时间：2019/1/319:29<p>
 * <p>更改时间：2019/1/319:29<p>
 * <p>版本号：1<p>
 */
public class MyCircleAdapter extends RecyclerView.Adapter<MyCircleAdapter.ViewHolder> {
    private List<MyCircle.ResultBean> mList;
    private Context mContext;

    public MyCircleAdapter(List<MyCircle.ResultBean> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }




    @NonNull
    @Override
    public MyCircleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHolder holder = null;
        View view = View.inflate(mContext, R.layout.circle_item, null);
        holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyCircleAdapter.ViewHolder viewHolder, final int i) {
        Glide.with(mContext).load(mList.get(i).getHeadPic()).placeholder(R.mipmap.load_hdpi).into(viewHolder.circle_item_head);
        viewHolder.circle_item_name.setText(mList.get(i).getNickName());
        java.text.SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = dateFormat.format(mList.get(i).getCreateTime());
        viewHolder.circle_item_date.setText(str);
        viewHolder.circle_item_count.setText(mList.get(i).getContent());
        viewHolder.circle_item_num.setText(mList.get(i).getGreatNum() + "");
        Glide.with(mContext).load(mList.get(i).getImage()).placeholder(R.mipmap.load_hdpi).into(viewHolder.circle_item_pic);
        Log.e("zzz", "onBindViewHolder: " + mList.get(i).getWhetherGreat());
        if (mList.get(i).getWhetherGreat() == 1) {
            Glide.with(mContext).load(R.mipmap.common_btn_prise_s_hdpi).placeholder(R.mipmap.load_hdpi).into(viewHolder.circle_item_fabulous);

        } else {
            Glide.with(mContext).load(R.mipmap.common_btn_prise_n_hdpi).placeholder(R.mipmap.load_hdpi).into(viewHolder.circle_item_fabulous);

        }
        viewHolder.circle_item_fabulous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkItem != null) {

                    checkItem.setItemCheck(v, mList.get(i).getWhetherGreat(), mList.get(i).getId(), i);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView circle_item_head;
        private TextView circle_item_name;
        private TextView circle_item_date;
        private TextView circle_item_count;
        private ImageView circle_item_pic;
        private TextView circle_item_num;
        private ImageView circle_item_fabulous;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

                    circle_item_head = itemView.findViewById(R.id.circle_item_head);
                    circle_item_name = itemView.findViewById(R.id.circle_item_name);
                    circle_item_date = itemView.findViewById(R.id.circle_item_date);
                    circle_item_count = itemView.findViewById(R.id.circle_item_count);
                    circle_item_pic = itemView.findViewById(R.id.circle_item_pic);
                    circle_item_num = itemView.findViewById(R.id.circle_item_num);
                    circle_item_fabulous = itemView.findViewById(R.id.circle_item_fabulous);
        }
    };

    public interface CheckItem {
        void setItemCheck(View view, int i, int id, int j);
    }

    private CheckItem checkItem;

    public void setCheckItem(CheckItem checkItem) {
        this.checkItem = checkItem;
    }
}
