package com.dlt.express.api;

import com.dlt.express.base.AppBaseBean;

import java.util.List;

/**
 * 描述：地址列表
 * 日期: 2022/12/4 11:31
 *
 * @author Zhout
 */
public class AddressListBean extends AppBaseBean {

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
            private String customerId;
            private String receiverName;
            private String telphone;
            private String address;


            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCustomerId() {
                return customerId;
            }

            public void setCustomerId(String customerId) {
                this.customerId = customerId;
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
    }
}
