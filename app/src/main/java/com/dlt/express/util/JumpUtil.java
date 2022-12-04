package com.dlt.express.util;

import android.content.Context;
import android.content.Intent;

import com.dlt.express.Constants;
import com.dlt.express.module.user.LoginActivity;
import com.dlt.express.module.web.WebActivity;

/**
 * 描述：跳转管理
 * 日期: 2022/12/3 15:08
 *
 * @author Zhout
 */
public class JumpUtil {
    /**
     * 用户登录
     * @param context 上下文
     */
    public static void jumpLogin(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    /**
     * 关于我们
     * @param context 上下文
     */
    public static void jumpAboutUs(Context context) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("title", "关于我们");
        intent.putExtra("url", Constants.aboutUsUrl);
        context.startActivity(intent);
    }


    /**
     * 客户服务
     * @param context 上下文
     */
    public static void jumpCustomerService(Context context) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("title", "客户服务");
        intent.putExtra("url", Constants.customerServiceUrl);
        context.startActivity(intent);
    }


    /**
     * 隐私政策
     * @param context 上下文
     */
    public static void jumpPrivacyPolice(Context context) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("title", "隐私政策");
        intent.putExtra("url", Constants.privacyPolicyUrl);
        context.startActivity(intent);
    }


    /**
     * 用户协议
     * @param context 上下文
     */
    public static void jumpUserAgreement(Context context) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("title", "用户协议");
        intent.putExtra("url", Constants.userAgreementUrl);
        context.startActivity(intent);
    }
}
