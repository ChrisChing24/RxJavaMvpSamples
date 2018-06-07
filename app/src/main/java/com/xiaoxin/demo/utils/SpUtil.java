package com.xiaoxin.demo.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtil {


    // 保存String
    public static void saveString(Context context,  String key, String value) {

        SharedPreferences sp = context.getSharedPreferences(SP_NAME,
                Context.MODE_PRIVATE);
        sp.edit().putString(key, value).apply();
    }

    // 获取String
    public static String getString(Context context, String key, String defValue) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME,
                Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    // 保存int
    public static void saveInt(Context context,  String key, int value) {

        SharedPreferences sp = context.getSharedPreferences(SP_NAME,
                Context.MODE_PRIVATE);
        sp.edit().putInt(key, value).apply();
    }

    // 获取int
    public static int getInt(Context context, String key, int defValue) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME,
                Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }
    private static String SP_NAME = "config";
    private static SharedPreferences sp;

    public static void saveBoolean(Context context, String key, boolean value) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);

        return sp.getBoolean(key, defValue);
    }


}
