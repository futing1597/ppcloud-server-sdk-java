/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */

package com.pplive.ppcloud;

import com.pplive.ppcloud.auth.AccessTokenSigner;
import com.pplive.ppcloud.auth.AuthCredentials;
import com.pplive.ppcloud.quick.*;
import com.pplive.ppcloud.quick.model.LivePreviewInfoModel;
import com.pplive.ppcloud.quick.model.LivePushInfoModel;
import com.pplive.ppcloud.quick.model.LiveStatusInfoModel;
import com.pplive.ppcloud.request.LiveWatchRequest;

/**
 * @author yancongliu
 */
public class LiveClient {

    private LiveClient() {
    }

    /**
     * 初始化客户端,设置accessKey，secretKey
     * @param accessKey accessKey
     * @param secretKey secretKey
     */
    public LiveClient(String accessKey, String secretKey) {
        AuthCredentials authCredentials = new AuthCredentials();
        authCredentials.setAccessKey(accessKey);
        authCredentials.setSecretKey(secretKey);
        AccessTokenSigner.getInstance().setAuthCredentials(authCredentials);
    }

    /**
     * 创建一个直播
     * 包含 创建,初始化,获取推流地址
     *
     * @param title 直播标题
     * @param mode  直播方式
     * @return 推流地址
     */
    public LivePushInfoModel create(String title, String mode, String clientIp) {
        return LiveCreateManager.getInstance().create(title, mode, clientIp);
    }


    /**
     * 开始直播
     * 包含 预览,直播中,获取预览地址
     *
     * @param channelWebId 直播播放串
     * @return 预览地址
     */
    public LivePreviewInfoModel start(String channelWebId) {
        return LiveStartManager.getInstance().start(channelWebId);
    }


    /**
     * 获取直播播放地址
     * 通过 channelWebId
     *
     * @param channelWebId 直播播放串
     * @return 预览地址
     */
    public LivePreviewInfoModel watch(String channelWebId, String clientIp) {
        return LiveWatchManager.getInstance().watch(channelWebId, clientIp);
    }

    /**
     * 获取直播播放地址
     *
     * @param lWatchRequest 请求参数
     * @return 预览地址
     */
    public LivePreviewInfoModel watch(LiveWatchRequest lWatchRequest) {
        return LiveWatchManager.getInstance().watch(lWatchRequest);
    }

    /**
     * 获取直播状态
     * 需要定时获取以判断当前状态是否正常
     *
     * @param channelWebId 直播播放串
     * @param clientIp     客户端Ip
     * @return 直播状态
     */
    public LiveStatusInfoModel status(String channelWebId, String clientIp) {
        return LiveStatusManager.getInstance().status(channelWebId, clientIp);
    }

    /**
     * 停止直播
     *
     * @param channelWebId 直播播放串
     */
    public void stop(String channelWebId) {
        LiveStopManager.getInstance().stop(channelWebId);
    }
}
