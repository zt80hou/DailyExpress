package com.dlt.express.main.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.dlt.express.Constants;
import com.dlt.express.R;
import com.dlt.express.base.AppFragment;
import com.dlt.express.main.UserInfoActivity;
import com.dlt.express.module.user.LoginActivity;
import com.dlt.express.module.user.LoginEvent;
import com.dlt.express.util.JumpUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * 描述：我的
 * 作者：Zhout
 * 日期：2020/8/9 10:45
 */
public class MineFragment extends AppFragment implements View.OnClickListener {
    private Context context;
    private ImageView ivHead;
    private TextView tvNickName;
    private LinearLayout llUserInfo;
    private LinearLayout llAboutUs;
    private LinearLayout llCustomerService;
    private LinearLayout llPrivacyPolice;
    private LinearLayout llUserAgreement;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        View view = View.inflate(context, R.layout.fragment_mine, null);

        ivHead = view.findViewById(R.id.ivHead);
        tvNickName = view.findViewById(R.id.tvNickName);
        llUserInfo = view.findViewById(R.id.llUserInfo);
        llAboutUs = view.findViewById(R.id.llAboutUs);
        llCustomerService = view.findViewById(R.id.llCustomerService);
        llPrivacyPolice = view.findViewById(R.id.llPrivacyPolice);
        llUserAgreement = view.findViewById(R.id.llUserAgreement);

        if (Constants.isLogin()) {
            tvNickName.setText(Constants.loginBean.getData().getUsername());
        } else {
            tvNickName.setText("请登录");
        }
        ivHead.setOnClickListener(this);
        tvNickName.setOnClickListener(this);
        llUserInfo.setOnClickListener(this);
        llAboutUs.setOnClickListener(this);
        llPrivacyPolice.setOnClickListener(this);
        llCustomerService.setOnClickListener(this);
        llUserAgreement.setOnClickListener(this);
        return view;
    }


    @Subscribe
    public void onEvent(LoginEvent event) {
        if (event.isLogin()) {
            tvNickName.setText(Constants.loginBean.getData().getUsername());
        } else {
            tvNickName.setText("请登录");
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvNickName:
            case R.id.ivHead:
            case R.id.llUserInfo:
                if (!Constants.isLogin()) {
                    startActivity(new Intent(context, LoginActivity.class));
                } else {
                    startActivity(new Intent(context, UserInfoActivity.class));
                }
                break;
            case R.id.llAboutUs:
                JumpUtil.jumpAboutUs(context);
                break;
            case R.id.llCustomerService:
                JumpUtil.jumpCustomerService(context);
                break;
            case R.id.llPrivacyPolice:
                JumpUtil.jumpPrivacyPolice(context);
                break;
            case R.id.llUserAgreement:
                JumpUtil.jumpUserAgreement(context);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
