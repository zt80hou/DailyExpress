package com.dlt.express.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hzlh.sdk.util.YLog;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Map;

/**
 * Json解析工具类
 */
public class JsonUtil {
    private static Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().create();

    @SuppressWarnings("hiding")
    public static <T> T parseJson(String response, Class<T> clazz) {
        try {
            return gson.fromJson(response, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T parseJson(String response, Type type) {
        try {
            return gson.fromJson(response, type);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String toJson(Object object) {
        try {
            String json = gson.toJson(object);
            YLog.i("json = " + json);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static Map<String, Object> object2Map(Object obj) {
        if (obj == null) {
            return Collections.emptyMap();
        }
        return gson.fromJson(gson.toJson(obj), Map.class);
    }
}