package com.dlt.express.sacn;

import com.huayusoft.barcodeadmin.entity.IScanSetting;

public class SettingParams {
    /**
     * 输出编码
     * 0-->UTF-8（默认)
     * 1-->GBK
     */
    public static int mOutCodeCharSet = 0;
    /**
     * 提示音(index:0->无声音;1->声音1;2->声音2；3->声音3)
     */
    public static int mTone = 2;
    /**
     * 音量大小(0->10)
     * */
    public static int mVolume = 5;
    /**
     * 提示方式 震动(0->关闭，1->开启）
     * 默认关闭
     * */
    public static int mVibrator = 0;
    /**
     * led提示灯 (0->关闭，1->开启)
     * */
    public static int mLedIndicator = 1;
    /**
     * 按键扫描（0->关闭，1->开启）
     * 默认关闭
     * */
    public static int mEnableScanKey = 1;
    /**
     * 二维条码出光强度(0->10)
     * */
    public static int mLightIntensity = 2;
    /**
     * 二维免提模式(0-关闭，1->开启)
     * */
    public static int mHandsFree = 1;
    /**
     * 二维屏幕扫描模式(0->关闭，1->开启)
     * */
    public static int mMobileMode = 1;

    /**
     * 连续扫描(0->关闭，1->开启)
     * */
    public static int mContinueScan = 0;
    /**
     * 条码头恢复出厂设置（zebra一维和二维）
     * 0->不恢复；1->恢复出厂
     * */
    public static int mDefaultSetting = 0;

    /**
     * 输出广播
     * 0-->关（默认)
     * 1-->开
     */
    public static int mOutBroadcast = 1;
    /**
     * 填充与模拟按键输出
     * 0-->无（默认）
     * 1-->直接输出
     * 2-->模拟按键输出
     */
    public static int mOutType = 0;
    /**
     * 设置自动补充   十  十六
     * index:
     * 0-->自动补换行 10 0A
     * 1-->自动补TAB  9 09
     * 2-->自动补Dot  点号
     * 3-->自动补Start 星号
     * value:
     * 0-->关（默认)
     * 1-->开
     */
    public static int[] mOutAutoAdd = new int[]{0, 0};
    /**
     * 模拟按键间隔时间
     */
    public static int mOutCharInterval = 0;
    /**
     * 前缀
     * 0-->关（默认)
     * 1-->开
     */
//    public static int mPrefix = 0;
    /**
     * 后缀
     * 0-->关（默认)
     * 1-->开
     */
//    public static int mSuffix = 0;
    /**
     * 前缀内容
     */
//    public static String mPrefixContext = "";
    /**
     * 后缀内容
     */
//    public static  String mSuffixContext = "";

    /**
     * 高级设置
     * 0-->关（默认)
     * 1-->开
     */
//    public static  int mAdvancedFormat = 0;

    /**
     * 触发方式
     * 0 --> 短按触发，松开停止（默认)
     * 1 --> 短按触发扫码至超时
     */
    public static int mTriggerMethod = 1;

    /**
     * 扫码触发超时时间设置（单位：ms）
     */
    public static int mTriggerTimeOut = 10000;

    public static IScanSetting getIScanParam(IScanSetting iScanSetting){
        iScanSetting.mOutCodeCharSet = mOutCodeCharSet;
        iScanSetting.mTone = mTone;
        iScanSetting.mVolume = mVolume;
        iScanSetting.mVibrator = mVibrator;
        iScanSetting.mLedIndicator = mLedIndicator;
        iScanSetting.mEnableScanKey = mEnableScanKey;
        iScanSetting.mLightIntensity = mLightIntensity;
        iScanSetting.mHandsFree = mHandsFree;
        iScanSetting.mMobileMode = mMobileMode;
        iScanSetting.mContinueScan = mContinueScan;
        iScanSetting.mDefaultSetting = mDefaultSetting;
        iScanSetting.mOutBroadcast = mOutBroadcast;
        iScanSetting.mOutType = mOutType;
        iScanSetting.mOutAutoAdd = mOutAutoAdd;
        iScanSetting.mOutCharInterval = mOutCharInterval;
        iScanSetting.mTriggerMethod =mTriggerMethod;
        iScanSetting.mTriggerTimeOut = mTriggerTimeOut;
        return iScanSetting;

    }
}
