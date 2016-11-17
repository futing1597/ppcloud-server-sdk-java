/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */

package com.pplive.ppcloud.quick;

import com.pplive.ppcloud.live.LiveManager;
import com.pplive.ppcloud.request.LiveStatusControlRequest;
import com.pplive.ppcloud.response.BaseResponse;

/**
 * Created by chaogao on 2016/11/15.
 */
public class LiveStatusControllManager {

    private LiveStatusControllManager() {}

    public static LiveStatusControllManager getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static LiveStatusControllManager instance = new LiveStatusControllManager();
    }

    /**
     * 直播状态变更
     * @param channelWebId 直播播放串
     * status  直播状态:notstart,init,preview,living,pause,broken,stopped,deleted,sysdeleted
     */
    public void statusChange(String channelWebId, String status) {
        LiveStatusControlRequest lStatusControlRequest = new LiveStatusControlRequest();
        lStatusControlRequest.setChannelWebId(channelWebId);
        lStatusControlRequest.setLiveStatus(status);

        BaseResponse lsResponse = LiveManager.getInstance().statusControll(lStatusControlRequest);

        if (null == lsResponse || !"0".equals(lsResponse.getErr())) {
            throw new RuntimeException("live status change error");
        }
    }
}
