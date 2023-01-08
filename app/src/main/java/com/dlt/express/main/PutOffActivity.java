package com.dlt.express.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dlt.express.R;
import com.dlt.express.api.ExpressInfo;
import com.dlt.express.api.ExpressListBean;
import com.dlt.express.api.ServerApi;
import com.dlt.express.base.AppActivity;
import com.dlt.express.base.AppBaseBean;
import com.dlt.express.base.AppCallBack;
import com.dlt.express.util.permission.GrantListener;
import com.dlt.express.util.permission.PermissionVerifyUtil;
import com.dlt.express.view.AtMostListView;
import com.huawei.hms.hmsscankit.ScanUtil;
import com.huawei.hms.ml.scan.HmsScan;
import com.huawei.hms.ml.scan.HmsScanAnalyzerOptions;
import com.hzlh.sdk.util.YToast;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：下架
 * 作者：Zhout
 * 日期：2023/1/8 16:43
 */
public class PutOffActivity extends AppActivity implements View.OnClickListener {
    private Context context = this;
    private EditText etOrderNo;
    private ImageView ivScanOrderNo;
    private EditText etExpressNo;
    private ImageView ivScanExpressNo;
    private TextView tvCustomer;
    private AtMostListView listView;
    private TextView tvWaitScan;
    private TextView tvScanned;
    private TextView tvUnScan;
    private TextView tvEmpty;
    private PutOffListAdapter adapter;
    private List<ExpressInfo> expressInfoList = new ArrayList<>();

