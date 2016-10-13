/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */
package com.pplive.ppcloud.quick;

import java.net.URISyntaxException;

import com.pplive.ppcloud.LiveStatus;
import com.pplive.ppcloud.live.LiveManager;
import com.pplive.ppcloud.request.LiveStatusControlRequest;
import com.pplive.ppcloud.response.BaseResponse;

/**
 * @author chaogao
 *
 */
public class LiveStopManager {
	
	private LiveStopManager() {}
	
	public static LiveStopManager getInstance() {
		return SingletonHolder.instance;
	}
	
	private static class SingletonHolder {
		private static LiveStopManager instance = new LiveStopManager();
	}
	
	/**
	 * 停止直播
	 * @param channelWebId 直播播放串
	 */
	public void stop(String channelWebId) {
		//stop
		LiveStatusControlRequest lStatusControlRequest = new LiveStatusControlRequest();
		lStatusControlRequest.setChannelWebId(channelWebId);
		lStatusControlRequest.setLiveStatus(LiveStatus.STOPPED.toString());
		
		BaseResponse lsResponse = LiveManager.getInstance().statusControll(lStatusControlRequest);
		
		if (null == lsResponse || !"0".equals(lsResponse.getErr())) {
			throw new RuntimeException("set live to stop error");
		}
	}
}
