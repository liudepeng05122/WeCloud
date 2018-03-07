package com.navinfo.wecloud.net.bean;

/**
 * Created by liudepeng on 2018-3-7.
 * mail:liudepeng@navinfo.com
 */

public class RemoteControlRequest {
    // 车架号
    private String vin;

    // 指纹标志位，0：使用安防密码验证；1：使用指纹验证；当为0时，scyPwd必填，当为1时，signStr必填。
    private int flag;

    // 安防密码，使用MD5加密后的字符串
    private String scyPwd;

    // 指纹密码字符串，vin号+session，使用MD5加密字符串
    private String signStr;

    //用户Id
    private String userId;

    // 引擎、空调启动持续时间，单位：分钟。引擎默认启动时间为30分钟。空调运行时间取值范围为1-30
    private int duration;

    // 设置空调温度，单位：摄氏度。取值范围为17-31
    private int temperature;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getScyPwd() {
        return scyPwd;
    }

    public void setScyPwd(String scyPwd) {
        this.scyPwd = scyPwd;
    }

    public String getSignStr() {
        return signStr;
    }

    public void setSignStr(String signStr) {
        this.signStr = signStr;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}
