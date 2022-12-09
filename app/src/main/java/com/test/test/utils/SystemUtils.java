package com.test.test.utils;

import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * 系统工具类
 *
 * @author sm2886
 */
public class SystemUtils {

    private static final String TAG = "TianYOU";
    private static final String MODEL = "ro.build.version.sdk";

    /**
     * 获取当前手机系统语言。
     *
     * @return 返回当前系统语言。例如：当前设置的是“中文-中国”，则返回“zh-CN”
     */
    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取当前系统上的语言列表(Locale列表)
     *
     * @return 语言列表
     */
    public static Locale[] getSystemLanguageList() {
        return Locale.getAvailableLocales();
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return 系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return 手机厂商
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    /**
     * 获取手机IMEI(需要“android.permission.READ_PHONE_STATE”权限)
     *
     * @return 手机IMEI
     */
    public static String getIMEI(Context ctx) {
        TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Activity.TELEPHONY_SERVICE);
        if (tm != null) {
            return tm.getDeviceId();
        }
        return null;
    }


    public static void isPersistSunMiModel() {
        String name = System.getProperty(MODEL);
        System.getProperties().list(System.out);
        if (name == null) {
            Log.e(TAG, " " + null);
        } else {
            Log.d(TAG, name);
        }
    }

    public static int setNumber(){
        Random r = new Random();

        return r.nextInt(255);

    }

    /**
     * 获取当前系统支持的语言集
     * @return
     */
    public static List<String> getLanguages(){
        List<String> list=new ArrayList<>();
        Locale[] lg = Locale.getAvailableLocales();
        for(Locale language:lg){
            String name=language.getDisplayLanguage();
            //去掉重复的语言
            if (!list.contains(name)){
                list.add(name);
            }
        }
        return list;
    }



}
