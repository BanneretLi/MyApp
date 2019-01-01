package banners;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * 作者：穆佳琪
 * 时间：2018/12/30 11:52.
 */

public class MyBanner extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //初始化Glid包
        Glide.with(context).load(path).into(imageView);
    }
}
