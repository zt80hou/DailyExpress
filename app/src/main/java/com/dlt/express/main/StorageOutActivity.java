package com.dlt.express.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.dlt.express.R;
import com.dlt.express.api.ServerApi;
import com.dlt.express.base.AppActivity;
import com.dlt.express.base.AppBaseBean;
import com.dlt.express.base.AppCallBack;
import com.dlt.express.util.DateUtil;
import com.dlt.express.util.permission.GrantListener;
import com.dlt.express.util.permission.PermissionVerifyUtil;
import com.huawei.hms.hmsscankit.ScanUtil;
import com.huawei.hms.ml.scan.HmsScan;
import com.huawei.hms.ml.scan.HmsScanAnalyzerOptions;
import com.hzlh.sdk.util.YLog;
import com.hzlh.sdk.util.YToast;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：出库
 * 作者：Zhout
 * 日期：2022/11/21 14:43
 */
public class StorageOutActivity extends AppActivity implements View.OnClickListener {
    private Context context = this;
    private final int REQUEST_CODE_SCAN_EXPRESS = 100;
    private EditText etCarNo;
    private EditText etExpressNo;
    private ImageView ivScanExpressNo;
    private LinearLayout layoutList;
    private String batchNo = ""; // 班次信息
    private List<String> expressOrderList = new ArrayList<>();
    private StorageOutListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_out);
        initView();

    }

    private void initView() {
        initHeader("装车出库");

        etCarNo = findViewById(R.id.etCarNo);
        etExpressNo = findViewById(R.id.etExpressNo);
        ivScanExpressNo = findViewById(R.id.ivScanExpressNo);
        CheckBox checkbox1 = findViewById(R.id.checkbox_1);
        CheckBox checkbox2 = findViewById(R.id.checkbox_2);
        CheckBox checkbox3 = findViewById(R.id.checkbox_3);
        ListView listView = findViewById(R.id.list_view);
        layoutList = findViewById(R.id.layout_list);
        ivScanExpressNo.setOnClickListener(this);

        adapter = new StorageOutListAdapter(context, expressOrderList);
        listView.setAdapter(adapter);

        int hour = Integer.valueOf(DateUtil.getCurrentHour());
        YLog.e("小时：" + hour);

        if (hour >= 5 && hour < 10) {
            checkbox1.setChecked(true);
            batchNo = "T00";
        } else if (hour >= 10 && hour < 16) {
            checkbox2.setChecked(true);
            batchNo = "T12";
        } else {
            checkbox3.setChecked(true);
            batchNo = "T24";
        }

        checkbox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    batchNo = "T00";
                }
            }
        });
        checkbox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    batchNo = "T12";
                }
            }
        });
        checkbox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    batchNo = "T24";
                }
            }
        });

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
        new ServerApi().storageOut(context, etCarNo.getText().toString().trim(), batchNo, etExpressNo.getText().toString().trim(),
                new AppCallBack<AppBaseBean>(context) {
                    @Override
                    public void onResultOk(AppBaseBean result) {
                        super.onResultOk(result);
                        YToast.shortToast(context, "出库成功！");

                        layoutList.setVisibility(View.VISIBLE);
                        expressOrderList.add(etExpressNo.getText().toString().trim());
                        adapter.notifyDataSetChanged();
                        etExpressNo.setText("");
                    }

                    @Override
                    public void onResultError(AppBaseBean result) {
                        super.onResultError(result);
                        YToast.shortToast(context, result.getMsg());
                    }
                });
    }

}

