package com.dlt.express.main;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dlt.express.api.ExpressInfo;
import com.dlt.express.api.ExpressListBean;
import com.dlt.express.api.ServerApi;
import com.dlt.express.base.AppActivity;
import com.dlt.express.base.AppBaseBean;
import com.dlt.express.base.AppCallBack;
import com.dlt.express.sacn.SettingParams;
import com.dlt.express.util.InputMethodUtils;
import com.huayusoft.barcodeadmin.IScanCallBack;
import com.huayusoft.barcodeadmin.entity.IScanSetting;
import com.hzlh.sdk.util.YLog;
import com.hzlh.sdk.util.YTextWatcher;
import com.hzlh.sdk.util.YToast;
import com.dlt.express.R;
import com.raindi.scanner.RDManager;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 描述：下架
 * 作者：Zhout
 * 日期：2020/8/9 21:43
 */
public class PutOffActivity extends AppActivity implements View.OnClickListener {
    private Context context = this;
    private EditText etOrderExpressNo;// 单号
    private ImageView ivScanOrderNo;
    private EditText etGoodsNo;// 货号
    private ImageView ivScanGoodsShelvesNo;
    private ListView listView;
    private ArrayList<String> goodsOrderList = new ArrayList<>();
    private boolean hasData = false;
    private DataListAdapter adapter;
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_off);
        initView();
        boolean bindService = RDManager.getInstance().bindScannerService(this, iCallback);
        YLog.e("bindService = " + bindService);
        RDManager.getInstance().setting(SettingParams.getIScanParam(new IScanSetting()));
    }

    private void initView() {
        initHeader("下架");
        etOrderExpressNo = (EditText) findViewById(R.id.etOrderExpressNo);
        ivScanGoodsShelvesNo = (ImageView) findViewById(R.id.ivScanGoodsShelvesNo);
        etGoodsNo = (EditText) findViewById(R.id.etGoodsNo);
        ivScanOrderNo = (ImageView) findViewById(R.id.ivScanOrderNo);
        ivScanGoodsShelvesNo.setOnClickListener(this);
        ivScanOrderNo.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.listView);
        adapter = new DataListAdapter(this, goodsOrderList);
        listView.setAdapter(adapter);

        etOrderExpressNo.addTextChangedListener(new YTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (TextUtils.isEmpty(s.toString().trim())) {
                    goodsOrderList.clear();
                    adapter.notifyDataSetChanged();
                }
                if (s.toString().trim().endsWith("#")) {
                    InputMethodUtils.hide(context);
                    queryWaitPutOffPackages(s.toString().trim().replace("#", ""));
                }
            }
        });
        etOrderExpressNo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (!TextUtils.isEmpty(etOrderExpressNo.getText().toString().trim().replace("#", ""))) {
                        InputMethodUtils.hide(context);
                        queryWaitPutOffPackages(etOrderExpressNo.getText().toString().trim().replace("#", ""));
                    } else {
                        YToast.shortToast(context, "请输入单号！");
                    }
                }
                return false;
            }
        });
        etGoodsNo.setEnabled(false);
        etGoodsNo.addTextChangedListener(new YTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (s.toString().trim().endsWith("#")) {
                    InputMethodUtils.hide(context);
                    if (goodsOrderList.contains(etGoodsNo.getText().toString().trim().replace("#", ""))) {
                        commit();
                    }
                }
            }
        });
        etGoodsNo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (!TextUtils.isEmpty(etGoodsNo.getText().toString().replace("#", ""))) {
                        if (goodsOrderList.contains(etGoodsNo.getText().toString().trim().replace("#", ""))) {
                            commit();
                        }
                    } else {
                        YToast.shortToast(context, "请输入货号！");
                    }
                    etGoodsNo.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            etGoodsNo.setFocusable(true);
                            etGoodsNo.setFocusableInTouchMode(true);
                            etGoodsNo.requestFocus();
                        }
                    }, 10);
                }
                return false;
            }
        });
    }

    private void queryWaitPutOffPackages(String orderExpressNo) {
        new ServerApi().queryWaitPutOffPackages(context, orderExpressNo, new AppCallBack<ExpressListBean>(context) {
            @Override
            public void onResultOk(ExpressListBean result) {
                super.onResultOk(result);
                if (result.getData() != null) {
                    hasData = true;
                    etGoodsNo.setEnabled(true);
                    etOrderExpressNo.setText(orderExpressNo);
                    etOrderExpressNo.setSelection(orderExpressNo.length());
                    etGoodsNo.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            etGoodsNo.setFocusable(true);
                            etGoodsNo.setFocusableInTouchMode(true);
                            etGoodsNo.requestFocus();
                        }
                    }, 10);
                    goodsOrderList.clear();
                    for (ExpressInfo info : result.getData()) {
                        goodsOrderList.add(info.getFromExpressNo());
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onResultError(ExpressListBean response) {
                super.onResultError(response);
                etOrderExpressNo.setText(orderExpressNo);
                etOrderExpressNo.setSelection(orderExpressNo.length());
                goodsOrderList.clear();
                adapter.notifyDataSetChanged();
                YToast.shortToast(context, "订单号不存在！");
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivScanGoodsShelvesNo:
                //打开扫描界面扫描条形码或二维码
                RDManager.getInstance().doScan();
                break;
            case R.id.ivScanOrderNo:
                if (TextUtils.isEmpty(etOrderExpressNo.getText().toString().trim()) || !hasData) {
                    YToast.shortToast(context, "请先扫描单号!");
                    return;
                }
                //打开扫描界面扫描条形码或二维码
                RDManager.getInstance().doScan();
                break;
        }
    }


    private void commit() {
        if (TextUtils.isEmpty(etOrderExpressNo.getText().toString().trim())) {
            YToast.shortToast(context, "单号不能为空");
            return;
        }
        if (TextUtils.isEmpty(etGoodsNo.getText().toString().trim())) {
            YToast.shortToast(context, "货号不能为空");
            return;
        }
        new ServerApi().putOff(context, etOrderExpressNo.getText().toString().trim(), etGoodsNo.getText().toString().trim().replace("#", ""), new AppCallBack<AppBaseBean>(context) {
            @Override
            public void onResultOk(AppBaseBean result) {
                super.onResultOk(result);
                YToast.shortToast(context, result.getMsg());

                // 从列表移除
                Iterator<String> it = goodsOrderList.iterator();
                while (it.hasNext()) {
                    if (it.next().equals(etGoodsNo.getText().toString().trim().replace("#", ""))) {
                        it.remove();
                    }
                }
                adapter.notifyDataSetChanged();
                etGoodsNo.setText("");

                if (goodsOrderList.size() == 0) {
                    // 全部下架完了，清除这一批，从头来
                    etOrderExpressNo.setText("");
                    etOrderExpressNo.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            etOrderExpressNo.setFocusable(true);
                            etOrderExpressNo.setFocusableInTouchMode(true);
                            etOrderExpressNo.requestFocus();
                        }
                    }, 10);
                }

            }

            @Override
            public void onResultError(AppBaseBean result) {
                super.onResultError(result);
                YToast.shortToast(context, result.getMsg());
            }
        });
    }


    IScanCallBack iCallback = new IScanCallBack.Stub() {
        @Override
        public void onSuccess(final String bean) throws RemoteException {
            if (!TextUtils.isEmpty(bean)) {
                YLog.i("scan result: " + bean);
                handler.post(new Runnable() {  // iCallback 是子线程
                    @Override
                    public void run() {
                        // etOrderExpressNo空的说明扫码扫的是单号，去查询
                        if (TextUtils.isEmpty(etOrderExpressNo.getText().toString()) || !hasData) {
                            etOrderExpressNo.setText(bean);
                            etOrderExpressNo.setSelection(bean.length());
                            queryWaitPutOffPackages(bean);
                        } else {
                            etGoodsNo.setText(bean);
                            etGoodsNo.setSelection(bean.length());
                            commit();
                        }
                    }
                });
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 继续扫码,延时一会儿，防止一直扫那1个码
                        RDManager.getInstance().doScan();
                    }
                }, 500);
            } else {
                YLog.e("callback bean is null");
            }
        }

        @Override
        public void onFiled(int errorCode) throws RemoteException {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RDManager.getInstance().unbindScannerService(this);
        handler = null;
    }
}

