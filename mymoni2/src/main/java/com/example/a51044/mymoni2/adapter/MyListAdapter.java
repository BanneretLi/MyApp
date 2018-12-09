package com.example.a51044.mymoni2.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a51044.mymoni2.R;
import com.example.a51044.mymoni2.bean.MyData;

import java.util.List;

public class MyListAdapter extends BaseAdapter {
    private List<MyData.DataBean>mList;
    private Context mContext;

    public MyListAdapter(List<MyData.DataBean> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    public MyListAdapter(List<MyData.DataBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList.isEmpty()?0:mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null)
        {
            convertView=View.inflate(mContext, R.layout.xlist_item,null);
            holder=new ViewHolder();
            holder.iv=convertView.findViewById(R.id.item_iv);
            holder.tv=convertView.findViewById(R.id.item_tv);
            convertView.setTag(holder);
        }
        else
        {
            holder= (ViewHolder) convertView.getTag();
        }
        Glide.with(mContext).load(mList.get(position).getThumbnail_pic_s()).into(holder.iv);
        holder.tv.setText(mList.get(position).getTitle());
        return convertView;
    }

    class ViewHolder
    {
        ImageView iv;
        TextView tv;
    }
}
