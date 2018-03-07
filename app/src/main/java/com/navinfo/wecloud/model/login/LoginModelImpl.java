package com.navinfo.wecloud.model.login;

import android.content.Context;

import com.navinfo.wecloud.db.DaoManager;
import com.navinfo.wecloud.db.User;

/**
 * Created by liudepeng on 2018-2-25.
 * mail:liudepeng@navinfo.com
 */

public class LoginModelImpl implements LoginModel {
    private Context mContext;

    public LoginModelImpl(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void login(String phoneNumber, String password) {
        DaoManager.getInstance().insertUser(new User(phoneNumber, password, System.currentTimeMillis()));
    }
}
