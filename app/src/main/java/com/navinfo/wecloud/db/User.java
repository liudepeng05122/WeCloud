package com.navinfo.wecloud.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by liudepeng on 2018-2-27.
 * mail:liudepeng@navinfo.com
 */

@Entity
public class User {
    private String phoneNumber;
    private String password;
    private Long lastLoginTime;
    @Generated(hash = 913948922)
    public User(String phoneNumber, String password, Long lastLoginTime) {
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.lastLoginTime = lastLoginTime;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Long getLastLoginTime() {
        return this.lastLoginTime;
    }
    public void setLastLoginTime(Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
