package com.navinfo.wecloud.inject.model;

import com.navinfo.wecloud.view.login.RegisterSmsVerView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by liudepeng on 2018-2-26.
 * mail:liudepeng@navinfo.com
 */

@Module
public class RegisterSmsVerModel {
    private final RegisterSmsVerView registerSmsVerView;

    public RegisterSmsVerModel(RegisterSmsVerView registerSmsVerView) {
        this.registerSmsVerView = registerSmsVerView;
    }

    @Provides
    public RegisterSmsVerView provideRegisterSmsVerModel() {
        return registerSmsVerView;
    }
}
