package com.dlt.express.main;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.dlt.express.R;
import com.dlt.express.api.CustomerListBean;
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
 * 描述：客户列表
 * 作者：Zhout
 * 日期：2022/12/4 11:08
 */
public class CustomerListActivity extends AppActivity {
    private Context context = this;
    private ListView listView;
    private RefreshLayout refreshLayout;
    private int pageIndex = 0;
    private List<CustomerListBean.DataBean.ContentBean> dataList = new ArrayList<>();
    private CustomerListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);
        initView();

        initData();
    }

    private void initData() {
        new ServerApi().queryCustomer(context, pageIndex, new AppCallBack<CustomerListBean>(context) {
            @Override
            public void onResultOk(CustomerListBean result) {
                super.onResultOk(result);

                if (result.getData() != null && result.getData().getContent() != null) {
                    dataList.addAll(result.getData().getContent());
                }
                adapter.notifyDataSetChanged();
                refreshLayout.finishLoadmore();
            }

            @Override
            public void onNull() {
                super.onNull();
                refreshLayout.finishLoadmore();
            }
        });
    }

    private void initView() {
        initHeader("客户列表");

        refreshLayout = findViewById(R.id.refresh_layout);
        listView = findViewById(R.id.listView);
        TextView tvEmpty = findViewById(R.id.tvEmpty);
        listView.setEmptyView(tvEmpty);
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setEnableOverScroll(false);
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                pageIndex++;
                initData();
            }
        });

        adapter = new CustomerListAdapter(context, dataList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                CustomerEvent event = new CustomerEvent();
                event.setCustomerId(dataList.get(position).getId());
                event.setCustomerName(dataList.get(position).getCustomerName());
                event.setCustomerNo(dataList.get(position).getCustomerNo());
                EventBus.getDefault().post(event);
                finish();
            }
        });
    }


}

