package com.navinfo.wecloud.view.login;

import android.content.Context;

/**
 * Created by liudepeng on 2018-2-25.
 * mail:liudepeng@navinfo.com
 */

public interface RegisterSetNewPwdView {
    Context getContext();

    void goMainActivity();

    void dismissProgressDialog();

    void onSetNewPwdStart();

    void onSetNewPwdSuccess();

    void onSetNewPwdError();
}
