package com.dlt.express.api;

/**
 * 描述：快递信息
 * 作者：Zhout
 * 日期：2020/8/26 22:43
 */
public class ExpressInfo {
    private String expressNo; //快递单号
    private String customerNo; //客户编号
    private String customerName; //客户名称
    private String weight;//重量
    private String volume;//体积
    private String packageStatus;//RECEIVED,未上架；   STORAGED，在架子上； PACKED 已打包
    private String updateTime;//更新时间
    private String packageName;//物品名称
    private String goodsShelvesNo;//所在货架号


    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getPackageStatus() {
        return packageStatus;
    }

    public void setPackageStatus(String packageStatus) {
        this.packageStatus = packageStatus;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getGoodsShelvesNo() {
        return goodsShelvesNo;
    }

    public void setGoodsShelvesNo(String goodsShelvesNo) {
        this.goodsShelvesNo = goodsShelvesNo;
    }
}
