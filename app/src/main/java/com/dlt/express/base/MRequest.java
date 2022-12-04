package com.dlt.express.base;

import android.content.Context;

import com.dlt.express.BuildConfig;
import com.dlt.express.Constants;
import com.hzlh.sdk.net.CallBack;
import com.hzlh.sdk.net.YFile;
import com.hzlh.sdk.net.YRequest;
import com.hzlh.sdk.util.YLog;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.Request;


/**
 * 请求类
 */
public class MRequest extends YRequest {
    private static MRequest mInstance;
    private static HashMap<String, String> headerMap = new HashMap<>();
    private final String TAG = "MRequest";


    public static MRequest getInstance() {
        if (okHttpClient != null && (long) okHttpClient.readTimeoutMillis() != 60L) {
            setTimeOut(60L);// 1分钟
        }
        if (mInstance == null) {
            mInstance = new MRequest();
        }
        headerMap.clear();
        // 统一加上header
        if (Constants.loginBean != null && Constants.loginBean.getData() != null) {
            // Bearer Token认证
            headerMap.put("Authorization", Constants.loginBean.getData().getTokenType() + " " + Constants.loginBean.getData().getAccessToken());
            headerMap.put("version", BuildConfig.VERSION_NAME);
            headerMap.put("plateForm", "Android App");
        }
        return mInstance;
    }

    public MRequest addHeader(String key, String value) {
        headerMap.put(key, value);
        return mInstance;
    }

    @Override
    protected Request addHeaders(Request request) {
        Iterator iterator = headerMap.entrySet().iterator();
        Request.Builder request1 = request.newBuilder();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            if (entry.getValue() != null) {
                request1.addHeader(entry.getKey().toString(), String.valueOf(entry.getValue())).build();
                YLog.d(TAG, "header = " + entry.getKey().toString() + " : " + entry.getValue());
            }
        }
        return request1.build();
    }

    @Override
    protected void handleResult(Object object, CallBack handler) {
        if (object == null) {
            handler.onNull();
        } else if (!"SUCCESS".equals(((AppBaseBean) object).getStatus())) {
            handler.onNull();
            handler.onResultError(object);
        } else {
            handler.onResultOk(object);
        }
    }

    @Override
    protected void handleResultJson(Context context, String resp, CallBack handler) {
        super.handleResultJson(context, resp, handler);
        if (resp.contains("SUCCESS")) {
            YLog.i(TAG, "response resp =" + handler.getUrl() + " " + resp);
        } else {
            YLog.e(TAG, "response resp =" + handler.getUrl() + " " + resp);
        }

    }

    @Override
    public void postFile(Context context, String url, Class<?> _class, HashMap<String, String> map, List<YFile> fileList, CallBack handler) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < fileList.size(); i++) {
            buffer.append(fileList.get(i).getFile().getAbsolutePath());
            if (i != fileList.size() - 1) {
                buffer.append(", ");
            }
        }
        YLog.i(TAG, "postFile = " + url + ",  fileList = " + buffer.toString());
        super.postFile(context, url, _class, map, fileList, handler);
    }

    @Override
    public void postJson(Context context, String url, String json, Class<?> _class, CallBack handler) {
        YLog.i(TAG, "postJson = " + url + ",  json = " + json);
        super.postJson(context, url, json, _class, handler);
    }

}
