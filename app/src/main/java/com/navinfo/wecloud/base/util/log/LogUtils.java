package com.navinfo.wecloud.base.util.log;

import android.util.Log;

public final class LogUtils {
    static {
        KLog.init(true);
    }

    private LogUtils() {
        //do nothing
    }

    public static void v(String tag, String msg) {
        KLog.v(tag, msg);
    }

    public static void d(String tag, String msg) {
        KLog.d(tag, msg);
    }

    public static void i(String tag, String msg) {
        KLog.i(tag, msg);
    }

    public static void w(String tag, String msg) {
        KLog.i(tag, msg);
    }

    public static void e(String tag, Throwable tr) {
        KLog.e(tag, Log.getStackTraceString(tr));
    }

    public static void e(String tag, String msg, Throwable tr) {
        KLog.e(tag, msg + '\n' + Log.getStackTraceString(tr));
    }


//    private static final String DEFAULT_TAG = "AG_LOGGER";
//
//    static {
//        Logger.init(DEFAULT_TAG).logLevel(LogLevel.FULL).hideThreadInfo();
//    }
//
//    private LogUtils() {
//        //do nothing
//    }
//
//    public static void v(String tag, String message) {
//        Logger.t(tag).v(message);
//    }
//
//    public static void v(String tag, String message, Object... args) {
//        Logger.t(tag).v(message, args);
//    }
//
//    public static void d(String tag, String message) {
//        Logger.t(tag).d(message);
//    }
//
//    public static void d(String tag, String message, Object... args) {
//        Logger.t(tag).d(message, args);
//    }
//
//    public static void i(String tag, String message) {
//        Logger.t(tag).i(message);
//    }
//
//    public static void i(String tag, String message, Object... args) {
//        Logger.t(tag).i(message, args);
//    }
//
//    public static void w(String tag, String message) {
//        Logger.t(tag).w(message);
//    }
//
//    public static void w(String tag, String message, Object... args) {
//        Logger.t(tag).w(message, args);
//    }
//
//    public static void e(String tag, Throwable tr) {
//        Logger.t(tag).e(tr, "");
//    }
//
//    public static void e(String tag, Throwable tr, String message) {
//        Logger.t(tag).e(tr, message);
//    }
//
//    public static void e(String tag, Throwable tr, String message, Object... args) {
//        Logger.t(tag).e(tr, message, args);
//    }

}
