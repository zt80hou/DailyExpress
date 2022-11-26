package com.dlt.express.main;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;

import com.dlt.express.R;
import com.dlt.express.base.AppActivity;

/**
 * 描述：下架
 * 作者：Zhout
 * 日期：2020/8/9 21:43
 */
public class PutOffActivity extends AppActivity implements View.OnClickListener {
    private Context context = this;
    private EditText etOrderExpressNo;// 单号
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_off);
        initView();

    }

    private void initView() {
        initHeader("下架");

    }


    @Override
    public void onClick(View view) {

    }


    private void commit() {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler = null;
    }
}

