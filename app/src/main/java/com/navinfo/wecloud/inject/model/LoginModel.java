package com.navinfo.wecloud.inject.model;

import com.navinfo.wecloud.view.login.LoginView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by liudepeng on 2018-2-25.
 * mail:liudepeng@navinfo.com
 */

@Module
public class LoginModel {
    private final LoginView loginView;

    public LoginModel(LoginView loginView) {
        this.loginView = loginView;
    }

    @Provides
    public LoginView provideLoginModel() {
        return loginView;
    }
}
