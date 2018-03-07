package com.navinfo.wecloud.presenter.login;

import com.navinfo.wecloud.model.login.RegisterSmsVerModelImpl;
import com.navinfo.wecloud.view.login.RegisterSmsVerView;

import javax.inject.Inject;

/**
 * Created by liudepeng on 2018-2-26.
 * mail:liudepeng@navinfo.com
 */

public class RegisterSmsVerPresenterImpl implements RegisterSmsVerPresenter {

    private RegisterSmsVerView registerSmsVerView;
    private RegisterSmsVerModelImpl registerSmsVerModel;

    @Inject
    public RegisterSmsVerPresenterImpl(RegisterSmsVerView registerSmsVerView) {
        this.registerSmsVerView = registerSmsVerView;
        registerSmsVerModel = new RegisterSmsVerModelImpl();
    }

    @Override
    public void smsVerify(String phoneNumber, String verifyCode, int type) {
        registerSmsVerModel.smsVerify(phoneNumber, verifyCode, type);
        registerSmsVerView.goRegisterSetNewPwd();
    }
}
