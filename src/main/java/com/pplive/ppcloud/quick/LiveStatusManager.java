/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */
package com.pplive.ppcloud.quick;

import java.net.URISyntaxException;

import com.pplive.ppcloud.live.LiveManager;
import com.pplive.ppcloud.quick.model.LiveStatusInfoModel;
import com.pplive.ppcloud.request.LiveStatusRequest;
import com.pplive.ppcloud.response.LiveStatusResponse;

/**
 * @author chaogao
 *
 */
public class LiveStatusManager {
	private LiveStatusManager() {}
	
	public static LiveStatusManager getInstance() {
		return SingletonHolder.instance;
	}
	
	private static class SingletonHolder {
		private static LiveStatusManager instance = new LiveStatusManager();
	}

	/**
	 * 获取直播状态
	 * 需要定时获取以判断当前状态是否正常
	 * @param channelWebId 直播播放串
	 * @param clientIp 客户端Ip
	 * @return 直播状态
	 */
	public LiveStatusInfoModel status(String channelWebId, String clientIp) {
		LiveStatusInfoModel lStatusInfoModel = new LiveStatusInfoModel();
		//live status
		LiveStatusRequest lStatusRequest = new LiveStatusRequest();
		lStatusRequest.setChannelWebId(channelWebId);
		lStatusRequest.setClientIp(clientIp);
		
		LiveStatusResponse lStatusResponse = LiveManager.getInstance().status(lStatusRequest);
		
		if (null == lStatusResponse || !"0".equals(lStatusResponse.getErr())) {
			lStatusInfoModel.setErr(lStatusResponse.getErr());
			lStatusInfoModel.setMsg(lStatusResponse.getMsg());
			return lStatusInfoModel;
		}
		
		lStatusInfoModel.setChannelWebId(channelWebId);
		lStatusInfoModel.setLiveStatus(lStatusResponse.getLiveStatus());
		lStatusInfoModel.setStreamStatus(lStatusResponse.getStreamStatus());
		lStatusInfoModel.setDelay(lStatusResponse.getDelay());
		
		return lStatusInfoModel;
	}
}
