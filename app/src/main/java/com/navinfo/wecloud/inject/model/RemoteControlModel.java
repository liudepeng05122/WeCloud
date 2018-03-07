package com.navinfo.wecloud.inject.model;

import com.navinfo.wecloud.view.remotecontrol.RemoteControlView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by liudepeng on 2018-3-7.
 * mail:liudepeng@navinfo.com
 */
@Module
public class RemoteControlModel {
    private final RemoteControlView remoteControlView;

    public RemoteControlModel(RemoteControlView remoteControlView) {
        this.remoteControlView = remoteControlView;
    }

    @Provides
    public RemoteControlView provideRemoteControlModel() {
        return remoteControlView;
    }
}
