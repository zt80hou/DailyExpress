package com.dlt.express.base;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hzlh.sdk.net.CallBack;
import com.hzlh.sdk.net.YFile;
import com.hzlh.sdk.net.YRequest;
import com.hzlh.sdk.util.YLog;

import java.util.HashMap;
import java.util.List;


/**
 * 请求类
 * Created by 夏吧吧 on 2020/4/10.
 */
public class MRequest extends YRequest {
    public static Gson gson = new GsonBuilder().serializeNulls().excludeFieldsWithoutExposeAnnotation().disableHtmlEscaping().create();

    public static MRequest getInstance() {
        if (okHttpClient != null && (long) okHttpClient.readTimeoutMillis() != 60L) {
            setTimeOut(60L);// 1分钟
        }

        return MRequest.Loader.instance;
    }

    @Override
    protected void handleResult(Object object, CallBack handler) {
        if (object == null) {
            handler.onNull();
        } else if (!"SUCCESS".equals(((AppBaseBean) object).getStatus())) {
            handler.onNull();
            handler.onResultError(object);
//            YLog.e("YRequest error ", "response=" + handler.getUrl() + " " + this.gson.toJson(object));
        } else {
            handler.onResultOk(object);
//            if (handler.isDefault()) {
//                YLog.v("YRequestVLog", "response=" + handler.getUrl() + " " + this.gson.toJson(object));
//            } else {
//                YLog.i("YRequest", "response=" + handler.getUrl() + " " + this.gson.toJson(object));
//            }
        }
    }

    @Override
    protected void handleResultJson(Context context, String resp, CallBack handler) {
        super.handleResultJson(context, resp, handler);
        if (resp.contains("SUCCESS")) {
            YLog.i("YRequest", "response resp =" + handler.getUrl() + " " + resp);
        } else {
            YLog.e("YRequest", "response resp =" + handler.getUrl() + " " + resp);
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
        YLog.i("YRequest", "postFile = " + url + ",  fileList = " + buffer.toString());
        super.postFile(context, url, _class, map, fileList, handler);
    }

    @Override
    public void postJson(Context context, String url, String json, Class<?> _class, CallBack handler) {
        YLog.i("YRequest", "postJson = " + url + ",  json = " + json);
        super.postJson(context, url, json, _class, handler);
    }

    static class Loader {
        private static final MRequest instance = new MRequest();

        Loader() {
        }
    }
}
