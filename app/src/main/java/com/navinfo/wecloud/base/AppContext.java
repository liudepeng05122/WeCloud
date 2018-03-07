package com.navinfo.wecloud.base;

import android.app.Application;
import android.text.TextUtils;

import com.navinfo.wecloud.base.util.ShareUtil;
import com.navinfo.wecloud.db.DaoManager;

/**
 * Created by liudepeng on 2018-2-25.
 * mail:liudepeng@navinfo.com
 */

public class AppContext extends Application {
    public static boolean isPushInit = false;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoManager.getInstance().init(this);
    }
}
