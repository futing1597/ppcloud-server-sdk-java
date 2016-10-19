/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */
package com.pplive.ppcloud.quick;

import com.pplive.ppcloud.quick.model.LivePreviewInfoCftModel;
import org.apache.commons.lang3.StringUtils;

import com.pplive.ppcloud.HostConstants;
import com.pplive.ppcloud.LiveProtocol;
import com.pplive.ppcloud.live.LiveManager;
import com.pplive.ppcloud.quick.model.LivePreviewInfoModel;
import com.pplive.ppcloud.request.LiveWatchRequest;
import com.pplive.ppcloud.response.LiveWatchMediaChannelCftItemResponse;
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
			} else if (lWatchMediaResponse.getProtocol().equalsIgnoreCase(LiveProtocol.HDL.toString())) {
				protoStr = "http";
			}
			if (lWatchMediaResponse.getChannels().length > 0) {
				pString = String.format("%s://%s%s/%s", 
						protoStr,
						lWatchMediaResponse.getChannels()[0].getAddr()[0],
						lWatchMediaResponse.getChannels()[0].getPath(),
						lWatchMediaResponse.getChannels()[0].getName());
			}
			
			String mProtocol = lWatchMediaResponse.getProtocol();
			if (LiveProtocol.RTMP.toString().equalsIgnoreCase(mProtocol)) {
				lPreviewInfoModel.setRtmpUrl(String.format("%s?ppyunid=%s&cpn=%s", pString, lWatchResponse.getPpyunid(), lWatchResponse.getCpn()));
				
				if (lWatchMediaResponse.getChannels()[0].getCft() != null) {
					LivePreviewInfoCftModel[] rtmpsUrl = new LivePreviewInfoCftModel[lWatchMediaResponse.getChannels()[0].getCft().getItem().length];
					int rtmpIndex = 0;
					for(LiveWatchMediaChannelCftItemResponse itemResponse:lWatchMediaResponse.getChannels()[0].getCft().getItem()) {
						rtmpsUrl[rtmpIndex] = new LivePreviewInfoCftModel();
						rtmpsUrl[rtmpIndex].setFt(itemResponse.getFt());
						rtmpsUrl[rtmpIndex].setName(itemResponse.getName());
						rtmpsUrl[rtmpIndex].setftCn(itemResponse.getFt_cn());
						rtmpsUrl[rtmpIndex++].setRtmpUrl(String.format("%s://%s%s/%s?ppyunid=%s&cpn=%s",
								protoStr,
								lWatchMediaResponse.getChannels()[0].getAddr()[0],
								lWatchMediaResponse.getChannels()[0].getPath(),
								itemResponse.getName(),
								lWatchResponse.getPpyunid(),
								lWatchResponse.getCpn()));
					}
					lPreviewInfoModel.setRtmpsUrl(rtmpsUrl);
				}
				
			} else if (LiveProtocol.HDL.toString().equalsIgnoreCase(mProtocol)) {
				lPreviewInfoModel.setHdlUrl(String.format("%s?ppyunid=%s&cpn=%s", pString, lWatchResponse.getPpyunid(), lWatchResponse.getCpn()));
			}
			if (StringUtils.isNotEmpty(lWatchRequest.getChannelWebId())) {
				lPreviewInfoModel.setM3u8Url(String.format(HostConstants.M3U8_PLAY_URL, lWatchRequest.getChannelWebId()));
				lPreviewInfoModel.setChannelWebId(lWatchRequest.getChannelWebId());
			}
			
		}
		
		return lPreviewInfoModel;
	}
}
