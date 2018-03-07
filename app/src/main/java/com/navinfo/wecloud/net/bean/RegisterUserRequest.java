package com.navinfo.wecloud.net.bean;

/**
 * Created by liudepeng on 2018-3-1.
 * mail:liudepeng@navinfo.com
 */

public class RegisterUserRequest {
    // 登录帐号
    private String account;
    // 登录密码，使用MD5加密后的字符串
    private String pwd;
    // 处理类型，1：注册；2：重置密码。
    private int dealType;

    public RegisterUserRequest(String account, String pwd, int dealType) {
        this.account = account;
        this.pwd = pwd;
        this.dealType = dealType;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getDealType() {
        return dealType;
    }

    public void setDealType(int dealType) {
        this.dealType = dealType;
    }
}
