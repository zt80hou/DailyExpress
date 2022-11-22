package com.dlt.express.base;

/**
 * 项目中使用，封装底层对象模型
 * Created by 夏吧吧Ace on 2018/9/4.
 */
public class AppBaseBean {
    private String status; //	错误码；
    private String msg; //	错误消息（见附录-错误码）；
    private String info; // 错误消息详解

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}

