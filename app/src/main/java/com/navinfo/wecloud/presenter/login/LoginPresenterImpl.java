package com.navinfo.wecloud.presenter.login;

import com.navinfo.wecloud.model.login.LoginModelImpl;
import com.navinfo.wecloud.view.login.LoginView;

import javax.inject.Inject;

/**
 * Created by liudepeng on 2018-2-25.
 * mail:liudepeng@navinfo.com
 */

public class LoginPresenterImpl implements LoginPresenter {

    private LoginView loginView;
    private LoginModelImpl loginModel;

    @Inject
    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        loginModel = new LoginModelImpl(loginView.getContext());
    }


    @Override
    public void login(String phoneNumber, String password) {
        loginModel.login(phoneNumber, password);
        loginView.dismissProgressDialog();
        loginView.goMainActivity();
    }
}
