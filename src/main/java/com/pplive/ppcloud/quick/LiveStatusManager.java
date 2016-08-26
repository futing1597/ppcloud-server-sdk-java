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
	
	public LiveStatusInfoModel status(String channelWebId, String clientIp) throws URISyntaxException {
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
