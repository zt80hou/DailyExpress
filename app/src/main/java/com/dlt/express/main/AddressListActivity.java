package com.dlt.express.main;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.dlt.express.R;
import com.dlt.express.api.AddressListBean;
import com.dlt.express.api.ServerApi;
import com.dlt.express.base.AppActivity;
import com.dlt.express.base.AppCallBack;
import com.dlt.express.view.refresh.RefreshLayout;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：地址列表
 * 作者：Zhout
 * 日期：2022/12/4 11:08
 */
public class AddressListActivity extends AppActivity {
    private Context context = this;
    private ListView listView;
    private RefreshLayout refreshLayout;
    private int pageIndex = 0;
    private List<AddressListBean.DataBean.ContentBean> dataList = new ArrayList<>();
    private AddressListAdapter adapter;
    private String customerId;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);
        initView();

        customerId = getIntent().getStringExtra("customerId");
        initData();
    }

    private void initData() {
        new ServerApi().queryAddress(context, pageIndex, customerId, new AppCallBack<AddressListBean>(context) {
            @Override
            public void onResultOk(AddressListBean result) {
                super.onResultOk(result);
                if (result.getData() != null && result.getData().getContent() != null) {
                    dataList.addAll(result.getData().getContent());
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void initView() {
        initHeader("收货人列表");

        refreshLayout = findViewById(R.id.refresh_layout);
        listView = findViewById(R.id.listView);
        TextView tvEmpty = findViewById(R.id.tvEmpty);
        listView.setEmptyView(tvEmpty);
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                pageIndex++;
                initData();
            }
        });

        adapter = new AddressListAdapter(context, dataList);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                AddressEvent event = new AddressEvent();
                event.setId(dataList.get(position).getId());
                event.setReceiverName(dataList.get(position).getReceiverName());
                event.setTelphone(dataList.get(position).getTelphone());
                event.setAddress(dataList.get(position).getAddress());
                EventBus.getDefault().post(event);
                finish();
            }
        });
    }


}