    private final int REQUEST_CODE_SCAN_ORDER = 100;
    private final int REQUEST_CODE_SCAN_EXPRESS = 101;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_off);
        initView();
    }

    private void initView() {
        initHeader("拣货下架");
        etOrderNo = findViewById(R.id.etOrderNo);
        ivScanOrderNo = findViewById(R.id.ivScanOrderNo);
        etExpressNo = findViewById(R.id.etExpressNo);
        ivScanExpressNo = findViewById(R.id.ivScanExpressNo);
        tvCustomer = findViewById(R.id.tv_customer);
        tvWaitScan = findViewById(R.id.tv_wait_scan);
        tvScanned = findViewById(R.id.tv_scanned);
        tvUnScan = findViewById(R.id.tv_un_scan);
        listView = findViewById(R.id.list_view);
        tvEmpty = findViewById(R.id.tvEmpty);

        ivScanOrderNo.setOnClickListener(this);
        ivScanExpressNo.setOnClickListener(this);
        adapter = new PutOffListAdapter(context, expressInfoList);
        listView.setAdapter(adapter);
        listView.setEmptyView(tvEmpty);
    }


    /**
     * 扫描货架号
     */
    private void startScanShelve() {
        HmsScanAnalyzerOptions options = new HmsScanAnalyzerOptions
                .Creator()
                .setHmsScanTypes(HmsScan.ALL_SCAN_TYPE)
                .create();
        ScanUtil.startScan(this, REQUEST_CODE_SCAN_ORDER, options);
    }

    /**
     * 扫描运单号
     */
    private void startScanExpress() {
        HmsScanAnalyzerOptions options = new HmsScanAnalyzerOptions
                .Creator()
                .setHmsScanTypes(HmsScan.ALL_SCAN_TYPE)
                .create();
        ScanUtil.startScan(this, REQUEST_CODE_SCAN_EXPRESS, options);
    }


    @Override
    public void onClick(View view) {
        if (view == ivScanOrderNo) {
            // 权限检测
            int[] requestCodes = new int[]{PermissionVerifyUtil.CAMERA, PermissionVerifyUtil.WRITE_EXTERNAL_STORAGE};
            permissionVerifyUtil.apply(requestCodes, new GrantListener() {
                @Override
                public void onAgree() {
                    startScanShelve();
                }

                @Override
                public void onDeny() {
                    YToast.shortToast(context, "需要开启该相机和存储权限才能正常使用扫码功能！");
                }

                @Override
                public void onDenyNotAskAgain() {

                }
            });
        } else if (view == ivScanExpressNo) {
            if (TextUtils.isEmpty(etOrderNo.getText().toString().trim())) {
                YToast.shortToast(context, "请先扫订单号！");
                return;
            }
            // 权限检测
            int[] requestCodes = new int[]{PermissionVerifyUtil.CAMERA, PermissionVerifyUtil.WRITE_EXTERNAL_STORAGE};
            permissionVerifyUtil.apply(requestCodes, new GrantListener() {
                @Override
                public void onAgree() {
                    startScanExpress();
                }

                @Override
                public void onDeny() {
                    YToast.shortToast(context, "需要开启该相机和存储权限才能正常使用扫码功能！");
                }

                @Override
                public void onDenyNotAskAgain() {

                }
            });
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK || data == null) {
            return;
        }
        if (requestCode == REQUEST_CODE_SCAN_ORDER) {
            HmsScan obj = data.getParcelableExtra(ScanUtil.RESULT);
            if (obj != null) {
                etOrderNo.setText(obj.originalValue);
                queryWaitingPoutOff();
            }
        } else if (requestCode == REQUEST_CODE_SCAN_EXPRESS) {
            HmsScan obj = data.getParcelableExtra(ScanUtil.RESULT);
            if (obj != null) {
                etExpressNo.setText(obj.originalValue);
                handleExpressInfo(obj.originalValue);
            }
        }
    }

    private void handleExpressInfo(String expressNo) {
        int index = -1;
        for (int i = 0; i < expressInfoList.size(); i++) {
            if (expressNo.equals(expressInfoList.get(i).getExpressNo())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            tvCustomer.setText(expressInfoList.get(index).getCustomerName() + expressInfoList.get(index).getCustomerNo());
            doPutOff();
        } else {
            YToast.shortToast(context, "该运单号不在列表中，请重试！");
            tvCustomer.setText("");
        }
    }

    @SuppressLint("SetTextI18n")
    private void splitScanData() {
        List<ExpressInfo> unScanList = new ArrayList<>();
        List<ExpressInfo> scannedList = new ArrayList<>();
        for (int i = 0; i < expressInfoList.size(); i++) {
            if ("STORAGED".equals(expressInfoList.get(i).getPackageStatus())) {
                unScanList.add(expressInfoList.get(i));
            } else if ("PACKED".equals(expressInfoList.get(i).getPackageStatus())) {
                scannedList.add(expressInfoList.get(i));
            }
        }
        tvWaitScan.setText("待扫描：" + expressInfoList.size());
        tvUnScan.setText("未扫描：" + unScanList.size());
        tvScanned.setText("已扫描：" + scannedList.size());
    }

    /**
     * 下架后，更新数据
     */
    private void updateData(String expressNo) {
        for (int i = 0; i < expressInfoList.size(); i++) {
            if (expressNo.equals(expressInfoList.get(i).getExpressNo())) {
                expressInfoList.get(i).setPackageStatus("PACKED");
            }
        }
        adapter.notifyDataSetChanged();

        splitScanData();
    }


    /**
     * 查询待下架列表
     */
    private void queryWaitingPoutOff() {
        new ServerApi().queryWaitPutOffPackages(context, etOrderNo.getText().toString().trim(),
                new AppCallBack<ExpressListBean>(context) {
                    @Override
                    public void onResultOk(ExpressListBean result) {
                        super.onResultOk(result);
                        expressInfoList.clear();
                        if (result.getData() != null) {
                            expressInfoList.addAll(result.getData());
                            splitScanData();
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onResultError(ExpressListBean result) {
                        super.onResultError(result);
                        YToast.shortToast(context, result.getMsg());
                    }
                });
    }

    /**
     * 执行下架操作
     */
    private void doPutOff() {
        new ServerApi().putOff(context, etExpressNo.getText().toString().trim(),
                new AppCallBack<AppBaseBean>(context) {
                    @Override
                    public void onResultOk(AppBaseBean result) {
                        super.onResultOk(result);
                        YToast.shortToast(context, "下架成功！");
                        updateData(etExpressNo.getText().toString().trim());
                        etExpressNo.setText("");
                    }

                    @Override
                    public void onNull() {
                        super.onNull();
                        YToast.shortToast(context, "下架失败！");
                    }
                });
    }

}

