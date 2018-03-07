package com.navinfo.wecloud.inject.component;

import com.navinfo.wecloud.inject.model.RemoteControlModel;
import com.navinfo.wecloud.view.remotecontrol.RemoteControlActivity;

import dagger.Component;

/**
 * Created by liudepeng on 2018-3-7.
 * mail:liudepeng@navinfo.com
 */

@Component(modules = RemoteControlModel.class)
public interface RemoteControlComponent {
    void inject(RemoteControlActivity remoteControlActivity);
}
