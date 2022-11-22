package com.dlt.express.module.user;

/**
 * 描述：登录通知
 * 作者：Zhout
 * 日期：2020/9/14 20:21
 */
public class LoginEvent  {
    private boolean isLogin;

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
}
