package com.navinfo.wecloud.presenter.login;

/**
 * Created by liudepeng on 2018-2-26.
 * mail:liudepeng@navinfo.com
 */

public interface RegisterSetNewPwdPresenter {
    void setNewPwd(String phoneNumber, String password, int type, String key, int whichRequest, boolean isShowDialog);
}
