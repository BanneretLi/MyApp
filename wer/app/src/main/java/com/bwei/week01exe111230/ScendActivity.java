package com.bwei.week01exe111230;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.youth.banner.Banner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import banners.MyBanner;
import beans.Goods;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ScendActivity extends AppCompatActivity {
    @BindView(R.id.scend_banner)
    Banner scend_banner;
    @BindView(R.id.scend_title)
    TextView scend_title;
    @BindView(R.id.scend_price)
    TextView scend_price;
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scend);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getGoodsBean(Goods.DataBean bean) {
        Log.i("aaa", bean.toString());
        String images = bean.getImages();
        String[] split = images.split("\\|");//得到一个图片
        for (int i = split.length - 1; i > 0; i--) {
            String replace = split[i].replace("https", "http");
            list.add(replace);
        }
        scend_banner.setImageLoader(new MyBanner());
        scend_banner.setImages(list);
        scend_banner.setDelayTime(1000);
        scend_banner.start();
        scend_title.setText(bean.getTitle());
        scend_price.setText("¥："+bean.getBargainPrice());
    }

}
