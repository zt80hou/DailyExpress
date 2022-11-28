package com.dlt.express.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.PermissionChecker;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.hzlh.sdk.util.StringUtils;
import com.hzlh.sdk.util.YLog;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @author 作者 : luohl
 * @version 创建时间：2014-2-26 下午6:07:44 类说明
 */
public class Util {
    private static long lastClickTime;
    /**
     * 地球半径,单位 km
     */
    private static final double EARTH_RADIUS = 6378.137;
    private static AlertDialog.Builder builder;
    private static boolean isDialogShow = false;
    private static int GPS_REQUEST_CODE = 10;


    public static boolean isFastDoubleClick() {
        return isFastDoubleClick(1000);
    }

    public static boolean isFastDoubleClick(long duration) {
        long time = System.currentTimeMillis();
        long intercept = time - lastClickTime;
        if (0 < intercept && intercept < duration) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    public static View inflate(Context applicationContext, int layoutId) {
        LayoutInflater inflate = (LayoutInflater) applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflate.inflate(layoutId, null);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * sp转px.
     *
     * @param value the value
     * @return the int
     */
    public static int sp2px(Context context, float value) {
        Resources r;
        if (context == null) {
            r = Resources.getSystem();
        } else {
            r = context.getResources();
        }
        float spvalue = value * r.getDisplayMetrics().scaledDensity;
        return (int) (spvalue + 0.5f);
    }

    public static int px2sp(Context context, float pxValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }


    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    public static Bitmap getBitmapFromResources(Activity act, int resId) {
        Resources res = act.getResources();
        return BitmapFactory.decodeResource(res, resId);
    }

    public static String getDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(
                System.currentTimeMillis()));
    }

    public static long compare_date(String DATE1, String DATE2) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return ((dt1.getTime() - dt2.getTime()) / 1000);
            } else if (dt1.getTime() < dt2.getTime()) {
                return ((dt1.getTime() - dt2.getTime()) / 1000);
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public static void deleteDir(File dir) {
        if (dir == null) {
            return;
        }
        if (dir.isDirectory()) {
            String[] children = dir.list();
            if (children != null) {
                for (int i = 0; i < children.length; i++) {
                    deleteDir(new File(dir, children[i]));
                }
            }
        }
        dir.delete();
    }


    public static String list2String(List<String> list) {
        JSONArray jsonArray = new JSONArray();
        try {
            for (int i = 0; i < list.size(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", list.get(i));
                jsonArray.put(i, jsonObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonArray.toString();
    }



    /**
     * 解决ScrollView嵌套EditText滑动问题
     *
     * @param editText EditText
     */
    public static void handleScroll(final EditText editText) {
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                //触摸的是EditText并且当前EditText可以滚动则将事件交给EditText处理；否则将事件交由其父类处理
                if (canVerticalScroll(editText)) {
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        view.getParent().requestDisallowInterceptTouchEvent(false);
                    }
                }
                return false;
            }
        });
    }


    /**
     * EditText竖直方向是否可以滚动
     *
     * @param editText 需要判断的EditText
     * @return true：可以滚动   false：不可以滚动
     */
    private static boolean canVerticalScroll(EditText editText) {
        //滚动的距离
        int scrollY = editText.getScrollY();
        //控件内容的总高度
        int scrollRange = editText.getLayout().getHeight();
        //控件实际显示的高度
        int scrollExtent = editText.getHeight() - editText.getCompoundPaddingTop() - editText.getCompoundPaddingBottom();
        //控件内容总高度与实际显示高度的差值
        int scrollDifference = scrollRange - scrollExtent;

        if (scrollDifference == 0) {
            return false;
        }

        return (scrollY > 0) || (scrollY < scrollDifference - 1);
    }



    /**
     * 获取屏幕的宽度px
     *
     * @param context 上下文
     * @return 屏幕宽px
     */
    public static int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获取屏幕的高度px，不包括虚拟功能键功能
     *
     * @param context 上下文
     * @return 屏幕高px
     */
    public static int getScreenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * 判断是否已获取了某权限
     *
     * @param context
     * @param permission
     * @return
     */
    public static boolean hasSomePermission(Context context, String permission) {
        if (PermissionChecker.PERMISSION_GRANTED == PermissionChecker.checkSelfPermission(context, permission)) {
            return true;
        } else {
            return false;
        }
    }


    public static void locate(Context context, boolean openGPS, LocListener listener) {

        //声明定位回调监听器
        AMapLocationListener mLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (null != aMapLocation) {
                    if (aMapLocation.getErrorCode() == 0) {
                        YLog.i("aMapLocation : " + aMapLocation.toString());
                    } else {
                        // 12定位权限； 13GPS问题
                        if (aMapLocation.getErrorCode() == 12 || aMapLocation.getErrorCode() == 13) {
                            if (openGPS) {
//                                openGPSDialog(context);
                            }
                        }
                        YLog.e("aMapLocation error : " + aMapLocation.getErrorInfo());
                    }
                } else {
                    YLog.e("aMapLocation error: aMapLocation==null ");
                }
                if (listener != null) {
                    listener.onLocResult(aMapLocation);
                }
            }
        };
        //高德定位 @since 5.6.0 必须在AMapLocationClient实例化之前调用
        AMapLocationClient.updatePrivacyShow(context, true, true);
        AMapLocationClient.updatePrivacyAgree(context, true);

        //初始化定位
        AMapLocationClient mLocationClient = null;
        try {
            mLocationClient = new AMapLocationClient(context.getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        AMapLocationClientOption option = new AMapLocationClientOption();
//        option.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose);
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy); // 高精度
        option.setOnceLocation(true);
        option.setLocationCacheEnable(false);
        option.setInterval(1000);

        if (mLocationClient != null) {
            mLocationClient.setLocationOption(option);
            //设置定位回调监听
            mLocationClient.setLocationListener(mLocationListener);
            // 开始定位
            //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
            mLocationClient.stopLocation();
            mLocationClient.startLocation();
        }
    }

    public interface LocListener {
        void onLocResult(AMapLocation aMapLocation);
    }

