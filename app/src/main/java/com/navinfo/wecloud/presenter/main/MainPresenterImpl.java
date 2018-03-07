package com.navinfo.wecloud.presenter.main;

import com.navinfo.wecloud.model.main.MainModelImpl;
import com.navinfo.wecloud.view.main.MainView;

import javax.inject.Inject;

/**
 * Created by liudepeng on 2018-2-26.
 * mail:liudepeng@navinfo.com
 */

public class MainPresenterImpl implements MainPresenter {
    private MainView mainView;
    private MainModelImpl mainModel;

    @Inject
    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        mainModel = new MainModelImpl(mainView.getContext());
    }

    @Override
    public void checkoutLoginState() {
        mainView.checkoutLoginState(mainModel.getLoginState());
    }
}
