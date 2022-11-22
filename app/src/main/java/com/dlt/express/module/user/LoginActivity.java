package com.dlt.express.module.user;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import com.dlt.express.Constants;
import com.dlt.express.main.MainActivity;
import com.dlt.express.api.UserApi;
import com.dlt.express.api.LoginBean;
import com.dlt.express.base.AppActivity;
import com.dlt.express.base.AppCallBack;
import com.dlt.express.util.ImmersiveUtil;
import com.dlt.express.util.InputMethodUtils;
import com.hzlh.sdk.util.SPUtils;
import com.hzlh.sdk.util.YToast;
import com.dlt.express.R;

import org.greenrobot.eventbus.EventBus;

/**
 * 描述：登录
 * 作者：Zhout
 * 日期：2020/08/04 08:30
 */
public class LoginActivity extends AppActivity {
    private Context context = this;
    private EditText editAccount;
    private EditText editPwd;
    private Button btnLogin;
    private String account;
    private String pwd;
    private CheckBox checkbox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ImmersiveUtil.modifyStatusBarTextColor(this, true);
        editAccount = findViewById(R.id.editAccount);
        editAccount.requestFocus();
        checkbox = findViewById(R.id.checkbox);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    btnLogin.setClickable(true);
                }
            }
        });
        editPwd = findViewById(R.id.editPwd);
        btnLogin = findViewById(R.id.btnLogin);
        ImageView backImg = findViewById(R.id.backImg);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkParams()) {
                    gotoLogin();
                }
            }
        });

    }

    private boolean checkParams() {
        if (!checkbox.isChecked()) {
            YToast.shortToast(context, "请勾选“我已阅读并同意《用户隐私政策》");
            return false;
        }
        account = editAccount.getText().toString().trim();
        pwd = editPwd.getText().toString().trim();
        if (TextUtils.isEmpty(account)) {
            YToast.shortToast(context, "请输入账号！");
            return false;
        }
        if (TextUtils.isEmpty(pwd)) {
            YToast.shortToast(context, "请输入密码！");
            return false;
        }
        return true;
    }

    private void gotoLogin() {
        new UserApi().login(context, account, pwd, new AppCallBack<LoginBean>(context) {
            @Override
            public void onResultOk(LoginBean result) {
                super.onResultOk(result);
                YToast.shortToast(context, "登录成功！");
                InputMethodUtils.hide(context);
                Constants.loginBean = result;
                SPUtils.getInstance(context).putString(Constants.SP_KEY_LOGIN_ACCOUNT, account);
                SPUtils.getInstance(context).putString(Constants.SP_KEY_LOGIN_PWD, pwd);
                startActivity(MainActivity.class);

                LoginEvent event = new LoginEvent();
                event.setLogin(true);
                EventBus.getDefault().post(event);
                finish();
            }

            @Override
            public void onResultError(LoginBean result) {
                super.onResultError(result);
                YToast.shortToast(context, result.getMsg());
            }

            @Override
            public void onNull() {
                super.onNull();
                YToast.shortToast(context, "登录失败，请重试！");
            }
        });
    }

}
