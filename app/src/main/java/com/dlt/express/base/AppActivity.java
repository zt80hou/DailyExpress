package com.dlt.express.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dlt.express.R;
import com.dlt.express.util.ImmersiveUtil;
import com.dlt.express.util.permission.ApplyPermissionListener;
import com.dlt.express.util.permission.GrantListener;
import com.dlt.express.util.permission.PermissionVerifyUtil;
import com.hzlh.sdk.net.BaseBean;
import com.hzlh.sdk.ui.ActivityLifeCycle;
import com.hzlh.sdk.ui.ActivityLifeCycleImpl;
import com.hzlh.sdk.ui.BaseActivity;
import com.hzlh.sdk.util.YLog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Activity基础类
 */
public class AppActivity extends BaseActivity {

    protected final String TAG = getClass().getSimpleName();
    private ActivityLifeCycle activityLifeCycle = new ActivityLifeCycleImpl(this);
    protected RelativeLayout headerLayout;
    protected TextView titleTxt, rightTxt;
    protected ImageView backImg, rightImg, rightSecondImg;
    protected View titleLine;
    public boolean isActive = true;
    protected PermissionVerifyUtil permissionVerifyUtil;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (outState != null) {
            String FRAGMENTS_TAG = "android:support:fragments";
            // remove掉保存的Fragment
            outState.remove(FRAGMENTS_TAG);
        }
    }

    /**
     * 创建函数
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        activityLifeCycle.onCreate();
        if (permissionVerifyUtil == null) {
            permissionVerifyUtil = new PermissionVerifyUtil(this, null);
        }
        ImmersiveUtil.modifyStatusBarTextColor(this);

        YLog.i("页面跳转 >>> " + this.getLocalClassName());

        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void onEvent(BaseBean bean) {

    }

    /**
     * @param title
     */
    protected void initHeader(String title) {
        headerLayout = (RelativeLayout) findViewById(R.id.head_layout);
        backImg = (ImageView) findViewById(R.id.back_img);
        rightImg = (ImageView) findViewById(R.id.right_img);
        rightSecondImg = (ImageView) findViewById(R.id.right_second_img);
        titleTxt = (TextView) findViewById(R.id.title_txt);
        rightTxt = (TextView) findViewById(R.id.right_txt);
        titleLine = findViewById(R.id.title_line);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleTxt.setText(title);
    }

    public void startActivity(Class<?> clazz) {
        startActivity(new Intent(this, clazz));
    }

    @Override
    protected void onResume() {
        activityLifeCycle.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        activityLifeCycle.onPause();
    }

    @Override
    protected void onStop() {
        activityLifeCycle.onStop();
        isActive = false;
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        activityLifeCycle.onDestroy();
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
    }


    /**
     * 申请权限
     */
    public void applyPermission(int permission, ApplyPermissionListener listener) {
        // 请求使用单个权限
        permissionVerifyUtil.apply(permission, new GrantListener() {
            @Override
            public void onAgree() {
                YLog.d("AppActivity: 权限已同意");
                if (listener != null) {
                    listener.onPermissionAllowed();
                }
            }

            @Override
            public void onDeny() {
                YLog.d("AppActivity: 权限被拒绝，未点击不再询问");
            }

            @Override
            public void onDenyNotAskAgain() {
                YLog.d("AppActivity: 权限被拒绝，并点击了不再询问");
            }
        });
    }

    /**
     * 请求权限结果回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissionVerifyUtil != null) {
            if (requestCode == PermissionVerifyUtil.ONCE_TIME_APPLY) {
                permissionVerifyUtil.onceTimeApplyResult(permissions, grantResults);
            } else {
                permissionVerifyUtil.singleApplyResult(requestCode, grantResults);
            }
        }
    }
}
