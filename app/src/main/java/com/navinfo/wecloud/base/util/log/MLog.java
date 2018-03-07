package com.navinfo.wecloud.base.util.log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class MLog {
    private static final String TAG = "MLog";

    private MLog() {
        //do nothing
    }

    public static final void dumpLogCat(String logFilePath) {
        List<String> commandLine = new ArrayList<>();
        commandLine.add("logcat");
        //使用该参数可以让logcat获取日志完毕后终止进程
        commandLine.add("-d");
        commandLine.add("-v");
        commandLine.add("time");
        //如果使用commandLine.add(">");是不会写入文件，必须使用-f的方式
        commandLine.add("-f");
        commandLine.add(logFilePath);
        try {
            Runtime.getRuntime().exec(commandLine.toArray(new String[commandLine.size()]));
        } catch (IOException e) {
            LogUtils.e(TAG, e);
        }
    }

}
