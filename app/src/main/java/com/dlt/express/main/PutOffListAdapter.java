package com.dlt.express.main;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dlt.express.R;
import com.dlt.express.api.ExpressInfo;
import com.hzlh.sdk.util.YBaseAdapter;

import java.util.List;

/**
 * 描述：上架货物列表适配器
 * 日期: 2023/1/8 13:37
 *
 * @author Zhout
 */
public class PutOffListAdapter extends YBaseAdapter<ExpressInfo> {

    public PutOffListAdapter(Context context, List<ExpressInfo> dataList) {
        super(context, dataList);
        this.dataList = dataList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.put_on_list_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvNo.setText(dataList.get(position).getExpressNo());
        holder.tvName.setText(dataList.get(position).getPackageName());
        holder.tvTime.setText(dataList.get(position).getUpdateTime());

        if ("STORAGED".equals(dataList.get(position).getPackageStatus())) {
            holder.ivState.setImageResource(R.drawable.icon_ng);
        } else if ("PACKED".equals(dataList.get(position).getPackageStatus())) {
            holder.ivState.setImageResource(R.drawable.icon_ok);
        }

        return convertView;
    }

    private static class ViewHolder {
        private TextView tvNo;
        private ImageView ivState;
        private TextView tvName;
        private TextView tvTime;

        ViewHolder(View view) {
            tvNo = view.findViewById(R.id.tv_no);
            ivState = view.findViewById(R.id.iv_state);
            tvName = view.findViewById(R.id.tv_name);
            tvTime = view.findViewById(R.id.tv_time);
        }

    }
}
