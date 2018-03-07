package com.navinfo.wecloud.inject.component;

import com.navinfo.wecloud.inject.model.MessageModel;
import com.navinfo.wecloud.view.message.MessageActivity;

import dagger.Component;

/**
 * Created by liudepeng on 2018-2-27.
 * mail:liudepeng@navinfo.com
 */

@Component(modules = MessageModel.class)
public interface MessageComponent {
    void inject(MessageActivity messageActivity);
}
