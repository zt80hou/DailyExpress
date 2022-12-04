package com.dlt.express.main;


/**
 * 描述：客户列表选择发通知
 * 作者：Zhout
 * 日期：2022/12/4 11:08
 */
public class AddressEvent {
    private String id;
    private String receiverName;// 收货人姓名
    private String telphone;//电话
    private String address;//地址

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

