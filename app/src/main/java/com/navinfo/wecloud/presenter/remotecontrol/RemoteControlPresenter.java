package com.navinfo.wecloud.presenter.remotecontrol;

/**
 * Created by liudepeng on 2018-3-7.
 * mail:liudepeng@navinfo.com
 */

public interface RemoteControlPresenter {
    void sendCommand(int cmdCode, int flag, String scyPwd);
}
