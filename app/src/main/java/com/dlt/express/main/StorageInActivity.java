package com.dlt.express.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.dlt.express.R;
import com.dlt.express.api.ReceivedRequestBean;
import com.dlt.express.api.ServerApi;
import com.dlt.express.api.WareHouseListBean;
import com.dlt.express.base.AppActivity;
import com.dlt.express.base.AppBaseBean;
import com.dlt.express.base.AppCallBack;
import com.dlt.express.util.Util;
import com.dlt.express.util.permission.GrantListener;
import com.dlt.express.util.permission.PermissionVerifyUtil;
import com.huawei.hms.hmsscankit.ScanUtil;
import com.huawei.hms.ml.scan.HmsScan;
import com.huawei.hms.ml.scan.HmsScanAnalyzerOptions;
import com.hzlh.sdk.util.YTextWatcher;
import com.hzlh.sdk.util.YToast;

import org.greenrobot.eventbus.Subscribe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述：入库
 * 作者：Zhout
 * 日期：2022/11/21 14:43
 */
public class StorageInActivity extends AppActivity implements View.OnClickListener {
    private Context context = this;
    private LinearLayout layoutScan;
    private Button btnCommit;
    private EditText etCode;
    private Spinner spinnerGoodsType;
    private Spinner spinnerWareHouse;
    private EditText etLength;
    private EditText etWidth;
    private EditText etHeight;
    private EditText etVolume;
    private EditText etWeight;
    private EditText etGoodsNum;
    private EditText etGoodsName;
    private TextView tvQueryCustomer;
    private EditText etCustomerNo;
    private EditText etCustomerName;
    private TextView tvQueryAddress;
    private EditText etReceivedName;
    private EditText etTelephone;
    private EditText etAddress;
    private EditText etGoodsShelvesNo;
    private EditText etRemark;

