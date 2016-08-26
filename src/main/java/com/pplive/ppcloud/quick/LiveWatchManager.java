/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */
package com.pplive.ppcloud.quick;

import com.pplive.ppcloud.LiveProtocol;
import com.pplive.ppcloud.live.LiveManager;
import com.pplive.ppcloud.quick.model.LivePreviewInfoModel;
import com.pplive.ppcloud.request.LiveWatchRequest;
import com.pplive.ppcloud.response.LiveWatchMediaResponse;
import com.pplive.ppcloud.response.LiveWatchResponse;

/**
 * @author chaogao
 *
 */
public class LiveWatchManager {
	private LiveWatchManager() {}
	
	public static LiveWatchManager getInstance() {
		return SingletonHolder.instance;
	}
	
	private static class SingletonHolder {
		private static LiveWatchManager instance = new LiveWatchManager();
	}
	
	/**
	 * 获取直播播放地址
	 * 通过 channelWebId
	 * @param channelWebId 直播播放串
	 * @return 预览地址
	 */
	public LivePreviewInfoModel watch(String channelWebId, String clientIp) {
		LiveWatchRequest lWatchRequest = new LiveWatchRequest();
		lWatchRequest.setChannelWebId(channelWebId);
		lWatchRequest.setChannelWebId(clientIp);
		return watch(lWatchRequest);
	}
	
	/**
	 * 获取直播播放地址
	 * 通过 nickname
	 * @param nickname 流昵称
	 * @return 预览地址
	 */
	public LivePreviewInfoModel watchForNickname(String nickname, String clientIp) {
		LiveWatchRequest lWatchRequest = new LiveWatchRequest();
		lWatchRequest.setNickName(nickname);
		lWatchRequest.setClientIp(clientIp);
		return watch(lWatchRequest);
	}
	
	/**
	 * 获取直播播放地址
	 * @param lWatchRequest 请求参数
	 * @return 预览地址
	 */
	public LivePreviewInfoModel watch(LiveWatchRequest lWatchRequest) {
		LivePreviewInfoModel lPreviewInfoModel = new LivePreviewInfoModel();
		
		//play url
		
		LiveWatchResponse lWatchResponse = LiveManager.getInstance().watch(lWatchRequest);
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
			if (lWatchMediaResponse.getProtocol().equalsIgnoreCase(LiveProtocol.LIVE2.toString())) {
				protoStr = "http";
			}
			if (lWatchMediaResponse.getChannels().length > 0) {
				pString = String.format("%s://%s%s/%s", 
						protoStr,
						lWatchMediaResponse.getChannels()[0].getAddr()[0],
						lWatchMediaResponse.getChannels()[0].getPath(),
						lWatchMediaResponse.getChannels()[0].getName());
			}
			if (lWatchMediaResponse.getProtocol().equalsIgnoreCase(LiveProtocol.RTMP.toString())) {
				lPreviewInfoModel.setRtmpUrl(pString);
			}
			if (lWatchMediaResponse.getProtocol().equalsIgnoreCase(LiveProtocol.LIVE2.toString())) {
				lPreviewInfoModel.setLive2Url(pString);
			}
		}
		
		return lPreviewInfoModel;
	}
}
