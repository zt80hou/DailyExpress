package com.dlt.express.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.hzlh.sdk.ui.ActivityLifeCycle;
import com.hzlh.sdk.ui.ActivityLifeCycleImpl;
import com.hzlh.sdk.util.YLog;


/**
 * 区别于AppActivity，仅记录Activity跳转，加入生命周期管理
 * 适用于不需要主题的Activity，弹框Activity ...
 */
public class AppFragmentActivity extends FragmentActivity {

    protected final String TAG = getClass().getSimpleName();
    private ActivityLifeCycle activityLifeCycle = new ActivityLifeCycleImpl(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLifeCycle.onCreate();
        YLog.i("页面跳转 >>> " + this.getLocalClassName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        activityLifeCycle.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        activityLifeCycle.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        activityLifeCycle.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityLifeCycle.onDestroy();
    }

}
