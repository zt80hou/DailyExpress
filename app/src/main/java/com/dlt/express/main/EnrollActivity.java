package com.dlt.express.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dlt.express.R;
import com.dlt.express.base.AppActivity;
import com.dlt.express.util.permission.GrantListener;
import com.dlt.express.util.permission.PermissionVerifyUtil;
import com.huawei.hms.hmsscankit.ScanUtil;
import com.huawei.hms.ml.scan.HmsScan;
import com.huawei.hms.ml.scan.HmsScanAnalyzerOptions;
import com.hzlh.sdk.util.YToast;

/**
 * 描述：登记卸货
 * 作者：Zhout
 * 日期：2022/11/26 18:08
 */
public class EnrollActivity extends AppActivity implements View.OnClickListener {
    private Context context = this;
    private LinearLayout layoutScan;
    private TextView tvLeft;
    private TextView tvRight;
    private Button btnCommit;
    private TextView tvCode;
    private final int REQUEST_CODE_SCAN = 100;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll);
        initView();

    }

    private void initView() {
        initHeader("卸货登记");
        layoutScan = findViewById(R.id.layoutScan);
        tvLeft = findViewById(R.id.tvLeft);
        tvRight = findViewById(R.id.tvRight);
        btnCommit = findViewById(R.id.btnCommit);
        tvCode = findViewById(R.id.tvCode);


        layoutScan.setOnClickListener(this);
        tvLeft.setOnClickListener(this);
        tvRight.setOnClickListener(this);
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

        } else if (view == tvLeft) {
            tvCode.setText("车牌号：");
            tvLeft.setBackgroundResource(R.drawable.shape_corner8_left_green_solid_dark_stroke);
            tvLeft.setTextColor(context.getResources().getColor(R.color.white));
            tvRight.setBackgroundResource(R.drawable.shape_corner8_right_gray_solid_drak_stroke);
            tvRight.setTextColor(context.getResources().getColor(R.color.font_brown));
        } else if (view == tvRight) {
            tvCode.setText("运单号：");
            tvLeft.setTextColor(context.getResources().getColor(R.color.font_brown));
            tvLeft.setBackgroundResource(R.drawable.shape_corner8_left_gray_solid_dark_stroke);
            tvRight.setBackgroundResource(R.drawable.shape_corner8_right_green_solid_drak_stroke);
            tvRight.setTextColor(context.getResources().getColor(R.color.white));
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
                tvCode.setText(obj.toString());
            }
        }
    }

    private void commit() {

    }

}

