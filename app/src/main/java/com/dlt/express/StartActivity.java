package com.dlt.express;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;

import com.dlt.express.api.LoginBean;
import com.dlt.express.api.UserApi;
import com.dlt.express.api.UserInfoBean;
import com.dlt.express.base.AppActivity;
import com.dlt.express.base.AppCallBack;
import com.dlt.express.main.MainActivity;
import com.hzlh.sdk.util.SPUtils;
import com.hzlh.sdk.util.YLog;

/**
 * 描述：启动页
 * 作者：Zhout
 * 日期：2020/8/3 9:04
 */
public class StartActivity extends AppActivity {
    private Context context = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_start);

        String account = SPUtils.getInstance(this).getString(Constants.SP_KEY_LOGIN_ACCOUNT);
        String pwd = SPUtils.getInstance(this).getString(Constants.SP_KEY_LOGIN_PWD);

        if (!TextUtils.isEmpty(account) && !TextUtils.isEmpty(pwd)) {
            autoLogin(account, pwd);
        } else {
            jumpToMain();
        }
    }

    private void autoLogin(String account, String pwd) {
        new UserApi().login(context, account, pwd, new AppCallBack<LoginBean>(context) {
            @Override
            public void onResultOk(LoginBean result) {
                super.onResultOk(result);
                YLog.e("===autoLogin========");
                Constants.loginBean = result;
                SPUtils.getInstance(context).putString(Constants.SP_KEY_LOGIN_ACCOUNT, account);
                SPUtils.getInstance(context).putString(Constants.SP_KEY_LOGIN_PWD, pwd);

                getUserInfo();
            }

            @Override
            public void onNull() {
                super.onNull();
                jumpToMain();
            }
        });
    }

    private void getUserInfo() {
        new UserApi().getUserInfo(context, new AppCallBack<UserInfoBean>(context) {
            @Override
            public void onResultOk(UserInfoBean result) {
                super.onResultOk(result);
                Constants.userInfoBean = result;
                jumpToMain();
            }

            @Override
            public void onNull() {
                super.onNull();
                jumpToMain();
            }
        });
    }

    private void jumpToMain() {
        startActivity(MainActivity.class);
        finish();
    }

}
