package com.dlt.express.view.refresh;

import android.content.Context;
import android.util.AttributeSet;

import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;

/**
 * 描述：下拉刷新加载更更多,继承TwinklingRefreshLayout,统一header和footer
 * 作者：Zhout
 * 日期：2020/4/9 10:11
 */
public class RefreshLayout extends TwinklingRefreshLayout {
    private HeaderUiStyle mStyle = HeaderUiStyle.ARROW; // 默认

    public RefreshLayout(Context context) {
        super(context);
        init(context, mStyle);
    }

    public RefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, mStyle);
    }

    public RefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, mStyle);
    }

    private void init(Context context, HeaderUiStyle style) {
        if (style == HeaderUiStyle.CIRCLE) {
            // 仿系统自带的小圆圈刷新
            ProgressLayout progressLayout = new ProgressLayout(context);
            setHeaderView(progressLayout);
            setFloatRefresh(true);// 开启悬浮
            setEnableOverScroll(false);// 禁用回弹
        } else if (style == HeaderUiStyle.LOADING) {
            // 菊花loading
            HeaderLoadingView headerLoadingView = new HeaderLoadingView(context);
            setHeaderView(headerLoadingView);
        } else {
            // 新浪箭头
            HeaderRefreshView headerRefreshView = new HeaderRefreshView(context);
            setHeaderView(headerRefreshView);
        }
        FooterLoadingView footerLoadingView = new FooterLoadingView(context);
        setBottomView(footerLoadingView);
        setMaxHeadHeight(80);// 下拉的最大高度,默认120
    }

    /**
     * 设置头部样式
     *
     * @param style 0 默认 仿新浪微博箭头转向刷新； 1 仿系统自带的小圆圈刷新; 2 头部和底部一样都是加载
     */
    public void setHeaderUIStyle(Context context, HeaderUiStyle style) {
        this.mStyle = style;
        init(context, style);
    }

    public enum HeaderUiStyle {
        ARROW, // 新浪箭头
        CIRCLE, // 小圆圈
        LOADING,// 菊花加载
    }

}
