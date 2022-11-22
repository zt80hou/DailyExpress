package com.dlt.express.api;

import com.dlt.express.base.AppBaseBean;

import java.util.List;

/**
 * 描述：快递列表
 * 作者：Zhout
 * 日期：2020/8/26 22:44
 */
public class ExpressListBean extends AppBaseBean {
    private List<ExpressInfo> data;

    public List<ExpressInfo> getData() {
        return data;
    }

    public void setData(List<ExpressInfo> data) {
        this.data = data;
    }
}