    private final int REQUEST_CODE_SCAN = 100;
    private String customerId;
    private String goodsTypeValue;
    private String destinationId;// 收货地址id，非必填，选择框;
    private String wareHouseId;// 仓库id;
    private List<WareHouseListBean.DataBean.ContentBean> wareHouseList = new ArrayList<>();
    private WareHouseAdapter wareHouseAdapter;
    private float length, width, height, volume;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage_in);
        initView();
        queryWareHouse();
    }

    private void initView() {
        initHeader("收单入库");
        layoutScan = findViewById(R.id.layoutScan);
        btnCommit = findViewById(R.id.btnCommit);

        etCode = findViewById(R.id.et_code);
        spinnerWareHouse = findViewById(R.id.spinnerWareHouse);
        spinnerGoodsType = findViewById(R.id.spinnerGoodsType);
        etLength = findViewById(R.id.etLength);
        etWidth = findViewById(R.id.etWidth);
        etHeight = findViewById(R.id.etHeight);
        etVolume = findViewById(R.id.etVolume);
        etWeight = findViewById(R.id.etWeight);
        etGoodsNum = findViewById(R.id.etGoodsNum);
        etGoodsName = findViewById(R.id.etGoodsName);
        tvQueryCustomer = findViewById(R.id.tv_query_customer);
        etCustomerNo = findViewById(R.id.etCustomerNo);
        etCustomerName = findViewById(R.id.etCustomerName);
        tvQueryAddress = findViewById(R.id.tv_query_address);
        etReceivedName = findViewById(R.id.etReceivedName);
        etTelephone = findViewById(R.id.etTelephone);
        etAddress = findViewById(R.id.etAddress);
        etGoodsShelvesNo = findViewById(R.id.etGoodsShelvesNo);
        etRemark = findViewById(R.id.etRemark);


        layoutScan.setOnClickListener(this);
        tvQueryCustomer.setOnClickListener(this);
        tvQueryAddress.setOnClickListener(this);
        btnCommit.setOnClickListener(this);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.goodsTypeNames, R.layout.spinner_goods_type_item);
        spinnerGoodsType.setAdapter(adapter);
        spinnerGoodsType.setBackgroundColor(0x0);// 去掉小三角
        spinnerGoodsType.setDropDownVerticalOffset(Util.dip2px(context, 10));
        spinnerGoodsType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                String[] goodsTypeValues = getResources().getStringArray(R.array.goodsTypeValues);
                goodsTypeValue = goodsTypeValues[pos];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        wareHouseAdapter = new WareHouseAdapter(context, wareHouseList);
        spinnerWareHouse.setAdapter(wareHouseAdapter);
        spinnerWareHouse.setBackgroundColor(0x0);// 去掉小三角
        spinnerWareHouse.setDropDownVerticalOffset(Util.dip2px(context, 10));
        spinnerWareHouse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                wareHouseId = wareHouseList.get(pos).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        handleLWH();
    }

    /**
     * 查询仓库
     */
    private void queryWareHouse() {
        new ServerApi().queryWarehouse(context, 0, new AppCallBack<WareHouseListBean>(context) {
            @Override
            public void onResultOk(WareHouseListBean result) {
                super.onResultOk(result);
                if (result.getData() != null && result.getData().getContent() != null) {
                    wareHouseList.addAll(result.getData().getContent());
                }
                wareHouseAdapter.notifyDataSetChanged();
            }
        });
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
        } else if (view == tvQueryCustomer) {
            startActivity(CustomerListActivity.class);
        } else if (view == tvQueryAddress) {
            startActivity(new Intent(context, AddressListActivity.class).putExtra("customerId", customerId));
        } else if (view == btnCommit) {
            commit();
        }
    }

    /**
     * 处理长宽高
     */
    private void handleLWH() {
        etLength.addTextChangedListener(new YTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (!TextUtils.isEmpty(s.toString().trim())) {
                    length = Float.parseFloat(s.toString().trim());
                } else {
                    length = 0;
                }
                volume = length * width * height;
                keep3Point(volume);
                etVolume.setText(String.valueOf(volume));
            }
        });
        etWidth.addTextChangedListener(new YTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (!TextUtils.isEmpty(s.toString().trim())) {
                    width = Float.parseFloat(s.toString().trim());
                } else {
                    width = 0;
                }
                volume = length * width * height;
                keep3Point(volume);
                etVolume.setText(String.valueOf(volume));
            }
        });
        etHeight.addTextChangedListener(new YTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (!TextUtils.isEmpty(s.toString().trim())) {
                    height = Float.parseFloat(s.toString().trim());
                } else {
                    height = 0;
                }
                volume = length * width * height;
                keep3Point(volume);
                etVolume.setText(String.valueOf(volume));
            }
        });
        etVolume.addTextChangedListener(new YTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                volume = Float.parseFloat(s.toString().trim());
            }
        });
    }

    /**
     * 保留3位小数
     * @param f
     */
    private void keep3Point(float f) {
        BigDecimal b = new BigDecimal(f);
        //保留3位小数，四舍五入
        volume = b.setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    @Subscribe
    public void onEvent(CustomerEvent event) {
        customerId = event.getCustomerId();
        etCustomerNo.setText(event.getCustomerNo());
        etCustomerName.setText(event.getCustomerName());
    }

    @Subscribe
    public void onEvent(AddressEvent event) {
        destinationId = event.getId();
        etReceivedName.setText(event.getReceiverName());
        etAddress.setText(event.getAddress());
        etTelephone.setText(event.getTelphone());
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

    /**
     * 提交
     */
    private void commit() {
        ReceivedRequestBean bean = new ReceivedRequestBean();
        bean.setExpressNo("");
        bean.setWarehouseId(wareHouseId);
        bean.setGoodsShelvesNo(etGoodsShelvesNo.getText().toString().trim());
        if (TextUtils.isEmpty(etVolume.getText().toString().trim())) {
            bean.setLength(Float.valueOf(etLength.getText().toString().trim()));
            bean.setWidth(Float.valueOf(etWidth.getText().toString().trim()));
            bean.setHeight(Float.valueOf(etHeight.getText().toString().trim()));
        } else {
            bean.setVolume(Float.valueOf(etVolume.getText().toString().trim()));
        }
        bean.setWeight(Float.valueOf(etWeight.getText().toString().trim()));
        bean.setCustomerId(customerId);
        bean.setCustomerNo(etCustomerNo.getText().toString().trim());
        bean.setCustomerName(etCustomerName.getText().toString().trim());
        bean.setPackageName(etGoodsName.getText().toString().trim());
        bean.setPackageType(goodsTypeValue);
        bean.setQuantity(etGoodsNum.getText().toString().trim());
        bean.setDestinationId(destinationId);
        bean.setReceiverName(etReceivedName.getText().toString().trim());
        bean.setTelephone(etTelephone.getText().toString().trim());
        bean.setAddress(etAddress.getText().toString().trim());
        bean.setRemark(etRemark.getText().toString().trim());

        new ServerApi().received(context, bean, new AppCallBack<AppBaseBean>(context) {
            @Override
            public void onResultOk(AppBaseBean result) {
                super.onResultOk(result);

            }
        });
    }

}

