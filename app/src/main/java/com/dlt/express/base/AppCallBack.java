package com.dlt.express.base;

import android.content.Context;

import com.hzlh.sdk.net.CallBack;

import okhttp3.Response;

/**
 * 项目基础回调处理类，项目中原调用CallBack的地方需要替换成该类
 * Created by 夏吧吧Ace on 2018/9/4.
 */
public class AppCallBack<T> extends CallBack<T> {
    private Context mContext;

    public AppCallBack(Context context) {
        super(context);
        mContext = context;
    }

    public AppCallBack(Context context, boolean shouldDecode) {
        super(context, shouldDecode);
        mContext = context;
    }

    @Override
    public void onNull() {
        super.onNull();
    }

    @Override
    public void onResultError(T result) {
        super.onResultError(result);
    }

    @Override
    public void onResultOk(T result) {
        super.onResultOk(result);
    }

    @Override
    public void onHttpError(int errorCode, Response response) {
        super.onHttpError(errorCode, response);
    }
}
