package com.dlt.express.api;

import com.dlt.express.base.AppBaseBean;

import java.util.List;

/**
 * 描述：仓库列表
 * 日期: 2022/12/4 11:31
 *
 * @author Zhout
 */
public class WareHouseListBean extends AppBaseBean {

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
            private String warehouseName;
            private String warehouseNo;
            private String address;


            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getWarehouseName() {
                return warehouseName;
            }

            public void setWarehouseName(String warehouseName) {
                this.warehouseName = warehouseName;
            }

            public String getWarehouseNo() {
                return warehouseNo;
            }

            public void setWarehouseNo(String warehouseNo) {
                this.warehouseNo = warehouseNo;
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
