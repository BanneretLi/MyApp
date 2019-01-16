package com.example.a51044.myfirstapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.a51044.myfirstapp.R;
import com.example.a51044.myfirstapp.bean.QueryGoods;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：${小强}<p>
 * <p>创建时间：2019/1/913:57<p>
 * <p>更改时间：2019/1/913:57<p>
 * <p>版本号：1<p>
 */
public class MyQueryGoodsAdapter extends RecyclerView.Adapter<MyQueryGoodsAdapter.ViewHolder> {
    private List<QueryGoods.ResultBean>mList;
    private Context mContext;

    public MyQueryGoodsAdapter(List<QueryGoods.ResultBean> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyQueryGoodsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewHolder holder=null;
        View view=View.inflate(mContext, R.layout.shopping_item,null);
        holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyQueryGoodsAdapter.ViewHolder viewHolder, final int i) {
        Glide.with(mContext).load(mList.get(i).getPic()).into(viewHolder.shopping_item_img);
        viewHolder.shopping_item_name.setText(mList.get(i).getCommodityName());
        viewHolder.shopping_item_num.setText(mList.get(i).getCount()+"");
        viewHolder.shopping_item_price.setText("￥"+mList.get(i).getPrice()+".00");



        viewHolder.shopping_item_check.setChecked(mList.get(i).isChecked());
        viewHolder.shopping_item_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adapterCallback!=null)
                {
                    adapterCallback.setChildCheck(v,i);
                }
            }
        });

        viewHolder.shopping_item_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adapterCallback!=null)
                {
                    int a= Integer.valueOf((String) viewHolder.shopping_item_num.getText());
                    if(a>0)
                    {
                        a--;
                        viewHolder.shopping_item_num.setText(a+"");
                        mList.get(i).setCount(a);
                    }

                }
            }
        });

        viewHolder.shopping_item_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adapterCallback!=null)
                {
                    int a= Integer.valueOf((String) viewHolder.shopping_item_num.getText());
                    a++;
                    viewHolder.shopping_item_num.setText(a+"");
                    mList.get(i).setCount(a);
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView shopping_item_img;
        private TextView shopping_item_name;
        private TextView shopping_item_price;
        private Button shopping_item_jian;
        private Button shopping_item_num;
        private Button shopping_item_add;
        private CheckBox shopping_item_check;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            shopping_item_img=itemView.findViewById(R.id.shopping_item_img);
            shopping_item_name=itemView.findViewById(R.id.shopping_item_name);
            shopping_item_price=itemView.findViewById(R.id.shopping_item_price);
            shopping_item_jian=itemView.findViewById(R.id.shopping_item_jian);
            shopping_item_num=itemView.findViewById(R.id.shopping_item_num);
            shopping_item_add=itemView.findViewById(R.id.shopping_item_add);
            shopping_item_check=itemView.findViewById(R.id.shopping_item_check);
        }
    }

    public boolean isChecked(int i)
    {

        if(!mList.get(i).isChecked())
        {
            return false;
        }

        return true;
    }

    public void setChildCheck(int i,boolean isCheckBox)
    {
        mList.get(i).setChecked(isCheckBox);
    }

    public boolean isCheckAll()
    {
        for(int i=0;i<mList.size();i++)
        {
            if(!mList.get(i).isChecked())
            {
                return false;
            }
        }
        return true;
    }

    public void checkAll(boolean a)
    {
        for(int i=0;i<mList.size();i++)
        {
            mList.get(i).setChecked(a);
        }
    }


    public float getShopPrice()
    {
        float price=0;
        for(int i=0;i<mList.size();i++)
        {
            if(mList.get(i).isChecked())
            {
                price+=mList.get(i).getPrice();
            }

        }
        return price;
    }








    //接口
    public interface AdapterCallback
    {
        void setChildCheck(View v,int i);

    }

    private AdapterCallback adapterCallback;

    public void setCallback(AdapterCallback adapterCallback)
    {
        this.adapterCallback=adapterCallback;
    }
}
