package com.navinfo.wecloud.presenter.message;

import com.navinfo.wecloud.base.util.log.LogUtils;
import com.navinfo.wecloud.model.message.MessageModelImpl;
import com.navinfo.wecloud.view.message.MessageView;

import javax.inject.Inject;

/**
 * Created by liudepeng on 2018-2-27.
 * mail:liudepeng@navinfo.com
 */

public class MessagePresenterImpl implements MessagePresenter {
    private MessageView messageView;
    private MessageModelImpl messageModel;

    @Inject
    public MessagePresenterImpl(MessageView messageView) {
        this.messageView = messageView;
        messageModel = new MessageModelImpl();
    }

    @Override
    public void loadPushMessage() {
        messageView.setMessageList(messageModel.loadPushMessage());
    }
}
