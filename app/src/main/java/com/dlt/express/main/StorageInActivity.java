package com.dlt.express.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dlt.express.R;
import com.dlt.express.base.AppActivity;
import com.dlt.express.util.permission.GrantListener;
import com.dlt.express.util.permission.PermissionVerifyUtil;
import com.huawei.hms.hmsscankit.ScanUtil;
import com.huawei.hms.ml.scan.HmsScan;
import com.huawei.hms.ml.scan.HmsScanAnalyzerOptions;
import com.hzlh.sdk.util.YToast;

/**
 * 描述：入库
 * 作者：Zhout
 * 日期：2022/11/21 14:43
 */
public class StorageInActivity extends AppActivity implements View.OnClickListener {
    private Context context = this;
    private LinearLayout layoutScan;
    private EditText etCode;
    private final int REQUEST_CODE_SCAN = 100;
    private final int REQUEST_CODE_PERMISSION = 101;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_in);
        initView();

    }

    private void initView() {
        initHeader("扫码入库");

        layoutScan = findViewById(R.id.layoutScan);
        etCode = findViewById(R.id.et_code);
        layoutScan.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        if (view == layoutScan) {
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
        ScanUtil.startScan(this, REQUEST_CODE_SCAN, options);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK || data == null) {
            return;
        }
        if (requestCode == REQUEST_CODE_SCAN) {
            HmsScan obj = data.getParcelableExtra(ScanUtil.RESULT);
            if (obj != null) {
                etCode.setText(obj.originalValue);
            }
        }
    }

    private void commit() {

    }

}

