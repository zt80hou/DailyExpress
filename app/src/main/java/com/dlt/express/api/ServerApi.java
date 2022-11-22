package com.dlt.express.api;

import android.content.Context;

import com.dlt.express.Constants;
import com.dlt.express.base.AppBaseBean;
import com.dlt.express.base.AppCallBack;
import com.dlt.express.base.MRequest;
import com.hzlh.sdk.net.HttpMap;

import java.util.HashMap;
import java.util.List;

/**
 * 描述：服务端相关Api
 * 作者：Zhout
 * 日期：2020/8/9 18:58
 */
public class ServerApi {
    /**
     * 上架
     *
     * @param context
     * @param goodsShelvesId    货架ID，扫码读取
     * @param packageExpressNos 快递单号，扫码读取, 多个逗号分开
     * @param callBack
     */
    public void putOn(final Context context, final String goodsShelvesId, final List<String> packageExpressNos, AppCallBack<AppBaseBean> callBack) {
        HashMap<String, String> map = HttpMap.getMap(new HttpMap.Action() {
            public void addParams(HashMap<String, String> map) {
                map.put("goodsShelvesId", goodsShelvesId);
                map.put("packageExpressNos", packageExpressNos.toString().replace("[", "").replace("]", "").replace(" ", ""));
            }
        });

        String url = Constants.baseAddr + "/expressPackage/storage";
        String json = MRequest.gson.toJson(map);
        MRequest.getInstance().postJson(context, url, json, AppBaseBean.class, callBack);
    }

    /**
     * 上架
     *
     * @param context
     * @param goodsShelvesId    货架ID，扫码读取
     * @param packageExpressNos 快递单号，扫码读取, 多个逗号分开
     * @param callBack
     */
    public void putOn(final Context context, final String goodsShelvesId, String packageExpressNos, AppCallBack<AppBaseBean> callBack) {
        HashMap<String, String> map = HttpMap.getMap(new HttpMap.Action() {
            public void addParams(HashMap<String, String> map) {
                map.put("goodsShelvesId", goodsShelvesId);
                map.put("packageExpressNos", packageExpressNos);
            }
        });

        String url = Constants.baseAddr + "/expressPackage/storage";
        String json = MRequest.gson.toJson(map);
        MRequest.getInstance().postJson(context, url, json, AppBaseBean.class, callBack);
    }

    /**
     * 查询待上架快递
     *
     * @param context
     * @param goodsShelvesId 货架ID
     * @param callBack
     */
    public void queryWaitPutOnPackages(final Context context, final String goodsShelvesId, AppCallBack<ExpressListBean> callBack) {
        HashMap<String, String> map = HttpMap.getMap(new HttpMap.Action() {
            public void addParams(HashMap<String, String> map) {
                map.put("goodsShelvesId", goodsShelvesId);
            }
        });

        String url = Constants.baseAddr + "/expressPackage/waitStoragePkgs";
        String json = MRequest.gson.toJson(map);
        MRequest.getInstance().postJson(context, url, json, ExpressListBean.class, callBack);
    }


    /**
     * 下架
     *
     * @param context
     * @param orderExpressNo    订单物流单号，扫码读取
     * @param packageExpressNos 快递单号，扫码读取, 多个逗号分开
     * @param callBack
     */
    public void putOff(final Context context, final String orderExpressNo, final List<String> packageExpressNos, AppCallBack<AppBaseBean> callBack) {
        HashMap<String, String> map = HttpMap.getMap(new HttpMap.Action() {
            public void addParams(HashMap<String, String> map) {
                map.put("orderExpressNo", orderExpressNo);
                map.put("packageExpressNos", packageExpressNos.toString().replace("[", "").replace("]", "").replace(" ", ""));
            }
        });

        String url = Constants.baseAddr + "/expressPackage/out";
        String json = MRequest.gson.toJson(map);
        MRequest.getInstance().postJson(context, url, json, AppBaseBean.class, callBack);
    }
    /**
     * 下架
     *
     * @param context
     * @param orderExpressNo    订单物流单号，扫码读取
     * @param packageExpressNos 快递单号，扫码读取, 多个逗号分开
     * @param callBack
     */
    public void putOff(final Context context, final String orderExpressNo,String  packageExpressNos, AppCallBack<AppBaseBean> callBack) {
        HashMap<String, String> map = HttpMap.getMap(new HttpMap.Action() {
            public void addParams(HashMap<String, String> map) {
                map.put("orderExpressNo", orderExpressNo);
                map.put("packageExpressNos", packageExpressNos);
            }
        });

        String url = Constants.baseAddr + "/expressPackage/out";
        String json = MRequest.gson.toJson(map);
        MRequest.getInstance().postJson(context, url, json, AppBaseBean.class, callBack);
    }

    /**
     * 查询待下架快递
     *
     * @param context
     * @param orderExpressNo 订单物流号
     * @param callBack
     */
    public void queryWaitPutOffPackages(final Context context, final String orderExpressNo, AppCallBack<ExpressListBean> callBack) {
        HashMap<String, String> map = HttpMap.getMap(new HttpMap.Action() {
            public void addParams(HashMap<String, String> map) {
                map.put("orderExpressNo", orderExpressNo);
            }
        });

        String url = Constants.baseAddr + "/expressPackage/waitOutPkgs";
        String json = MRequest.gson.toJson(map);
        MRequest.getInstance().postJson(context, url, json, ExpressListBean.class, callBack);
    }


}
