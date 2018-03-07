package com.navinfo.wecloud.inject.component;

import com.navinfo.wecloud.inject.model.RegisterSmsVerModel;
import com.navinfo.wecloud.view.login.RegisterSmsVerActivity;

import dagger.Component;

/**
 * Created by liudepeng on 2018-2-26.
 * mail:liudepeng@navinfo.com
 */

@Component(modules = RegisterSmsVerModel.class)
public interface RegisterSmsVerComponent {
    void inject(RegisterSmsVerActivity registerSmsVerActivity);
}
