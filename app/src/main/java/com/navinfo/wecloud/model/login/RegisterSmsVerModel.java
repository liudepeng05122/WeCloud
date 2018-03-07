package com.navinfo.wecloud.model.login;

/**
 * Created by liudepeng on 2018-2-26.
 * mail:liudepeng@navinfo.com
 */

public interface RegisterSmsVerModel {
    void smsVerify(String phoneNumber, String verifyCode, int type);

    void postSMS(String phoneNumber, int type);
}
