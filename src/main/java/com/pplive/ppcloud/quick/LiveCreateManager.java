/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */
package com.pplive.ppcloud.quick;

import com.pplive.ppcloud.HostConstants;
import com.pplive.ppcloud.auth.AccessTokenSigner;
import com.pplive.ppcloud.live.LiveManager;
import com.pplive.ppcloud.quick.model.LivePushInfoModel;
import com.pplive.ppcloud.request.LiveCreateRequest;
import com.pplive.ppcloud.request.LivePublishUrlRequest;
import com.pplive.ppcloud.response.LiveCreateResponse;
import com.pplive.ppcloud.response.LivePublishUrlResponse;


/**
 * @author chaogao
 *
 */
public class LiveCreateManager {
	
	private LiveCreateManager() {}
	
	public static LiveCreateManager getInstance() {
		return SingletonHolder.instance;
	}
	
	private static class SingletonHolder {
		private static LiveCreateManager instance = new LiveCreateManager();
	}
	
	/**
	 * 创建一个直播
	 * 包含 创建,初始化,获取推流地址
	 * @param mode 推流方式
	 * @return 推流地址
	 */
	public LivePushInfoModel create(String mode, String clientIp) {
		String title = "[SDK]"+System.currentTimeMillis();
		return create(title, mode, clientIp);
	}
	
	/**
	 * 创建一个直播
	 * 包含 创建,初始化,获取推流地址
	 * @param title 直播标题
	 * @param mode 直播方式
	 * @return 推流地址
	 */
	public LivePushInfoModel create(String title, String mode, String clientIp) {
		LivePushInfoModel livePushInfoModel = new LivePushInfoModel();
		//1.create live
		LiveCreateRequest liveCreateRequest = new LiveCreateRequest();
		liveCreateRequest.setTitle(title);
		liveCreateRequest.setMode(mode);
		liveCreateRequest.setStartTime(System.currentTimeMillis());
		liveCreateRequest.setEndTime(System.currentTimeMillis()+4*3600000);//默认4个小时
		
		LiveCreateResponse liveCreateResponse = null;
		liveCreateResponse = LiveManager.getInstance().create(liveCreateRequest);
    	
    	if (null == liveCreateResponse || !"0".equals(liveCreateResponse.getErr())) {
    		livePushInfoModel.setErr(liveCreateResponse.getErr());
    		livePushInfoModel.setMsg(liveCreateResponse.getMsg());
    		return livePushInfoModel;
		}
    	
    	//2.get url
		LivePublishUrlRequest livePublishUrlRequest = new LivePublishUrlRequest();
		livePublishUrlRequest.setChannelWebId(liveCreateResponse.getChannelWebId());
		livePublishUrlRequest.setClientIp(clientIp);
		
		LivePublishUrlResponse lPublishUrlResponse = LiveManager.getInstance().getPublishUrl(livePublishUrlRequest);
    	
		if (null == lPublishUrlResponse || !"0".equals(lPublishUrlResponse.getErr())) {
			livePushInfoModel.setErr(lPublishUrlResponse.getErr());
    		livePushInfoModel.setMsg(lPublishUrlResponse.getMsg());
    		return livePushInfoModel;
		}
		
		livePushInfoModel.setChannelWebId(liveCreateResponse.getChannelWebId());
		livePushInfoModel.setPushUrl(String.format("%s://%s%s", 
				lPublishUrlResponse.getProtocol(), 
				lPublishUrlResponse.getAddr()[0],
				lPublishUrlResponse.getPath()));
		livePushInfoModel.setToken(lPublishUrlResponse.getName());

		//3.network sense, expiretime = 1 day
		livePushInfoModel.setNetworkSenseUrl(String.format(HostConstants.NETWORK_SENSE_URL,
				livePushInfoModel.getChannelWebId(), title, AccessTokenSigner.getInstance().getAccessToken(60*24)));
		
		return livePushInfoModel;
	}
}
