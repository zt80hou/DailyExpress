package com.dlt.express.view.refresh;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.dlt.express.R;
import com.lcodecore.tkrefreshlayout.IBottomView;

/**
 * 作者：Zhout
 * 时间：2018/11/21 21:37
 * 描述：下拉刷新控件的footerView
 */
public class FooterLoadingView extends FrameLayout implements IBottomView {
    private ImageView loadingView;
    private TextView tv;

    public FooterLoadingView(Context context) {
        this(context, null);
    }

    public FooterLoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FooterLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View rootView = View.inflate(getContext(), R.layout.footer_loaing_view, null);
        loadingView = (ImageView) rootView.findViewById(R.id.iv_loading);
        tv = (TextView) rootView.findViewById(R.id.tv);
        addView(rootView);
    }

    public void setTextColor(int color) {
        tv.setTextColor(color);
    }

    public void setTextName(String name) {
        tv.setText(name);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void onPullingUp(float fraction, float maxHeadHeight, float headHeight) {

    }

    @Override
    public void startAnim(float maxHeadHeight, float headHeight) {
        ((AnimationDrawable) loadingView.getDrawable()).start();
    }

    @Override
    public void onPullReleasing(float fraction, float maxHeadHeight, float headHeight) {

    }

    @Override
    public void onFinish() {

    }

    @Override
    public void reset() {

    }
}

