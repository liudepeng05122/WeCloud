package com.navinfo.wecloud.net.api;

import com.navinfo.network.RxRetrofit;

/**
 * Created by liudepeng on 2018-3-1.
 * mail:liudepeng@navinfo.com
 */

public class Api {
    public static ApiService apiService;

    public static final String BASE_URL = "Http://apicloud.mob.com/appstore/calendar/";


    public static ApiService getDefaultService() {
        if (apiService == null) {
            apiService = RxRetrofit.getRetrofit(BASE_URL).create(ApiService.class);
        }
        return apiService;
    }
}
