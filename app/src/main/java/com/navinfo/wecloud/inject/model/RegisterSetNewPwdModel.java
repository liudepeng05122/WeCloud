package com.navinfo.wecloud.inject.model;

import com.navinfo.wecloud.view.login.RegisterSetNewPwdView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by liudepeng on 2018-2-26.
 * mail:liudepeng@navinfo.com
 */

@Module
public class RegisterSetNewPwdModel {
    private final RegisterSetNewPwdView registerSetNewPwdView;

    public RegisterSetNewPwdModel(RegisterSetNewPwdView registerSetNewPwdView) {
        this.registerSetNewPwdView = registerSetNewPwdView;
    }

    @Provides
    public RegisterSetNewPwdView provideRegisterSetNewPwdModel() {
        return registerSetNewPwdView;
    }
}
