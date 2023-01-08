package com.dlt.express.main;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dlt.express.R;
import com.dlt.express.api.AddressListBean;
import com.hzlh.sdk.util.YBaseAdapter;

import java.util.List;

/**
 * 描述：客户列适配器
 * 日期: 2022/12/4 11:37
 *
 * @author Zhout
 */
public class AddressListAdapter extends YBaseAdapter<AddressListBean.DataBean.ContentBean> {

    public AddressListAdapter(Context context, List<AddressListBean.DataBean.ContentBean> dataList) {
        super(context, dataList);
        this.dataList = dataList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.address_list_item, null);
            holder.tvName = convertView.findViewById(R.id.tvName);
            holder.tvTelephone = convertView.findViewById(R.id.tvTelephone);
            holder.tvAddress = convertView.findViewById(R.id.tvAddress);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvName.setText(dataList.get(position).getReceiverName());
        holder.tvTelephone.setText(dataList.get(position).getTelphone());
        holder.tvAddress.setText(dataList.get(position).getAddress());
        return convertView;
    }

    private static class ViewHolder {
        TextView tvName;
        TextView tvTelephone;
        TextView tvAddress;
    }
}
