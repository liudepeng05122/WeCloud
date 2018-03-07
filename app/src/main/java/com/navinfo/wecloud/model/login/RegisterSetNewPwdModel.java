package com.navinfo.wecloud.model.login;

import com.navinfo.wecloud.net.bean.RegisterUserResponse;

import io.reactivex.Observable;

/**
 * Created by liudepeng on 2018-2-26.
 * mail:liudepeng@navinfo.com
 */

public interface RegisterSetNewPwdModel {
//    void setNewPwd(String phoneNumber, String password, int type);

    Observable<RegisterUserResponse> setNewPwd(String phoneNumber, String password, int type);
}
