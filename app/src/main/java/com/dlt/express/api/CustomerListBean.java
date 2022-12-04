package com.dlt.express.api;

import com.dlt.express.base.AppBaseBean;

import java.util.List;

/**
 * 描述：客户列表
 * 日期: 2022/12/4 11:31
 *
 * @author Zhout
 */
public class CustomerListBean extends AppBaseBean {
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ContentBean> content;

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public static class ContentBean {
            private String id;
            private String customerNo;// 客户编号
            private String customerName;//客户名称

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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
    }
}
