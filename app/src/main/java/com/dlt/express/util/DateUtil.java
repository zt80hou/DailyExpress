package com.dlt.express.util;

import android.text.TextUtils;

import com.hzlh.sdk.util.YLog;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtil {

    /**
     * 获取现在时间的字符串
     *
     * @param parttern
     * @return
     */
    public static String getNowDateStr(String parttern) {
        Date c = new Date();
        DateFormat formater = new SimpleDateFormat(parttern);
        return formater.format(c);
    }

    /**
     * 获取现在时间 小时
     *
     * @return
     */
    public static String getCurrentHour() {
        Date c = new Date();
        DateFormat formatter = new SimpleDateFormat("HH", Locale.getDefault());
        return formatter.format(c);
    }


    /**
     * 字符串转时间
     *
     * @param timeString
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Timestamp str2Time(String timeString, String pattern) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return new Timestamp(format.parse(timeString).getTime());
    }

    /**
     * 时间获取时分
     *
     * @param timeString
     * @return
     * @throws ParseException
     */
    public static String getHourMinute(String timeString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(timeString);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
        String month = String.valueOf(date.getMonth() + 1);
        String day = String.valueOf(date.getDate());
        String hours = String.valueOf(date.getHours());
        if (hours.length() < 2)
            hours = "0" + hours;
        String minutes = String.valueOf(date.getMinutes());
        if (minutes.length() < 2)
            minutes = "0" + minutes;
        return month + "月" + day + "日" + hours + ":" + minutes;
    }

    /**
     * 时间转字符串
     *
     * @param date
     * @param pattern HH:mm:ss
     * @return
     */
    public static String time2Str(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }


    /**
     * 获取时分秒
     *
     * @param date
     * @return
     */
    public static String getTime(Date date) {
        return time2Str(date, "HH:mm:ss");
    }

    /**
     * 获取日期
     *
     * @param type 0今天；  -1昨天； 1明天
     * @return 日期yyyy-MM-dd
     */
    public static String getDate(int type) {
        Date date = new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, type);//把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime(); //这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return formatter.format(date);
    }


    /**
     * 比较时分（12:34）
     *
     * @param currentTime
     * @param endTime
     * @param startTime
     * @return true currentTime >= startTime && currentTime <= endTime
     */
    public static boolean compareTime(String currentTime, String startTime, String endTime, String pattern) {
        try {
            Timestamp t1 = str2Time(currentTime, pattern);
            Timestamp t2 = str2Time(startTime, pattern);
            Timestamp t3 = str2Time(endTime, pattern);
            return t1.getTime() >= t2.getTime() && t1.getTime() < t3.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            YLog.e("时间比较异常：" + e.getMessage());
        }
        return false;
    }

    /**
     * 时间与当前时间倒计时毫秒
     *
     * @param dateTime yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static long dateTimeLeft(String dateTime) {
        if (TextUtils.isEmpty(dateTime)) {
            return -1;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        try {
            Date dateStart = formatter.parse(dateTime);
            Date dateNow = new Date();
            long timeLeft = dateStart.getTime() - dateNow.getTime();
            return timeLeft;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
