package com.dlt.express;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;

import androidx.multidex.MultiDex;

import com.hzlh.sdk.BuildConfig;
import com.hzlh.sdk.constant.Screen;
import com.hzlh.sdk.ui.BaseApp;
import com.hzlh.sdk.util.YLog;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

import okhttp3.Response;

public class CntCenterApp extends BaseApp {
    private static CntCenterApp mInstance;

    public static CntCenterApp getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        initApplication();

    }

    @Override
    public String getOssPrefix() {
        return Constants.OSS_PREFIX;
    }

    @Override
    public String getOssPrefix2() {
        return Constants.OSS_PREFIX2;
    }

    @Override
    public boolean openOssSupport() {
        return Constants.OPEN_OSS_SUPPORT;
    }


    /**
     * 获取当前进程名
     */
    public String getCurrentProcessName() {
        int pid = android.os.Process.myPid();
        String processName = "";
        ActivityManager manager = (ActivityManager) getApplicationContext().getSystemService
                (Context.ACTIVITY_SERVICE);
        if (manager.getRunningAppProcesses() == null || manager.getRunningAppProcesses().size() == 0) {
            return "";
        }
        for (ActivityManager.RunningAppProcessInfo process : manager.getRunningAppProcesses()) {
            if (process.pid == pid) {
                processName = process.processName;
            }
        }
        return processName;
    }


    private void initApplication() {
        closeAndroidPDialog();


        YLog.e("screen==" + Screen.density);
        YLog.e("screen==" + Screen.densityDpi);
        YLog.e("screen==" + Screen.width);
        YLog.e("screen==" + Screen.height);

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    /**
     * 获取App程序版本名
     */
    public static String getAppVersionName() {
        String versionName = "";
        try {
            PackageManager manager = mInstance.getPackageManager();
            PackageInfo info = manager.getPackageInfo(mInstance.getPackageName(), 0);
            versionName = info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 关闭AndroidP弹框 （Detected problems with API compatibility(visit g.co/dev/appcompat for more info)
     * https://www.jianshu.com/p/f87fe39caf1d?tdsourcetag=s_pctim_aiomsg
     */
    @SuppressLint({"PrivateApi", "DiscouragedPrivateApi"})
    private void closeAndroidPDialog() {
        if (Build.VERSION.SDK_INT < 28) {
            return;
        }
        try {
            Class<?> aClass = Class.forName("android.content.pm.PackageParser$Package");
            Constructor declaredConstructor = aClass.getDeclaredConstructor(String.class);
            declaredConstructor.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Method declaredMethod = cls.getDeclaredMethod("currentActivityThread");
            declaredMethod.setAccessible(true);
            Object activityThread = declaredMethod.invoke(null);
            Field mHiddenApiWarningShown = cls.getDeclaredField("mHiddenApiWarningShown");
            mHiddenApiWarningShown.setAccessible(true);
            mHiddenApiWarningShown.setBoolean(activityThread, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static long lastHttpErrorTime;

    @Override
    public void onHttpError(int errorCode, Response response) {
        super.onHttpError(errorCode, response);
        YLog.e("==onHttpError=" + errorCode);
    }

    @Override
    public HashMap<String, String> getHeaderMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("version", BuildConfig.VERSION_NAME);
        if (Constants.loginBean != null && Constants.loginBean.getData() != null) {
            // Bearer Token认证
            map.put("Authorization", Constants.loginBean.getData().getTokenType() + " " + Constants.loginBean.getData().getAccessToken());
        }
        YLog.d("headers = " + map.toString());
        return map;
    }

}
