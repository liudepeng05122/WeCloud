package com.navinfo.wecloud.net.api;

import com.navinfo.network.HttpResult;
import com.navinfo.wecloud.net.bean.LoginRequest;
import com.navinfo.wecloud.net.bean.LoginResponse;
import com.navinfo.wecloud.net.bean.RegisterUserRequest;
import com.navinfo.wecloud.net.bean.RegisterUserResponse;
import com.navinfo.wecloud.net.bean.RemoteControlRequest;
import com.navinfo.wecloud.net.bean.RemoteControlResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by liudepeng on 2018-3-1.
 * mail:liudepeng@navinfo.com
 */

public interface ApiService {

    @POST("register")
    Observable<HttpResult<RegisterUserResponse>> register(@Body RegisterUserRequest registerUserRequest);

    @POST("login")
    Observable<HttpResult<LoginResponse>> login(@Body LoginRequest loginRequest);

    @POST("/api/vehicle/rc/lock/")
    Observable<HttpResult<RemoteControlResponse>> lock(@Body RemoteControlRequest remoteControlRequest);

    @POST("/api/vehicle/rc/unlock/")
    Observable<HttpResult<RemoteControlResponse>> unlock(@Body RemoteControlRequest remoteControlRequest);

    @POST("/api/vehicle/rc/deepFlash/")
    Observable<HttpResult<RemoteControlResponse>> deepFlash(@Body RemoteControlRequest remoteControlRequest);

    @POST("/api/vehicle/rc/startAirCon/")
    Observable<HttpResult<RemoteControlResponse>> startAirCon(@Body RemoteControlRequest remoteControlRequest);

    @POST("/api/vehicle/rc/stopAirCon/")
    Observable<HttpResult<RemoteControlResponse>> stopAirCon(@Body RemoteControlRequest remoteControlRequest);

    @POST("/api/vehicle/rc/startEngine/")
    Observable<HttpResult<RemoteControlResponse>> startEngine(@Body RemoteControlRequest remoteControlRequest);

    @POST("/api/vehicle/rc/stopEngine/")
    Observable<HttpResult<RemoteControlResponse>> stopEngine(@Body RemoteControlRequest remoteControlRequest);

    @POST("/api/vehicle/rc/freshVCondition/")
    Observable<HttpResult<RemoteControlResponse>> freshVCondition(@Body RemoteControlRequest remoteControlRequest);
}
