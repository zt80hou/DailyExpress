package com.dlt.express.api;

import android.content.Context;

import com.dlt.express.Constants;
import com.dlt.express.base.AppBaseBean;
import com.dlt.express.base.AppCallBack;
import com.dlt.express.base.MRequest;
import com.dlt.express.util.JsonUtil;
import com.hzlh.sdk.net.HttpMap;

import java.util.HashMap;

/**
 * 描述：服务端相关Api
 * 作者：Zhout
 * 日期：2020/8/9 18:58
 */
public class ServerApi {

    /**
     * 收单入库
     *
     * @param context
     * @param callBack
     */
    public void received(Context context, ReceivedRequestBean bean, AppCallBack<AppBaseBean> callBack) {
        String url = Constants.baseAddr + "/expressPackage/received";
        String json = JsonUtil.toJson(bean);
        MRequest.getInstance().postJson(context, url, json, AppBaseBean.class, callBack);
    }


    /**
     * 客户查询
     *
     * @param context
     * @param pageIndex 页码 0开始
     * @param callBack
     */
    public void queryCustomer(Context context, int pageIndex, AppCallBack<CustomerListBean> callBack) {
        String json = "{\"deleted\":[{\"datas\":[false],\"model\":\"EQUAL\"}]}";
        String url = Constants.baseAddr + "/customer/get/list/condition/member?page=" + pageIndex + "&size=20";
        MRequest.getInstance().postJson(context, url, json, CustomerListBean.class, callBack);
    }

    /**
     * 仓库查询
     *
     * @param context
     * @param pageIndex 页码 0开始
     * @param callBack
     */
    public void queryWarehouse(Context context, int pageIndex, AppCallBack<WareHouseListBean> callBack) {
        String json = "{\"deleted\":[{\"datas\":[false],\"model\":\"EQUAL\"}]}";
        String url = Constants.baseAddr + "/warehouse/get/list/condition/member?page=" + pageIndex + "&size=20";
        MRequest.getInstance().postJson(context, url, json, WareHouseListBean.class, callBack);
    }

    /**
     * 客户地址列表
     *
     * @param context
     * @param pageIndex 页码 0开始
     * @param callBack
     */
    public void queryAddress(Context context, int pageIndex, String customerId, AppCallBack<AddressListBean> callBack) {
        String url = Constants.baseAddr + "/destination/get/list/condition/member?page=" + pageIndex + "&size=20";
        String json = "{\"deleted\":[{\"datas\":[false],\"model\":\"EQUAL\"}],\"customerId\":[{\"datas\":[" + customerId + "],\"model\":\"EQUAL\"}]}";
        MRequest.getInstance().postJson(context, url, json, AddressListBean.class, callBack);
    }


    /**
     * 上架
     *
     * @param context
     * @param shelvesNo 货架ID，扫码读取
     * @param expressNo 快递单号
     * @param callBack
     */
    public void putOn(Context context, String shelvesNo, String expressNo, AppCallBack<AppBaseBean> callBack) {
        HashMap<String, String> map = HttpMap.getMap();

        String url = Constants.baseAddr + "/expressPackage/doStorage/" + shelvesNo + "/" + expressNo;
        MRequest.getInstance().post(context, url, AppBaseBean.class, map, callBack);
    }

    /**
     * 查询待上架快递
     *
     * @param context
     * @param shelvesNo 货架ID
     * @param callBack
     */
    public void queryWaitPutOnPackages(Context context, String shelvesNo, AppCallBack<ExpressListBean> callBack) {
        HashMap<String, String> map = HttpMap.getMap();
        String url = Constants.baseAddr + "/expressPackage/storage/waiting/" + shelvesNo;
        MRequest.getInstance().post(context, url, ExpressListBean.class, map, callBack);
    }



    /**
     * 下架
     *
     * @param context
     * @param orderExpressNo    订单物流单号，扫码读取
     * @param packageExpressNos 快递单号，扫码读取, 多个逗号分开
     * @param callBack
     */
    public void putOff(Context context, String orderExpressNo, String packageExpressNos, AppCallBack<AppBaseBean> callBack) {
        HashMap<String, String> map = HttpMap.getMap(new HttpMap.Action() {
            public void addParams(HashMap<String, String> map) {
                map.put("orderExpressNo", orderExpressNo);
                map.put("packageExpressNos", packageExpressNos);
            }
        });

        String url = Constants.baseAddr + "/expressPackage/out";
        String json = JsonUtil.toJson(map);
        MRequest.getInstance().postJson(context, url, json, AppBaseBean.class, callBack);
    }

    /**
     * 查询待下架快递
     *
     * @param context
     * @param orderExpressNo 订单物流号
     * @param callBack
     */
    public void queryWaitPutOffPackages(Context context, String orderExpressNo, AppCallBack<ExpressListBean> callBack) {
        HashMap<String, String> map = HttpMap.getMap(new HttpMap.Action() {
            public void addParams(HashMap<String, String> map) {
                map.put("orderExpressNo", orderExpressNo);
            }
        });

        String url = Constants.baseAddr + "/expressPackage/waitOutPkgs";
        String json = JsonUtil.toJson(map);
        MRequest.getInstance().postJson(context, url, json, ExpressListBean.class, callBack);
    }


}
