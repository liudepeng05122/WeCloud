package com.navinfo.wecloud.inject.model;

import com.navinfo.wecloud.view.main.MainView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by liudepeng on 2018-2-26.
 * mail:liudepeng@navinfo.com
 */

@Module
public class MainModel {
    private final MainView mainView;

    public MainModel(MainView mainView) {
        this.mainView = mainView;
    }

    @Provides
    public MainView provideMainView() {
        return mainView;
    }
}
