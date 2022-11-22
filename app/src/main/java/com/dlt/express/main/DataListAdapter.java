package com.dlt.express.main;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dlt.express.R;
import com.hzlh.sdk.util.YBaseAdapter;

import java.util.List;

/**
 * 描述：数据列表
 * 作者：Zhout
 * 日期：2020/10/11 14:59
 */
public class DataListAdapter extends YBaseAdapter<String> {
    public DataListAdapter(Context context, List<String> dataList) {
        super(context, dataList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_data_list, null);
            viewHolder.tvName = convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvName.setText(dataList.get(position));
        return convertView;
    }

    private class ViewHolder {
        private TextView tvName;
    }
}
