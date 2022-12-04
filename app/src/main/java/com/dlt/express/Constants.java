package com.dlt.express;

import com.dlt.express.api.LoginBean;
import com.dlt.express.api.UserInfoBean;

/**
 * 描述：常量
 * 作者：Zhout
 * 日期：2020/8/3 8:58
 */
public class Constants {

    static final boolean OPEN_OSS_SUPPORT = false;  //支持oss
    static final String OSS_PREFIX = "mtmy.oss"; //oss资源匹配字串
    static final String OSS_PREFIX2 = "oss.";  //oss资源匹配字串
    public static final String baseAddr = "http://121.37.243.43:8888/platformV2/api/v1";  // 接口地址
    public static final String SP_KEY_LOGIN_ACCOUNT = "sp_key_login_account";
    public static final String SP_KEY_LOGIN_PWD = "sp_key_login_pwd";
    public static LoginBean loginBean = null;
    public static UserInfoBean userInfoBean = null;

    private static final String webAddr = "http://121.37.243.43:8888/platformV2/html";  // 网页地址
    // 用户协议
    public static String userAgreementUrl = webAddr + "/logistics/app/addition/user_agreement.html";
    //隐私政策
    public static String privacyPolicyUrl = webAddr + "/logistics/app/addition/privacy_policy.html";
    // 客户服务
    public static String customerServiceUrl = webAddr + "/logistics/app/addition/customer_service.html";
    // 关于我们
    public static String aboutUsUrl = webAddr + "/logistics/app/addition/company_profile.html";

    public static boolean isLogin() {
        return loginBean != null && userInfoBean != null && userInfoBean.getData() != null;
    }


    public static UserInfoBean.DataBean getUserInfo() {
        if (userInfoBean != null && userInfoBean.getData() != null) {
            return userInfoBean.getData();
        }
        return new UserInfoBean.DataBean();
    }
}
