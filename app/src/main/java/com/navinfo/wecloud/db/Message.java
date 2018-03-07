package com.navinfo.wecloud.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by liudepeng on 2018-2-27.
 * mail:liudepeng@navinfo.com
 */

@Entity
public class Message {

    private int type;
    private String title;
    private String description;
    private String customContentString;
    private Long time;
    @Generated(hash = 272768694)
    public Message(int type, String title, String description,
            String customContentString, Long time) {
        this.type = type;
        this.title = title;
        this.description = description;
        this.customContentString = customContentString;
        this.time = time;
    }
    @Generated(hash = 637306882)
    public Message() {
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getCustomContentString() {
        return this.customContentString;
    }
    public void setCustomContentString(String customContentString) {
        this.customContentString = customContentString;
    }
    public Long getTime() {
        return this.time;
    }
    public void setTime(Long time) {
        this.time = time;
    }

}
