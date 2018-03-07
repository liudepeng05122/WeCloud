package com.navinfo.wecloud.base.util;

/**
 * Created by liudepeng on 2018-2-25.
 * mail:liudepeng@navinfo.com
 */

public class ClickUtil {
    private static long lastClickTime;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
