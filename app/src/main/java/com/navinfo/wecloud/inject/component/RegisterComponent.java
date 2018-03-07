package com.navinfo.wecloud.inject.component;

import com.navinfo.wecloud.inject.model.RegisterModel;
import com.navinfo.wecloud.view.login.RegisterActivity;

import dagger.Component;

/**
 * Created by liudepeng on 2018-2-25.
 * mail:liudepeng@navinfo.com
 */

@Component(modules = RegisterModel.class)
public interface RegisterComponent {
    void inject(RegisterActivity registerActivity);
}
