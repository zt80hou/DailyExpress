package com.dlt.express.main.banner;

import android.content.Context;

/**
 * 作者：Zhout
 * 时间：2017/12/18 15:02
 * 描述：banner点击事件处理类
 */

public class BannerClickHandler {

    private static BannerClickHandler instance;

    private BannerClickHandler() {
    }

    public static BannerClickHandler getInstance() {
        if (instance == null) {
            instance = new BannerClickHandler();
        }
        return instance;
    }


    /**
     * 通用banner点击事件
     */
    public void onBannerClick(Context context) {


    }
}

