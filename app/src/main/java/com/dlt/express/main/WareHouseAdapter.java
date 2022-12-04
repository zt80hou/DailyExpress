package com.dlt.express.main;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.dlt.express.R;
import com.dlt.express.api.WareHouseListBean;
import com.hzlh.sdk.util.YBaseAdapter;

import java.util.List;

/**
 * 描述：仓库adapter
 * 日期: 2022/12/4 19:26
 *
 * @author Zhout
 */
public class WareHouseAdapter extends YBaseAdapter implements SpinnerAdapter {
    private List<WareHouseListBean.DataBean.ContentBean> dataList;

    public WareHouseAdapter(Context context, List<WareHouseListBean.DataBean.ContentBean> dataList) {
        super(context, dataList);
        this.dataList = dataList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.spinner_ware_house_item, null);
            holder.tvName = convertView.findViewById(R.id.tvName);
            holder.tvAddress = convertView.findViewById(R.id.tvAddress);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvName.setText(dataList.get(position).getWarehouseName());
        holder.tvAddress.setText(dataList.get(position).getAddress());
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.spinner_ware_house_item, null);
            holder.tvName = convertView.findViewById(R.id.tvName);
            holder.tvAddress = convertView.findViewById(R.id.tvAddress);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvName.setText(dataList.get(position).getWarehouseName());
        holder.tvAddress.setText(dataList.get(position).getAddress());
        return convertView;
    }

    private static class ViewHolder {
        private TextView tvName;
        private TextView tvAddress;
    }
}
