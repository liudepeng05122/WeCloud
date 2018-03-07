package com.navinfo.wecloud.presenter.login;

/**
 * Created by liudepeng on 2018-2-26.
 * mail:liudepeng@navinfo.com
 */

public interface RegisterSmsVerPresenter {
    void smsVerify(String phoneNumber, String verifyCode, int type);
}
