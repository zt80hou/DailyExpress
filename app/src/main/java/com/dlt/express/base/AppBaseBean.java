package com.dlt.express.base;

/**
 * 项目中使用，封装底层对象模型
 * Created by 夏吧吧Ace on 2018/9/4.
 */
public class AppBaseBean {
    private String status; //	错误码； SUCCESS
    private String msg; //	消息；

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

}

