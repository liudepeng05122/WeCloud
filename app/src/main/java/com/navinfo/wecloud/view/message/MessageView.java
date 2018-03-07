package com.navinfo.wecloud.view.message;

import com.navinfo.wecloud.db.Message;

import java.util.List;

/**
 * Created by liudepeng on 2018-2-27.
 * mail:liudepeng@navinfo.com
 */

public interface MessageView {
    void setMessageList(List<Message> messageList);

    void goMainActivity();
}
