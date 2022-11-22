package com.dlt.express.api;

import android.content.Context;

import com.dlt.express.Constants;
import com.dlt.express.base.AppBaseBean;
import com.dlt.express.base.AppCallBack;
import com.dlt.express.base.MRequest;
import com.hzlh.sdk.net.HttpMap;

import java.util.HashMap;

/**
 * 描述：用户相关接口
 * 作者：Zhout
 * 日期：2020/8/4 9:18
 */
public class UserApi {
    /**
     * 登录
     *
     * @param context
     * @param account  账号
     * @param password 密码
     * @param callBack
     */
    public void login(final Context context, final String account, final String password, AppCallBack<LoginBean> callBack) {
        HashMap<String, String> map = HttpMap.getMap(new HttpMap.Action() {
            public void addParams(HashMap<String, String> map) {
                map.put("principal", account);
                map.put("password", password);
            }
        });

        String url = Constants.baseAddr + "/auth/login";
        String json = MRequest.gson.toJson(map);
        MRequest.getInstance().postJson(context, url, json, LoginBean.class, callBack);
    }

    /**
     * 退出登录
     *
     * @param context
     * @param callBack
     */
    public void logout(final Context context, AppCallBack<AppBaseBean> callBack) {
        HashMap<String, String> map = HttpMap.getMap(new HttpMap.Action() {
            public void addParams(HashMap<String, String> map) {
            }
        });

        String json = MRequest.gson.toJson(map);
        String url = Constants.baseAddr + "/auth/logout";
        MRequest.getInstance().postJson(context, url, json, AppBaseBean.class, callBack);
    }
}
