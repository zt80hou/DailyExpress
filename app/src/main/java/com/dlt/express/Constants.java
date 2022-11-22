package com.dlt.express;

import com.dlt.express.api.LoginBean;

/**
 * 描述：常量
 * 作者：Zhout
 * 日期：2020/8/3 8:58
 */
public class Constants {

    static final boolean OPEN_OSS_SUPPORT = false;  //支持oss
    static final String OSS_PREFIX = "mtmy.oss"; //oss资源匹配字串
    static final String OSS_PREFIX2 = "oss.";  //oss资源匹配字串
    public static final String baseAddr = "http://121.37.206.52/express/api/v1";  // 接口地址
    //    public static final String baseAddr = "http://112.74.205.140/express";  // 接口地址
    public static final String SP_KEY_LOGIN_ACCOUNT = "sp_key_login_account";
    public static final String SP_KEY_LOGIN_PWD = "sp_key_login_pwd";
    public static LoginBean loginBean = null;

    public static boolean isLogin() {
        return loginBean != null && loginBean.getData() != null && loginBean.getData().getUserInfo() != null;
    }

}
