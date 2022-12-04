package com.dlt.express.api;

import android.content.Context;

import com.dlt.express.Constants;
import com.dlt.express.base.AppBaseBean;
import com.dlt.express.base.AppCallBack;
import com.dlt.express.base.MRequest;
import com.dlt.express.util.JsonUtil;
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
    public void login( Context context,  String account,  String password, AppCallBack<LoginBean> callBack) {
        HashMap<String, String> map = HttpMap.getMap(new HttpMap.Action() {
            public void addParams(HashMap<String, String> map) {
                map.put("principal", account);
                map.put("password", password);
            }
        });

        String url = Constants.baseAddr + "/auth/login";
        String json = JsonUtil.toJson(map);
        MRequest.getInstance().postJson(context, url, json, LoginBean.class, callBack);
    }

    /**
     * 获取用户信息
     *
     * @param context
     * @param callBack
     */
    public void getUserInfo( Context context, AppCallBack<UserInfoBean> callBack) {
        HashMap<String, String> map = HttpMap.getMap();

        String url = Constants.baseAddr + "/user/get/currUser/info";
        MRequest.getInstance().post(context, url, UserInfoBean.class, map, callBack);
    }


    /**
     * 退出登录
     *
     * @param context
     * @param callBack
     */
    public void logout( Context context, AppCallBack<AppBaseBean> callBack) {
        HashMap<String, String> map = HttpMap.getMap(new HttpMap.Action() {
            public void addParams(HashMap<String, String> map) {
            }
        });

        String json = JsonUtil.toJson(map);
        String url = Constants.baseAddr + "/auth/logout";
        MRequest.getInstance().postJson(context, url, json, AppBaseBean.class, callBack);
    }
}
