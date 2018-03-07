package com.navinfo.wecloud.model.message;

import com.navinfo.wecloud.db.DaoManager;
import com.navinfo.wecloud.db.Message;

import java.util.List;

/**
 * Created by liudepeng on 2018-2-27.
 * mail:liudepeng@navinfo.com
 */

public class MessageModelImpl implements MessageModel {
    @Override
    public List<Message> loadPushMessage() {
        List<Message> messageList = DaoManager.getInstance().readMessage();

        return DaoManager.getInstance().readMessage();
    }
}
