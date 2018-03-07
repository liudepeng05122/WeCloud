package com.navinfo.wecloud.base;

/**
 * Created by liudepeng on 2018-2-25.
 * mail:liudepeng@navinfo.com
 */

public class AppConfig {
    public static final int REGISTER = 0;
    public static final int FORGET_PASSWORD = 1;

    public static final int VEHICLE_COMMAND_UNLOCKING = 5; //开锁
    public static final int VEHICLE_COMMAND_SHUTTING = 6; //闭锁
    public static final int VEHICLE_COMMAND_CLOSE_WINDOW = 101; //关窗
    public static final int VEHICLE_COMMAND_LIGHT = 7; //鸣笛闪灯
    public static final int VEHICLE_COMMAND_OPEN_AIR = 3; //开空调
    public static final int VEHICLE_COMMAND_CLOSE_AIR = 4; //关空调
    public static final int VEHICLE_COMMAND_OPEN_ENGINE = 1; //启动引擎
    public static final int VEHICLE_COMMAND_CLOSE_ENGINE = 2; //关闭引擎

    // tk,服务器端生成的令牌id,经过授权后的用户id
    private String tokenId;

    // 当前登录用户ID,未登录时设置为空
    private String userId;

    // 当前用户选择车辆，未登时录或当前用户无车辆时设置为空
    private String vin;

    private static AppConfig appConfig;

    public static AppConfig getInstance() {
        if(appConfig == null){
            synchronized (AppConfig.class){
                if (appConfig == null){
                    appConfig = new AppConfig();
                }
            }
        }
        return appConfig;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
}
