package adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwei.week01exe111230.MainActivity;
import com.bwei.week01exe111230.R;
import com.bwei.week01exe111230.ScendActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import beans.Goods;
import model.MainModel;

/**
 * 作者：穆佳琪
 * 时间：2018/12/30 9:56.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Goods.DataBean> list;
    private Context context;

    public MyAdapter(List<Goods.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new ViewHodel(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((ViewHodel)holder).item_title.setText(list.get(position).getTitle());
        ((ViewHodel)holder).item_price.setText("¥："+list.get(position).getPrice());

        //切割图片
        String images = list.get(position).getImages();//得到图片集
        String[] split = images.split("\\|");//得到一个图片
        if (split.length>0) {
            //将https成http  进行联网显示
            String replace = split[0].replace("https", "http");
            Uri parse = Uri.parse(replace);
            //Glide.with(context).load(replace).into(((ViewHodel)holder).item_img);//设置图片
            ((ViewHodel)holder).item_img.setImageURI(parse);
        }

        ((ViewHodel) holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.OnItemClick(v,position);
            }
        });
        //长按
        ((ViewHodel) holder).itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onItemLongClickListener.OnItemLongClick(v,position);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class ViewHodel extends RecyclerView.ViewHolder {

        private final TextView item_price;
        private final SimpleDraweeView item_img;
        private final TextView item_title;

        public ViewHodel(View view) {
            super(view);
            item_img = view.findViewById(R.id.item_img);
            item_title = view.findViewById(R.id.item_title);
            item_price = view.findViewById(R.id.item_price);

        }
    }



    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void OnItemClick(View view, int position);
    }
    private OnItemLongClickListener onItemLongClickListener;

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public interface OnItemLongClickListener{
        void OnItemLongClick(View view, int position);
    }

}
