package com.navinfo.wecloud.view.main;

import android.content.Context;

/**
 * Created by liudepeng on 2018-2-26.
 * mail:liudepeng@navinfo.com
 */

public interface MainView {
    Context getContext();

    void checkoutLoginState(boolean loginState);

    void goMessageActivity();

    void goRemoteControlActivity();
}
