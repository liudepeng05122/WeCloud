package com.navinfo.wecloud.view.login;

import android.content.Context;

/**
 * Created by liudepeng on 2018-2-25.
 * mail:liudepeng@navinfo.com
 */

public interface LoginView {
    Context getContext();

    void login();

    void goForgetPassword();

    void goRegister();

    void goMainActivity();

    void dismissProgressDialog();
}
