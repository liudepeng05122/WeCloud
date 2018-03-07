package com.navinfo.wecloud.presenter.login;

import com.navinfo.wecloud.model.login.RegisterModelImpl;
import com.navinfo.wecloud.view.login.RegisterView;

import javax.inject.Inject;

/**
 * Created by liudepeng on 2018-2-25.
 * mail:liudepeng@navinfo.com
 */

public class RegisterPresenterImpl implements RegisterPresenter {
    private RegisterView registerView;
    private RegisterModelImpl registerModel;

    @Inject
    public RegisterPresenterImpl(RegisterView registerView) {
        this.registerView = registerView;
        registerModel = new RegisterModelImpl();
    }

    @Override
    public void phoneNumberCommit(String phoneNumber, int type) {
        registerModel.postSMS(phoneNumber, type);
        registerView.dismissProgressDialog();
        registerView.goRegisterSmsVerActivity();
    }

}
