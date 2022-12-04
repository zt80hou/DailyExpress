package com.dlt.express.view.refresh;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.dlt.express.R;
import com.lcodecore.tkrefreshlayout.IHeaderView;
import com.lcodecore.tkrefreshlayout.OnAnimEndListener;

/**
 * 描述：头部加载
 * 作者：Zhout
 * 日期：2021/4/16 16:41
 */

public class HeaderLoadingView extends FrameLayout implements IHeaderView {
    private ImageView loadingView;
    private TextView tv;

    public HeaderLoadingView(Context context) {
        this(context, null);
    }

    public HeaderLoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeaderLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
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
    public void onPullingDown(float fraction, float maxHeadHeight, float headHeight) {

    }

    @Override
    public void onPullReleasing(float fraction, float maxHeadHeight, float headHeight) {

    }

    @Override
    public void startAnim(float maxHeadHeight, float headHeight) {
        ((AnimationDrawable) loadingView.getDrawable()).start();
    }

    @Override
    public void onFinish(OnAnimEndListener listener) {
        listener.onAnimEnd();
    }

    @Override
    public void reset() {

    }


}

