package com.dlt.express.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.dlt.express.R;
import com.dlt.express.api.ServerApi;
import com.dlt.express.base.AppActivity;
import com.dlt.express.base.AppBaseBean;
import com.dlt.express.base.AppCallBack;
import com.dlt.express.util.permission.GrantListener;
import com.dlt.express.util.permission.PermissionVerifyUtil;
import com.huawei.hms.hmsscankit.ScanUtil;
import com.huawei.hms.ml.scan.HmsScan;
import com.huawei.hms.ml.scan.HmsScanAnalyzerOptions;
import com.hzlh.sdk.util.YToast;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：登记卸货
 * 作者：Zhout
 * 日期：2022/11/26 18:08
 */
public class EnrollActivity extends AppActivity implements View.OnClickListener {
    private Context context = this;
    private final int REQUEST_CODE_SCAN_EXPRESS = 100;
    private EditText etCarNo;
    private EditText etExpressNo;
    private ImageView ivScanExpressNo;
    private LinearLayout layoutList;
    private List<String> expressOrderList = new ArrayList<>();
    private StorageOutListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll);
        initView();

    }

    private void initView() {
        initHeader("卸货登记");

        etCarNo = findViewById(R.id.etCarNo);
        etExpressNo = findViewById(R.id.etExpressNo);
        ivScanExpressNo = findViewById(R.id.ivScanExpressNo);

        ListView listView = findViewById(R.id.list_view);
        layoutList = findViewById(R.id.layout_list);
        ivScanExpressNo.setOnClickListener(this);

        adapter = new StorageOutListAdapter(context, expressOrderList);
        listView.setAdapter(adapter);
    }


    @Override
    public void onClick(View view) {
        if (TextUtils.isEmpty(etCarNo.getText().toString().trim())) {
            YToast.shortToast(context, "请先输入车牌号！");
            return;
        }
        if (view == ivScanExpressNo) {
            // 权限检测
            int[] requestCodes = new int[]{PermissionVerifyUtil.CAMERA, PermissionVerifyUtil.WRITE_EXTERNAL_STORAGE};
            permissionVerifyUtil.apply(requestCodes, new GrantListener() {
                @Override
                public void onAgree() {
                    startDefaultScanMode();
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


    //开启默认扫码模式
    private void startDefaultScanMode() {
        HmsScanAnalyzerOptions options = new HmsScanAnalyzerOptions
                .Creator()
                .setHmsScanTypes(HmsScan.ALL_SCAN_TYPE)
                .create();
        ScanUtil.startScan(this, REQUEST_CODE_SCAN_EXPRESS, options);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK || data == null) {
            return;
        }
        if (requestCode == REQUEST_CODE_SCAN_EXPRESS) {
            HmsScan obj = data.getParcelableExtra(ScanUtil.RESULT);
            if (obj != null) {
                etExpressNo.setText(obj.originalValue);
                commit();
            }
        }
    }

    private void commit() {
        new ServerApi().unload(context, etCarNo.getText().toString().trim(), etExpressNo.getText().toString().trim(),
                new AppCallBack<AppBaseBean>(context) {
                    @Override
                    public void onResultOk(AppBaseBean result) {
                        super.onResultOk(result);
                        YToast.shortToast(context, "卸货成功！");

                        layoutList.setVisibility(View.VISIBLE);
                        expressOrderList.add(etExpressNo.getText().toString().trim());
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onResultError(AppBaseBean result) {
                        super.onResultError(result);
                        YToast.shortToast(context, result.getMsg());
                    }
                });
    }

}

