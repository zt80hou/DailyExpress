package com.dlt.express.main;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dlt.express.R;
import com.dlt.express.api.CustomerListBean;
import com.hzlh.sdk.util.YBaseAdapter;

import java.util.List;

/**
 * 描述：客户列表适配器
 * 日期: 2022/12/4 11:37
 *
 * @author Zhout
 */
public class CustomerListAdapter extends YBaseAdapter<CustomerListBean.DataBean.ContentBean> {

    public CustomerListAdapter(Context context, List<CustomerListBean.DataBean.ContentBean> dataList) {
        super(context, dataList);
        this.dataList = dataList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.customer_list_item, null);
            holder.tvName = convertView.findViewById(R.id.tvName);
            holder.tvCode = convertView.findViewById(R.id.tvCode);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvName.setText(dataList.get(position).getCustomerName());
        holder.tvCode.setText(dataList.get(position).getCustomerNo());
        return convertView;
    }

    private static class ViewHolder {
        TextView tvName;
        TextView tvCode;
    }
}
