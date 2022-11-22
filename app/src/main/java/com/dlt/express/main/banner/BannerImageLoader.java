package com.dlt.express.main.banner;

import android.content.Context;
import android.widget.ImageView;

import com.dlt.express.R;
import com.hzlh.sdk.pic.YPic;
import com.youth.banner.loader.ImageLoader;


/**
 * 作者：Zhout
 * 时间：2017/12/18 15:01
 * 描述：统一的加载banner图片
 */

public class BannerImageLoader extends ImageLoader {
    private Context context;
    private YPic.Shape shape;
    private boolean setPadding;


    public BannerImageLoader(YPic.Shape shape, boolean setPadding) {
        this.shape = shape;
        this.setPadding = setPadding;
    }

    public BannerImageLoader() {
        this.shape = YPic.Shape.SQUARE;
        this.setPadding = false;
    }

    //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
    @Override
    public void displayImage(Context context, Object imgObj, ImageView imageView) {
        this.context = context;
        if (imgObj != null) {
            YPic.with(context).into(imageView).load((String) imgObj);
        } else {
            imageView.setImageResource(R.drawable.default_placeholder);
        }
    }

}
