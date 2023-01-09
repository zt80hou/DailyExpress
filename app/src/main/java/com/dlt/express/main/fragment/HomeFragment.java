package com.dlt.express.main.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dlt.express.Constants;
import com.dlt.express.R;
import com.dlt.express.base.AppFragment;
import com.dlt.express.main.CheckActivity;
import com.dlt.express.main.DispatchActivity;
import com.dlt.express.main.EnrollActivity;
import com.dlt.express.main.PutOffActivity;
import com.dlt.express.main.PutOnActivity;
import com.dlt.express.main.SendOkActivity;
import com.dlt.express.main.StorageInActivity;
import com.dlt.express.main.StorageOutActivity;
import com.dlt.express.util.JumpUtil;


/**
 * 描述：首页
 * 作者：Zhout
 * 日期：2022/11/19 11:45
 */
public class HomeFragment extends AppFragment implements View.OnClickListener {
    private Context context;
    private LinearLayout layoutIn;
    private LinearLayout layoutOn;
    private LinearLayout layoutCheck;
    private LinearLayout layoutOff;
    private LinearLayout layoutOut;
    private LinearLayout layoutEnroll;
    private LinearLayout layoutDispatch;
    private LinearLayout layoutSend;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(context, R.layout.fragment_home, null);
        layoutIn = view.findViewById(R.id.layout_in);
        layoutOn = view.findViewById(R.id.layout_on);
        layoutCheck = view.findViewById(R.id.layout_check);
        layoutOff = view.findViewById(R.id.layout_off);
        layoutOut = view.findViewById(R.id.layout_out);
        layoutEnroll = view.findViewById(R.id.layout_enroll);
        layoutDispatch = view.findViewById(R.id.layout_dispatch);
        layoutSend = view.findViewById(R.id.layout_send);


        layoutIn.setOnClickListener(this);
        layoutOn.setOnClickListener(this);
        layoutCheck.setOnClickListener(this);
        layoutOff.setOnClickListener(this);
        layoutOut.setOnClickListener(this);
        layoutEnroll.setOnClickListener(this);
        layoutDispatch.setOnClickListener(this);
        layoutSend.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (!Constants.isLogin()) {
            JumpUtil.jumpLogin(context);
        } else {
            if (view == layoutIn) {//入库
                startActivity(StorageInActivity.class);
            } else if (view == layoutOn) {//上架
                startActivity(PutOnActivity.class);
            } else if (view == layoutCheck) {//盘点
                startActivity(CheckActivity.class);
            } else if (view == layoutOff) {//下架
                startActivity(PutOffActivity.class);
            } else if (view == layoutOut) {//出库
                startActivity(StorageOutActivity.class);
            } else if (view == layoutEnroll) {//卸货登记
                startActivity(EnrollActivity.class);
            } else if (view == layoutDispatch) {// 开始派单
                startActivity(DispatchActivity.class);
            } else if (view == layoutSend) {//送达登记
                startActivity(SendOkActivity.class);
            }
        }


    }

}
