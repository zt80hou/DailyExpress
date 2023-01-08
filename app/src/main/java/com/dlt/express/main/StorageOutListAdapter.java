package com.dlt.express.main;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dlt.express.R;
import com.hzlh.sdk.util.YBaseAdapter;

import java.util.List;

/**
 * 描述：出库订单列表适配器
 * 日期: 2023/1/8 13:37
 *
 * @author Zhout
 */
public class StorageOutListAdapter extends YBaseAdapter<String> {

    public StorageOutListAdapter(Context context, List<String> dataList) {
        super(context, dataList);
        this.dataList = dataList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.storage_out_list_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvNo.setText(dataList.get(position));

        return convertView;
    }

    private static class ViewHolder {
        private TextView tvNo;

        ViewHolder(View view) {
            tvNo = view.findViewById(R.id.tv_no);
        }

    }
}
