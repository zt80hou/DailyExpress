package com.dlt.express.api;

import com.dlt.express.base.AppBaseBean;

/**
 * 描述：用户信息
 * 日期: 2022/12/3 13:45
 *
 * @author Zhout
 */
public class UserInfoBean extends AppBaseBean {

//    {
//        "status": "SUCCESS",
//            "msg": "请求已受理",
//            "data": {
//        "id": "1",
//                "createTime": "2019-10-24 10:16:19",
//                "updateTime": "2022-11-12 10:12:28",
//                "creatorId": "1",
//                "updatorId": "1",
//                "deleted": false,
//                "disabled": false,
//                "remark": null,
//                "departmentId": "1",
//                "userNo": "100001",
//                "username": "admin",
//                "nickName": "不知道",
//                "realName": "超级管理员",
//                "email": null,
//                "sex": "2",
//                "address": "方城县2",
//                "phoneNumber": "100001",
//                "birthday": "2019-07-11",
//                "parentUserId": "0",
//                "serial": 10100,
//                "leaf": false,
//                "department": null,
//                "children": [],
//        "score": "0"
//    }
//    }



    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        private String createTime;
        private String updateTime;
        private String creatorId;
        private String updatorId;
        private boolean deleted;
        private boolean disabled;
        private String remark;
        private String departmentId;
        private String userNo;
        private String username;// 用户名
        private String nickName;//昵称
        private String realName;//真实姓名
        private String email;
        private String sex;//性别 X男 Y女
        private String address;//地址
        private String phoneNumber;//电话
        private String birthday;//生日
        private String parentUserId;
        private int serial;
        private boolean leaf;
        private String score;

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

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
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

        public String getParentUserId() {
            return parentUserId;
        }

        public void setParentUserId(String parentUserId) {
            this.parentUserId = parentUserId;
        }

        public int getSerial() {
            return serial;
        }

        public void setSerial(int serial) {
            this.serial = serial;
        }

        public boolean isLeaf() {
            return leaf;
        }

        public void setLeaf(boolean leaf) {
            this.leaf = leaf;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }
    }
}
