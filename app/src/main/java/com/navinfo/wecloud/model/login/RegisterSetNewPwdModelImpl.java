package com.navinfo.wecloud.model.login;

import android.content.Context;

import com.navinfo.wecloud.db.DaoManager;
import com.navinfo.wecloud.db.User;

import com.navinfo.wecloud.net.bean.RegisterUserResponse;

import io.reactivex.Observable;

/**
 * Created by liudepeng on 2018-2-26.
 * mail:liudepeng@navinfo.com
 */

public class RegisterSetNewPwdModelImpl implements RegisterSetNewPwdModel {
    private Context mContext;

    public RegisterSetNewPwdModelImpl(Context mContext) {
        this.mContext = mContext;
    }

//    @Override
//    public void setNewPwd(String phoneNumber, String password, int type) {
//        DaoManager.getInstance().insertUser(new User(phoneNumber, password, System.currentTimeMillis()));
//
//    }

    @Override
    public Observable<RegisterUserResponse> setNewPwd(String phoneNumber, String password, int type) {

        DaoManager.getInstance().insertUser(new User(phoneNumber, password, System.currentTimeMillis()));

//        return Api.getDefaultService().register(new RegisterUserBean(phoneNumber, SecurityUtils.md5(password), type))
//                .map(new RxFunction<RegisterUserResultBean>())
//                .compose(RxSchedulers.<RegisterUserResultBean>io_main());
        return null;
    }
}
