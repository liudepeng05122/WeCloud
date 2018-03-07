package com.navinfo.wecloud.model.main;

import android.content.Context;

import com.navinfo.wecloud.base.util.log.LogUtils;
import com.navinfo.wecloud.db.DaoManager;
import com.navinfo.wecloud.db.User;

/**
 * Created by liudepeng on 2018-2-26.
 * mail:liudepeng@navinfo.com
 */

public class MainModelImpl implements MainModel {
    private Context mContext;
    private User user;

    public MainModelImpl(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public boolean getLoginState() {
        user = DaoManager.getInstance().readUser();

        if (user == null) {
            return false;
        } else {
//            if (user.get(0).getLastLoginTime())
            LogUtils.d("LDP", "phoneNumber = " + user.getPhoneNumber());
            LogUtils.d("LDP", "password = " + user.getPassword());
            LogUtils.d("LDP", "lastLoginTime = " + user.getLastLoginTime());
            return true;
        }
    }
}
