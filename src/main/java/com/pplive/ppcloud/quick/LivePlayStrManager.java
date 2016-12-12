/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */

package com.pplive.ppcloud.quick;

import com.pplive.ppcloud.live.LiveManager;
import com.pplive.ppcloud.quick.model.LivePreviewInfoModel;
import com.pplive.ppcloud.request.LiveWatchRequest;

/**
 * @author chaogao
 *
 */
public class LivePlayStrManager {

    private LivePlayStrManager() {}

    public static LivePlayStrManager getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static LivePlayStrManager instance = new LivePlayStrManager();
    }

    /**
     * 获取播放地址
     * 通过 channelWebId
     * @param channelWebId 播放串
     * @return 预览地址
     */
    public LivePreviewInfoModel getPlayStr(String channelWebId, String clientIp) {
        LiveWatchRequest lWatchRequest = new LiveWatchRequest();
        lWatchRequest.setChannelWebId(channelWebId);
        lWatchRequest.setClientIp(clientIp);
        return getPlayStr(lWatchRequest);
    }

    /**
     * 获取播放地址
     * 通过 channelWebId
     * @param channelWebId 播放串
     * @param expireInMinutes 过期时长
     * @return 预览地址
     */
    public LivePreviewInfoModel getPlayStr(String channelWebId, String clientIp, Integer expireInMinutes) {
        LiveWatchRequest lWatchRequest = new LiveWatchRequest();
        lWatchRequest.setChannelWebId(channelWebId);
        lWatchRequest.setClientIp(clientIp);
        lWatchRequest.setExpireInMinutes(expireInMinutes);
        return getPlayStr(lWatchRequest);
    }

    /**
     * 获取播放地址
     * @param lWatchRequest 请求参数
     * @return 预览地址
     */
    public LivePreviewInfoModel getPlayStr(LiveWatchRequest lWatchRequest) {
        //play url
        LivePreviewInfoModel livePreviewInfoModel = LiveManager.getInstance().getPlayStr(lWatchRequest);
        return livePreviewInfoModel;
    }
}
