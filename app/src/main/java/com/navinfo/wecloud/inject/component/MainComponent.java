package com.navinfo.wecloud.inject.component;

import com.navinfo.wecloud.inject.model.MainModel;
import com.navinfo.wecloud.view.main.MainActivity;

import dagger.Component;

/**
 * Created by liudepeng on 2018-2-26.
 * mail:liudepeng@navinfo.com
 */

@Component(modules = MainModel.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
