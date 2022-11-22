package com.dlt.express.main.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.dlt.express.Constants;
import com.dlt.express.R;
import com.dlt.express.base.AppFragment;
import com.dlt.express.main.UserInfoActivity;
import com.dlt.express.module.user.LoginActivity;

/**
 * 描述：我的
 * 作者：Zhout
 * 日期：2020/8/9 10:45
 */
public class MineFragment extends AppFragment implements View.OnClickListener {
    private Context context;
    private ImageView ivHead;
    private TextView tvNickName;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(context, R.layout.fragment_mine, null);
        ivHead = view.findViewById(R.id.ivHead);
        tvNickName = view.findViewById(R.id.tvNickName);
        if (Constants.isLogin()) {
            tvNickName.setText(Constants.loginBean.getData().getUserInfo().getNickName());
        } else {
            tvNickName.setText("请登录");
        }
        ivHead.setOnClickListener(this);
        tvNickName.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvNickName:
            case R.id.ivHead:
                if (!Constants.isLogin()) {
                    startActivity(new Intent(context, LoginActivity.class));
                } else {
                    startActivity(new Intent(context, UserInfoActivity.class));
                }
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Constants.isLogin()) {
            tvNickName.setText(Constants.loginBean.getData().getUserInfo().getNickName());
        } else {
            tvNickName.setText("请登录");
        }
    }
}
