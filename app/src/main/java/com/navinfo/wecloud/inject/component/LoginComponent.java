package com.navinfo.wecloud.inject.component;

import com.navinfo.wecloud.inject.model.LoginModel;
import com.navinfo.wecloud.view.login.LoginActivity;

import dagger.Component;

/**
 * Created by liudepeng on 2018-2-25.
 * mail:liudepeng@navinfo.com
 */

@Component(modules = LoginModel.class)
public interface LoginComponent {
    void inject(LoginActivity loginActivity);
}
