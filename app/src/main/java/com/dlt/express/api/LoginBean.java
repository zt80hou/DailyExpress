package com.dlt.express.api;

import com.dlt.express.base.AppBaseBean;

import java.util.List;

/**
 * 描述：用户信息
 * 作者：Zhout
 * 日期：2020/8/4 9:25
 */
public class LoginBean extends AppBaseBean {

//    {
//        "status": "SUCCESS",
//            "msg": "登陆成功",
//            "data": {
//        "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIzMDI1OTkxNDM1Njg4Mzg2NTYiLCJpYXQiOjE1OTY3MjU5ODAsImV4cCI6MTU5NjgxMjM4MH0.EGYaGHPjwCq58EA7qb84KamuyUGUBtT4neXuZBQgc0O4qpJii6YXSEfATko_S48Y94zjMORatHHBSPHLLSAJ8w",
//                "tokenType": "Bearer",
//                "userInfo": {
//            "id": "302599143568838656",
//                    "createTime": "2020-07-21 17:54:14",
//                    "updateTime": "2020-07-25 15:32:30",
//                    "creatorId": "0",
//                    "updatorId": "0",
//                    "deleted": false,
//                    "disabled": false,
//                    "remark": null,
//                    "departmentId": "301526153204731904",
//                    "userNo": "100008",
//                    "username": "admin",
//                    "nickName": "腾讯一哥",
//                    "realName": "站点老板",
//                    "email": "100008",
//                    "sex": {
//                "value": "1",
//                        "text": "男性"
//            },
//            "address": "345",
//                    "phoneNumber": "100008",
//                    "birthday": "2020-07-07",
//                    "accountNonLocked": true,
//                    "accountNonExpired": true,
//                    "credentialsNonExpired": true,
//                    "lastUpdatePasswordDate": "2020-07-07 00:00:00",
//                    "department": null,
//                    "roles": [],
//            "roleIds": null,
//                    "enabled": true
//        }
//    }
//    }
//
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
        private UserInfoBean userInfo;// 用户信息

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

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public static class UserInfoBean {
            private String id;
            private String createTime;
            private String updateTime;
            private String creatorId;
            private String updatorId;
            private boolean deleted;
            private boolean disabled;
            private Object remark;
            private String departmentId;
            private String userNo;
            private String username;
            private String nickName;// 昵称
            private String realName;//真实姓名
            private String email;
            private SexBean sex;
            private String address;
            private String phoneNumber;
            private String birthday;
            private boolean accountNonLocked;
            private boolean accountNonExpired;
            private boolean credentialsNonExpired;
            private String lastUpdatePasswordDate;
            private Object department;
            private Object roleIds;
            private boolean enabled;
            private List<?> roles;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public String getCreatorId() {
                return creatorId;
            }

            public void setCreatorId(String creatorId) {
                this.creatorId = creatorId;
            }

            public String getUpdatorId() {
                return updatorId;
            }

            public void setUpdatorId(String updatorId) {
                this.updatorId = updatorId;
            }

            public boolean isDeleted() {
                return deleted;
            }

            public void setDeleted(boolean deleted) {
                this.deleted = deleted;
            }

            public boolean isDisabled() {
                return disabled;
            }

            public void setDisabled(boolean disabled) {
                this.disabled = disabled;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public String getDepartmentId() {
                return departmentId;
            }

            public void setDepartmentId(String departmentId) {
                this.departmentId = departmentId;
            }

            public String getUserNo() {
                return userNo;
            }

            public void setUserNo(String userNo) {
                this.userNo = userNo;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public SexBean getSex() {
                return sex;
            }

            public void setSex(SexBean sex) {
                this.sex = sex;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getPhoneNumber() {
                return phoneNumber;
            }

            public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public boolean isAccountNonLocked() {
                return accountNonLocked;
            }

            public void setAccountNonLocked(boolean accountNonLocked) {
                this.accountNonLocked = accountNonLocked;
            }

            public boolean isAccountNonExpired() {
                return accountNonExpired;
            }

            public void setAccountNonExpired(boolean accountNonExpired) {
                this.accountNonExpired = accountNonExpired;
            }

            public boolean isCredentialsNonExpired() {
                return credentialsNonExpired;
            }

            public void setCredentialsNonExpired(boolean credentialsNonExpired) {
                this.credentialsNonExpired = credentialsNonExpired;
            }

            public String getLastUpdatePasswordDate() {
                return lastUpdatePasswordDate;
            }

            public void setLastUpdatePasswordDate(String lastUpdatePasswordDate) {
                this.lastUpdatePasswordDate = lastUpdatePasswordDate;
            }

            public Object getDepartment() {
                return department;
            }

            public void setDepartment(Object department) {
                this.department = department;
            }

            public Object getRoleIds() {
                return roleIds;
            }

            public void setRoleIds(Object roleIds) {
                this.roleIds = roleIds;
            }

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }

            public List<?> getRoles() {
                return roles;
            }

            public void setRoles(List<?> roles) {
                this.roles = roles;
            }

            public static class SexBean {
                private String value;
                private String text;

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }
            }
        }
    }
}
