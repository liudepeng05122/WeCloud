package com.navinfo.wecloud.presenter.remotecontrol;

import com.navinfo.wecloud.model.remotecontrol.RemoteControlModelImpl;
import com.navinfo.wecloud.view.remotecontrol.RemoteControlView;

import javax.inject.Inject;

import static com.navinfo.wecloud.base.AppConfig.VEHICLE_COMMAND_CLOSE_AIR;
import static com.navinfo.wecloud.base.AppConfig.VEHICLE_COMMAND_CLOSE_ENGINE;
import static com.navinfo.wecloud.base.AppConfig.VEHICLE_COMMAND_LIGHT;
import static com.navinfo.wecloud.base.AppConfig.VEHICLE_COMMAND_OPEN_AIR;
import static com.navinfo.wecloud.base.AppConfig.VEHICLE_COMMAND_OPEN_ENGINE;
import static com.navinfo.wecloud.base.AppConfig.VEHICLE_COMMAND_SHUTTING;
import static com.navinfo.wecloud.base.AppConfig.VEHICLE_COMMAND_UNLOCKING;

/**
 * Created by liudepeng on 2018-3-7.
 * mail:liudepeng@navinfo.com
 */

public class RemoteControlPresenterImpl implements RemoteControlPresenter {

    private RemoteControlView remoteControlView;
    private RemoteControlModelImpl remoteControlModel;


    @Inject
    public RemoteControlPresenterImpl(RemoteControlView remoteControlView) {
        this.remoteControlView = remoteControlView;
        remoteControlModel = new RemoteControlModelImpl();
    }

    @Override
    public void sendCommand(int cmdCode, int flag, String scyPwd) {
        switch (cmdCode) {
            case VEHICLE_COMMAND_OPEN_ENGINE:
                break;
            case VEHICLE_COMMAND_CLOSE_ENGINE:
                break;
            case VEHICLE_COMMAND_OPEN_AIR:
                break;
            case VEHICLE_COMMAND_CLOSE_AIR:
                break;
            case VEHICLE_COMMAND_UNLOCKING:
                break;
            case VEHICLE_COMMAND_SHUTTING:
                break;
            case VEHICLE_COMMAND_LIGHT:
                break;
        }
    }
}
