package com.navinfo.wecloud.view.main;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.baidu.android.pushservice.CustomPushNotificationBuilder;
import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.navinfo.wecloud.R;
import com.navinfo.wecloud.base.AppContext;
import com.navinfo.wecloud.base.util.ClickUtil;
import com.navinfo.wecloud.base.util.log.LogUtils;
import com.navinfo.wecloud.inject.component.DaggerMainComponent;
import com.navinfo.wecloud.inject.model.MainModel;
import com.navinfo.wecloud.presenter.main.MainPresenterImpl;
import com.navinfo.wecloud.view.base.BaseActivity;
import com.navinfo.wecloud.view.login.LoginActivity;
import com.navinfo.wecloud.view.message.MessageActivity;
import com.navinfo.wecloud.view.remotecontrol.RemoteControlActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liudepeng on 2018-2-26.
 * mail:liudepeng@navinfo.com
 */

public class MainActivity extends BaseActivity implements MainView {
    @Inject
    MainPresenterImpl mainPresenter;

    @BindView(R.id.btn_remote_control)
    Button btnRemoteControl;
    @BindView(R.id.btn_message)
    Button btnMessage;

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerMainComponent.builder().mainModel(new MainModel(this)).build().inject(this);

        //开启百度云推送
        if (!AppContext.isPushInit) {
            initPush();
        }

        //检查登入状态
        mainPresenter.checkoutLoginState();
    }

    private void initPush() {
        //绑定push
        PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY, "L9QcWjS05mrOpEQqLTl4Q3Rn");


        //自定义弹出框样式
        CustomPushNotificationBuilder cBuilder = new CustomPushNotificationBuilder(
                R.layout.notification_custom_builder,
                R.id.notification_icon,
                R.id.notification_title,
                R.id.notification_text);

        cBuilder.setNotificationFlags(Notification.FLAG_AUTO_CANCEL);
        cBuilder.setNotificationDefaults(Notification.DEFAULT_VIBRATE);
        cBuilder.setStatusbarIcon(this.getApplicationInfo().icon);
        cBuilder.setLayoutDrawable(R.drawable.ic_launcher_background);
        cBuilder.setNotificationSound(Uri.withAppendedPath(
                MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "6").toString());
        // 若您的应用需要适配Android O（8.x）系统，且将目标版本targetSdkVersion设置为26及以上时：
        // 可自定义channelId/channelName, 若不设置则使用默认值"Push"；
        // 注：非targetSdkVersion 26的应用无需以下2行调用且不会生效
        cBuilder.setChannelId("testId");
        cBuilder.setChannelName("testName");
        // 推送高级设置，通知栏样式设置为下面的ID，ID应与server下发字段notification_builder_id值保持一致
        PushManager.setNotificationBuilder(this, 1, cBuilder);
    }

    @OnClick({R.id.btn_message, R.id.btn_remote_control})
    public void onClick(View view) {
        if (ClickUtil.isFastDoubleClick()) {
            return;
        }
        switch (view.getId()) {
            case R.id.btn_message:
                goMessageActivity();
                break;
            case R.id.btn_remote_control:
                goRemoteControlActivity();
                break;
            default:
        }
    }


    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void checkoutLoginState(boolean loginState) {
        if (!loginState) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {

        }
    }

    @Override
    public void goMessageActivity() {
        Intent intent = new Intent(this, MessageActivity.class);
        startActivity(intent);
    }

    @Override
    public void goRemoteControlActivity() {
        Intent intent = new Intent(this, RemoteControlActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
