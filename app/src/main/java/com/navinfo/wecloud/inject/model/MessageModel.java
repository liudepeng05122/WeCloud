package com.navinfo.wecloud.inject.model;

import com.navinfo.wecloud.view.message.MessageView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by liudepeng on 2018-2-27.
 * mail:liudepeng@navinfo.com
 */

@Module
public class MessageModel {
    private final MessageView messageView;

    public MessageModel(MessageView messageView) {
        this.messageView = messageView;
    }

    @Provides
    public MessageView provideMessageModel() {
        return messageView;
    }
}
