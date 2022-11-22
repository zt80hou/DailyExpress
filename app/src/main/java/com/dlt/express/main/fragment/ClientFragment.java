package com.dlt.express.main.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.dlt.express.R;
import com.dlt.express.base.AppFragment;
import com.dlt.express.main.banner.BannerClickHandler;
import com.dlt.express.main.banner.BannerImageLoader;
import com.hzlh.sdk.pic.YPic;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：客户端
 * 作者：Zhout
 * 日期：2020/8/9 10:45
 */
public class ClientFragment extends AppFragment implements View.OnClickListener {
    private Context context;
    private Banner banner;
    private TextView tvMyExpress;
    private TextView tvMyOrder;
    private List<String> bannerList = new ArrayList<>();// banner条数据

    public static ClientFragment getInstance() {
        ClientFragment fragment = new ClientFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(context, R.layout.fragment_client, null);
        banner = (Banner) view.findViewById(R.id.banner);
        tvMyExpress = (TextView) view.findViewById(R.id.tvMyExpress);
        tvMyOrder = (TextView) view.findViewById(R.id.tvMyOrder);
        initBanner();
        tvMyExpress.setOnClickListener(this);
        tvMyOrder.setOnClickListener(this);
        return view;
    }

    /**
     * 初始化banner
     */
    private void initBanner() {
        bannerList.clear();
        bannerList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1596977646104&di=b67953eac500d9bdd7e2f22ca10bb6d7&imgtype=0&src=http%3A%2F%2Fa.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2Fcc11728b4710b912d81c7b33c3fdfc0393452219.jpg");
        bannerList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1596977657314&di=bd633fa776e329d77afe3a637785a825&imgtype=0&src=http%3A%2F%2Fa1.att.hudong.com%2F81%2F71%2F01300000164151121808718718556.jpg");
        bannerList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1596977657311&di=42d8c5612590be24fda823c7719ac370&imgtype=0&src=http%3A%2F%2Ffile01.16sucai.com%2Fd%2Ffile%2F2011%2F1012%2F20111012020018990.jpg");
        BannerImageLoader imageLoader = new BannerImageLoader(YPic.Shape.SQUARE, false);
        banner.setImageLoader(imageLoader);
        //设置图片集合
        banner.setImages(bannerList);
        //设置banner动画效果
        // banner.setBannerAnimation(Transformer.DepthPage);
        //设置轮播时间
        banner.setDelayTime(5000);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                BannerClickHandler.getInstance().onBannerClick(context);
            }
        });
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvMyExpress:
                break;
            case R.id.tvMyOrder:
                break;
        }
    }
}
