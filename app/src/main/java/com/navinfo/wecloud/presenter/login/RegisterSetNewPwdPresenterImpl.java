package com.navinfo.wecloud.presenter.login;

import com.navinfo.wecloud.model.login.RegisterSetNewPwdModelImpl;
import com.navinfo.wecloud.view.login.RegisterSetNewPwdView;

import javax.inject.Inject;

/**
 * Created by liudepeng on 2018-2-26.
 * mail:liudepeng@navinfo.com
 */

public class RegisterSetNewPwdPresenterImpl implements RegisterSetNewPwdPresenter {
    private RegisterSetNewPwdView registerSetNewPwdView;
    private RegisterSetNewPwdModelImpl registerSetNewPwdModel;

    @Inject
    public RegisterSetNewPwdPresenterImpl(RegisterSetNewPwdView registerSetNewPwdView) {
        this.registerSetNewPwdView = registerSetNewPwdView;
        registerSetNewPwdModel = new RegisterSetNewPwdModelImpl(registerSetNewPwdView.getContext());
    }

    @Override
    public void setNewPwd(String phoneNumber, String password, int type, String key, int whichRequest, boolean isShowDialog) {
//        registerSetNewPwdModel.setNewPwd(phoneNumber, password, type)
//                .subscribe(new RxObserver<RegisterUserResultBean>(registerSetNewPwdView.getContext(), key, whichRequest, isShowDialog) {
//                    @Override
//                    public void onStart(int whichRequest) {
//                        LogUtils.d("ldp", "onStart");
//                        super.onStart(whichRequest);
//                        registerSetNewPwdView.onSetNewPwdStart();
//                    }
//
//                    @Override
//                    public void onSuccess(int whichRequest, RegisterUserResultBean registerUserResultBean) {
//                        LogUtils.d("ldp", "onSuccess");
//                        registerSetNewPwdView.onSetNewPwdSuccess();
//                    }
//
//                    @Override
//                    public void onError(int whichRequest, Throwable e) {
//                        LogUtils.d("ldp", e.toString());
//                        registerSetNewPwdView.onSetNewPwdError();
//                    }
//                });
        registerSetNewPwdModel.setNewPwd(phoneNumber, password, type);
        registerSetNewPwdView.onSetNewPwdSuccess();
    }
}
