/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */
package com.pplive.ppcloud.quick;

import java.net.URISyntaxException;

import com.pplive.ppcloud.LiveProtocol;
import com.pplive.ppcloud.LiveStatus;
import com.pplive.ppcloud.live.LiveManager;
import com.pplive.ppcloud.quick.model.LivePreviewInfoModel;
import com.pplive.ppcloud.request.LiveStatusControlRequest;
import com.pplive.ppcloud.request.LiveWatchRequest;
import com.pplive.ppcloud.response.BaseResponse;
import com.pplive.ppcloud.response.LiveWatchMediaResponse;
import com.pplive.ppcloud.response.LiveWatchResponse;

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
	 * @param channelWebId 直播播放串
	 * @return 预览地址
	 * @throws URISyntaxException 异常
	 */
	public LivePreviewInfoModel start(String channelWebId, String clientIp) throws URISyntaxException {
		LivePreviewInfoModel lPreviewInfoModel = new LivePreviewInfoModel();
		//1.preview
		LiveStatusControlRequest lStatusControlRequest = new LiveStatusControlRequest();
		lStatusControlRequest.setChannelWebId(channelWebId);
		lStatusControlRequest.setLiveStatus(LiveStatus.PREVIEW.toString());
		
		BaseResponse lsResponse = LiveManager.getInstance().statusControll(lStatusControlRequest);
		
		if (null == lsResponse || !"0".equals(lsResponse.getErr())) {
			lPreviewInfoModel.setErr(lsResponse.getErr());
			lPreviewInfoModel.setMsg(lsResponse.getMsg());
			return lPreviewInfoModel;
		}
		//2.living
		LiveStatusControlRequest lStatusControlRequest2 = new LiveStatusControlRequest();
		lStatusControlRequest2.setChannelWebId(channelWebId);
		lStatusControlRequest2.setLiveStatus(LiveStatus.LIVING.toString());
		
		BaseResponse lsResponse2 = LiveManager.getInstance().statusControll(lStatusControlRequest2);
		
		if (null == lsResponse2 || !"0".equals(lsResponse2.getErr())) {
			lPreviewInfoModel.setErr(lsResponse2.getErr());
			lPreviewInfoModel.setMsg(lsResponse2.getMsg());
			return lPreviewInfoModel;
		}
		//3.preview url
		LiveWatchRequest lWatchRequest = new LiveWatchRequest();
		lWatchRequest.setChannelWebId(channelWebId);
		lWatchRequest.setClientIp(clientIp);
		
		LiveWatchResponse lWatchResponse = LiveManager.getInstance().preview(lWatchRequest);
		if (null == lWatchResponse || !"0".equals(lWatchResponse.getErr())) {
			lPreviewInfoModel.setErr(lWatchResponse.getErr());
			lPreviewInfoModel.setMsg(lWatchResponse.getMsg());
			return lPreviewInfoModel;
		}
		
		for(LiveWatchMediaResponse lWatchMediaResponse:lWatchResponse.getMedias()){
			String pString = "";
			String protoStr = "";
			if (lWatchMediaResponse.getProtocol().equalsIgnoreCase(LiveProtocol.RTMP.toString())) {
				protoStr = "rtmp";
			}
			if (lWatchMediaResponse.getChannels().length > 0) {
				pString = String.format("%s://%s%s/%s", 
						protoStr,
						lWatchMediaResponse.getChannels()[0].getAddr()[0],
						lWatchMediaResponse.getChannels()[0].getPath(),
						lWatchMediaResponse.getChannels()[0].getName());
			}
			if (lWatchMediaResponse.getProtocol().equalsIgnoreCase(LiveProtocol.RTMP.toString())) {
				lPreviewInfoModel.setRtmpUrl(String.format("%s?ppyunid=%s&cpn=%s", pString, lWatchResponse.getPpyunid(), lWatchResponse.getCpn()));
			}
		}
		
		return lPreviewInfoModel;
	}
}
