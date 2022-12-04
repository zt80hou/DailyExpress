package com.dlt.express.api;

/**
 * 描述：入库请求bean
 * 日期: 2022/12/3 17:04
 *
 * @author Zhout
 */
public class ReceivedRequestBean {
    private String expressNo; // 快递单号，选填
    private String warehouseId;// 仓库ID，必填值，从当前用户的仓库清单接口获取列表，进行选择
    private String goodsShelvesNo;// 货架号，选填

    private float length; // 长度/米，必填
    private float width; //宽度/米，必填
    private float height;//高度/米，必填
    private float weight; // 重量kg，必填
    private float volume; // 体积，必填（体积和长宽高，填一个即可，如果有长宽高，可以计算体积，如果客户填了体积，就不再计算了）

    private String customerId;//客户ID，选填，客户清单接口
    private String customerNo;//客户编号，显示用，后台传customerId，没有可不传
    private String customerName;//客户名称，必填，新客户后台会自动新增加

    private String packageName;//货物名称，必填
    private String packageType;//货物类型，必填，静态清单选择，默认一般货物
//    DEFAULT, //一般货物
//    CCC_PRODUCTS, //3C产品
//    PAPER_CARTON, //纸箱纸板
//    METAL_STEEL, //金属钢材
//    PRECISION_INSTRUMENT, //精密仪器
//    CERAMIC_GLASS, //陶瓷玻璃
//    FRESH_AQUATIC, //水产生鲜
//    VEGETABLES_FRUITS, //蔬菜水果
//    OTHER; //其它分类
    private String quantity;//货物件数，必填

    private String destinationId;//收货地址，非必填，选择框
    private String receiverName;//收货人，必填，
    private String telphone;//收货人电话，必填
    private String address;//收货地址，必填

    private String remark;//备注


    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getGoodsShelvesNo() {
        return goodsShelvesNo;
    }

    public void setGoodsShelvesNo(String goodsShelvesNo) {
        this.goodsShelvesNo = goodsShelvesNo;
    }



    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getTelephone() {
        return telphone;
    }

    public void setTelephone(String telephone) {
        this.telphone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
