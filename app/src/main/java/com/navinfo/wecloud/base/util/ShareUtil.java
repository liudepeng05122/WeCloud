package com.navinfo.wecloud.base.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/6/4.
 */
public class ShareUtil {
    public static SharedPreferences.Editor writeValue(Context context) {
        return context.getSharedPreferences("WeCloud SharedPreferences", Activity.MODE_PRIVATE).edit();
    }

    public static SharedPreferences readValue(Context context) {
        return context.getSharedPreferences("WeCloud SharedPreferences", Activity.MODE_PRIVATE);
    }

    public static void saveBoolean(String key, boolean value, Context context) {
        SharedPreferences.Editor editor = writeValue(context);
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getBoolean(String key, boolean def, Context context) {
        SharedPreferences sharedPreferences = readValue(context);
        return sharedPreferences.getBoolean(key, def);
    }

    public static void saveInt(String key, int value, Context context) {
        SharedPreferences.Editor editor = writeValue(context);
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getInt(String key, int def, Context context) {
        SharedPreferences sharedPreferences = readValue(context);
        return sharedPreferences.getInt(key, def);
    }

    public static void saveString(String key, String value, Context context) {
        SharedPreferences.Editor editor = writeValue(context);
        editor.putString(key, value);
        editor.apply();
    }

    public static String getString(String key, String def, Context context) {
        SharedPreferences sharedPreferences = readValue(context);
        return sharedPreferences.getString(key, def);
    }

    public static final boolean contains(Context context, String key) {
        SharedPreferences sharedPreferences = readValue(context);
        return sharedPreferences.contains(key);
    }
}
