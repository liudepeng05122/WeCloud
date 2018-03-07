package com.navinfo.wecloud.inject.model;

import com.navinfo.wecloud.view.login.RegisterView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by liudepeng on 2018-2-25.
 * mail:liudepeng@navinfo.com
 */
@Module
public class RegisterModel {
    private final RegisterView registerView;

    public RegisterModel(RegisterView registerView) {
        this.registerView = registerView;
    }

    @Provides
    public RegisterView provideRegisterModel() {
        return registerView;
    }
}
