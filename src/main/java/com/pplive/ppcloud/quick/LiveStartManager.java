/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */
package com.pplive.ppcloud.quick;

import com.pplive.ppcloud.LiveStatus;
import com.pplive.ppcloud.live.LiveManager;
import com.pplive.ppcloud.quick.model.LivePreviewInfoModel;
import com.pplive.ppcloud.request.LiveStatusControlRequest;
import com.pplive.ppcloud.response.BaseResponse;

/**
 * @author chaogao
 *
 */
public class LiveStartManager {
	
	private LiveStartManager() {}
	
	public static LiveStartManager getInstance() {
		return SingletonHolder.instance;
	}
	
	private static class SingletonHolder {
		private static LiveStartManager instance = new LiveStartManager();
	}
	
	/**
	 * 开始直播
	 * 包含 预览,直播中,获取预览地址
	 * @return 预览地址
	 */
	public LivePreviewInfoModel start(String channelWebId) {
		LivePreviewInfoModel lPreviewInfoModel = new LivePreviewInfoModel();

		//1.living
		LiveStatusControlRequest lStatusControlRequest2 = new LiveStatusControlRequest();
		lStatusControlRequest2.setChannelWebId(channelWebId);
		lStatusControlRequest2.setLiveStatus(LiveStatus.LIVING.toString());
		
		BaseResponse lsResponse2 = LiveManager.getInstance().statusControll(lStatusControlRequest2);
		
		if (null == lsResponse2 || !"0".equals(lsResponse2.getErr())) {
			lPreviewInfoModel.setErr(lsResponse2.getErr());
			lPreviewInfoModel.setMsg(lsResponse2.getMsg());
			return lPreviewInfoModel;
		}
		
		return lPreviewInfoModel;
	}
}
