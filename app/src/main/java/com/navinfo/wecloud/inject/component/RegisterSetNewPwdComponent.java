package com.navinfo.wecloud.inject.component;

import com.navinfo.wecloud.inject.model.RegisterSetNewPwdModel;
import com.navinfo.wecloud.view.login.RegisterSetNewPwdActivity;

import dagger.Component;

/**
 * Created by liudepeng on 2018-2-26.
 * mail:liudepeng@navinfo.com
 */
@Component(modules = RegisterSetNewPwdModel.class)
public interface RegisterSetNewPwdComponent {
    void inject(RegisterSetNewPwdActivity registerSetNewPwdActivity);
}
