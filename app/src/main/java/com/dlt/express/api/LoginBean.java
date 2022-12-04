package com.dlt.express.api;

import com.dlt.express.base.AppBaseBean;

/**
 * 描述：用户信息
 * 作者：Zhout
 * 日期：2020/8/4 9:25
 */
public class LoginBean extends AppBaseBean {
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String accessToken;// token
        private String tokenType;
        private String username;// 登录的用户名
        private String userId;// 用户id


        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getTokenType() {
            return tokenType;
        }

        public void setTokenType(String tokenType) {
            this.tokenType = tokenType;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
