package com.dlt.express.api;

/**
 * 描述：快递信息
 * 作者：Zhout
 * 日期：2020/8/26 22:43
 */
public class ExpressInfo {
    private String id;
    private String createTime;
    private String updateTime;
    private String creatorId;
    private String updatorId;
    private boolean deleted;
    private boolean disabled;
    private String remark;
    private String userId;
    private String departmentId;
    private String expressOrderId;
    private String packageName;
    private String packageType;
    private String fromExpressNo;
    private String expressType;
    private String goodsShelvesId;
    private String packageStatus;
    private String charge;
    private double weight;
    private double volume;
    private double cost;
    private String fileUploadId;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getExpressOrderId() {
        return expressOrderId;
    }

    public void setExpressOrderId(String expressOrderId) {
        this.expressOrderId = expressOrderId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getFromExpressNo() {
        return fromExpressNo;
    }

    public void setFromExpressNo(String fromExpressNo) {
        this.fromExpressNo = fromExpressNo;
    }

    public String getExpressType() {
        return expressType;
    }

    public void setExpressType(String expressType) {
        this.expressType = expressType;
    }

    public String getGoodsShelvesId() {
        return goodsShelvesId;
    }

    public void setGoodsShelvesId(String goodsShelvesId) {
        this.goodsShelvesId = goodsShelvesId;
    }

    public String getPackageStatus() {
        return packageStatus;
    }

    public void setPackageStatus(String packageStatus) {
        this.packageStatus = packageStatus;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getFileUploadId() {
        return fileUploadId;
    }

    public void setFileUploadId(String fileUploadId) {
        this.fileUploadId = fileUploadId;
    }
}
