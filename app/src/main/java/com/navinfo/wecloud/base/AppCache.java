package com.navinfo.wecloud.base;

import android.content.Context;

import com.navinfo.wecloud.base.util.ShareUtil;

/**
 * Created by liudepeng on 2018-2-25.
 * mail:liudepeng@navinfo.com
 */

public class AppCache {
    public static final String ScyPwd = "isHasScyPwd";
    public static final boolean fpControl = true;

    // 安防密码第一次输入时间
    private int mScyPwdStart = 0;
    // 安防密码在有效时间内
    private boolean isScyPwdVaild = false;
    // 安防密码，使用MD5加密后的字符串
    private String scyPwd = null;

    public static int CUR_CONTROL_CMD = 0;

    private static AppCache appCache;

    public static AppCache getInstance() {
        if (appCache == null) {
            synchronized (AppCache.class) {
                if (appCache == null) {
                    appCache = new AppCache();
                }
            }
        }
        return appCache;
    }

    /**
     * 获取是否有安防密码
     *
     * @return
     */
    public boolean isHasScyPwd(Context context) {
        scyPwd = ShareUtil.getString(ScyPwd, null, context);

        if (scyPwd == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获取是否有指纹控制权限
     *
     * @return
     */
    public boolean isFpControl() {
        return fpControl;
    }

    public boolean isScyPwdVaild() {
        return isScyPwdVaild;
    }

    public void setScyPwdVaild(boolean scyPwdVaild) {
        isScyPwdVaild = scyPwdVaild;
    }

    /**
     * 更新安防密码有效时长
     */
    public void UpdateScyPwdVaild() {
        isScyPwdVaild = false;
        mScyPwdStart = 0;
        scyPwd = null;
    }

    public static String getScyPwd() {
        return ScyPwd;
    }
}
