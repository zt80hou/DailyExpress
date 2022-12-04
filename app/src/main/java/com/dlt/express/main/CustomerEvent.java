package com.dlt.express.main;


/**
 * 描述：客户列表选择发通知
 * 作者：Zhout
 * 日期：2022/12/4 11:08
 */
public class CustomerEvent {

    private String customerId;
    private String customerNo;
    private String customerName;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}