//    public static void openGPSDialog(Context context) {
//        if (isDialogShow) {
//            return;
//        }
//        DialogShow.dialogShow(context, "定位服务已关闭", "定位服务已关闭，请打开定位服务。", "去设置", "取消", new DialogShow.ICallBack() {
//            @Override
//            public void onOkClick() {
//                //跳转到手机打开GPS页面
//                //跳转GPS设置界面
//                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                ((Activity) context).startActivityForResult(intent, GPS_REQUEST_CODE);
//                isDialogShow = false;
//            }
//
//            @Override
//            public void onCancel() {
//                isDialogShow = false;
//            }
//        });
//        isDialogShow = true;
//    }


    /**
     * 根据经纬度，计算两点间的距离
     *
     * @param longitude1 第一个点的经度
     * @param latitude1  第一个点的纬度
     * @param longitude2 第二个点的经度
     * @param latitude2  第二个点的纬度
     * @return 返回距离 单位千米
     */
    public static double getDistance(String longitude1, String latitude1, double longitude2, double latitude2) {
        if (StringUtils.isEmpty(longitude1) || StringUtils.isEmpty(latitude1)) {
            return Double.MAX_VALUE;
        }
        // 纬度
        double lat1 = Math.toRadians(Double.parseDouble(latitude1));
        double lat2 = Math.toRadians(latitude2);
        // 经度
        double lng1 = Math.toRadians(Double.parseDouble(longitude1));
        double lng2 = Math.toRadians(longitude2);
        // 纬度之差
        double a = lat1 - lat2;
        // 经度之差
        double b = lng1 - lng2;
        // 计算两点距离的公式
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(b / 2), 2)));
        // 弧长乘地球半径, 返回单位: 千米
        s = s * EARTH_RADIUS;
        return s;
    }


}
