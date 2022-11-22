package com.dlt.express.main;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dlt.express.Constants;
import com.dlt.express.api.UserApi;
import com.dlt.express.base.AppActivity;
import com.dlt.express.base.AppBaseBean;
import com.dlt.express.base.AppCallBack;
import com.hzlh.sdk.util.SPUtils;
import com.hzlh.sdk.util.YToast;
import com.dlt.express.R;

/**
 * 描述：用户信息
 * 作者：Zhout
 * 日期：2020/8/9 22:20
 */
public class UserInfoActivity extends AppActivity {
    private Context context = this;
    private ImageView ivHead;
    private TextView tvNickName;
    private TextView tvRealName;
    private TextView tvSex;
    private TextView tvPhone;
    private Button btnLogout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        initHeader("个人中心");
        ivHead = (ImageView) findViewById(R.id.ivHead);
        tvNickName = (TextView) findViewById(R.id.tvNickName);
        tvRealName = (TextView) findViewById(R.id.tvRealName);
        tvSex = (TextView) findViewById(R.id.tvSex);
        tvPhone = (TextView) findViewById(R.id.tvPhone);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        tvNickName.setText(Constants.loginBean.getData().getUserInfo().getNickName());
        tvRealName.setText(Constants.loginBean.getData().getUserInfo().getRealName());
        tvSex.setText(Constants.loginBean.getData().getUserInfo().getSex().getText());
        tvPhone.setText(Constants.loginBean.getData().getUserInfo().getPhoneNumber());


    }

    private void logout() {
        new UserApi().logout(context, new AppCallBack<AppBaseBean>(context) {
            @Override
            public void onResultOk(AppBaseBean result) {
                super.onResultOk(result);
                YToast.shortToast(context, "退出登录成功！");
                Constants.loginBean = null;
                SPUtils.getInstance(context).putString(Constants.SP_KEY_LOGIN_PWD, "");
                finish();
            }

            @Override
            public void onResultError(AppBaseBean result) {
                super.onResultError(result);
                YToast.shortToast(context, result.getMsg());
            }
        });
    }

}
