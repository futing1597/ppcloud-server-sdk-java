/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */

package com.pplive.ppcloud;

import java.net.URISyntaxException;

import com.pplive.ppcloud.live.LiveManager;
import com.pplive.ppcloud.request.LiveCreateRequest;
import com.pplive.ppcloud.request.LiveInfoRequest;
import com.pplive.ppcloud.request.LivePublishUrlRequest;
import com.pplive.ppcloud.request.LiveStatusControlRequest;
import com.pplive.ppcloud.request.LiveStatusRequest;
import com.pplive.ppcloud.request.LiveUpdateRequest;
import com.pplive.ppcloud.request.LiveWatchRequest;
import com.pplive.ppcloud.response.BaseResponse;
import com.pplive.ppcloud.response.LiveCreateResponse;
import com.pplive.ppcloud.response.LiveInfoResponse;
import com.pplive.ppcloud.response.LivePublishUrlResponse;
import com.pplive.ppcloud.response.LiveStatusResponse;
import com.pplive.ppcloud.response.LiveWatchResponse;
import com.pplive.ppcloud.utils.JsonUtils;
import com.pplive.ppcloud.utils.LogUtils;

import junit.framework.TestCase;

/**
 * @author chaogao
 *
 */
public class APITest extends TestCase {
	
	public void testLiveCreate()
    {
		LiveCreateRequest request = new LiveCreateRequest();
		request.setTitle("[SDKTest]test001");
		request.setMode(LiveMode.XSPLIT.toString());
		request.setStartTime(System.currentTimeMillis());
		request.setEndTime(System.currentTimeMillis()+7200000);
		request.setNickName("[SDKTest]nickname_001");
		
		LiveCreateResponse response = null;
    	try {
    		response = LiveManager.getInstance().create(request);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
    	
    	LogUtils.log(String.format("LiveCreate: %s", JsonUtils.toJsonString(response)));
    	LogUtils.log(String.format("channel_web_id: %s", response.getChannelWebId()));
    	
    	assertNotNull(response);
    	assertEquals("0", response.getErr());
    }
	
	public void testLiveUpdate()
	{
		LiveUpdateRequest request = new LiveUpdateRequest();
		request.setChannelWebId("0a2dnqyaqaSkoKyL4K2hoqrhoaSioKaboA");
		request.setEndTime(System.currentTimeMillis()+3600000);
		
		BaseResponse response = null;
		try {
			response = LiveManager.getInstance().update(request);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		LogUtils.log(String.format("LiveUpdate: %s", JsonUtils.toJsonString(response)));
		
		assertNotNull(response);
    	assertEquals("0", response.getErr());
	}
	
	public void testLiveStatus()
	{
		LiveStatusRequest request = new LiveStatusRequest();
		request.setChannelWebId("0a2dnqyaqaSkoKyL4K2hoqrhoaSioKaboA");
		request.setClientIp("117.135.159.4");
		
		LiveStatusResponse response = null;
		try {
			response = LiveManager.getInstance().status(request);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		LogUtils.log(String.format("LiveStatus: %s", JsonUtils.toJsonString(response)));
		
		assertNotNull(response);
    	assertEquals("0", response.getErr());
	}
	
	public void testLiveStatusControll()
	{
		LiveStatusControlRequest request = new LiveStatusControlRequest();
		request.setChannelWebId("0a2dnqyaqaSkoKyL4K2hoqrhoaSioKaboA");
		request.setLiveStatus(LiveStatus.INIT.toString());
		
		BaseResponse response = null;
		try {
			response = LiveManager.getInstance().statusControll(request);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		LogUtils.log(String.format("LiveStatusControll: %s", JsonUtils.toJsonString(response)));
		
		assertNotNull(response);
    	assertEquals("0", response.getErr());
	}
	
	public void testLivePublishUrl()
	{
		LivePublishUrlRequest request = new LivePublishUrlRequest();
		request.setChannelWebId("0a2dnqyaqaSkoKyL4K2hoqrhoaSioKaboA");
		request.setClientIp("117.135.159.4");
		
		LivePublishUrlResponse response = LiveManager.getInstance().getPublishUrl(request);
		LogUtils.log(String.format("LivePublishUrl: %s", JsonUtils.toJsonString(response)));
		
		assertNotNull(response);
    	assertEquals("0", response.getErr());
	}
	
	public void testLivePreview()
	{
		LiveWatchRequest request = new LiveWatchRequest();
		request.setChannelWebId("0a2dnqyaqaSkoKyL4K2hoqrhoaSioKaboA");
		request.setClientIp("117.135.159.4");
		
		LiveWatchResponse response = LiveManager.getInstance().preview(request);
		LogUtils.log(String.format("LivePreview: %s", JsonUtils.toJsonString(response)));
		
		assertNotNull(response);
    	assertEquals("0", response.getErr());
	}
	
	public void testLiveWatch()
	{
		LiveWatchRequest request = new LiveWatchRequest();
		request.setChannelWebId("0a2dnqyaqaSkoKyL4K2hoqrhoaSioKaboA");
//		request.setNickName("[SDKTest]nickname_001");
		request.setClientIp("117.135.159.4");
		
		LiveWatchResponse response = LiveManager.getInstance().watch(request);
		LogUtils.log(String.format("LivePreview: %s", JsonUtils.toJsonString(response)));
		
		assertNotNull(response);
    	assertEquals("0", response.getErr());
	}
	
	public void testLiveDetail()
	{
		LiveInfoRequest request = new LiveInfoRequest();
		request.setChannelWebId("0a2dnqyaqaSkoKyL4K2hoqrhoaSioKaboA");
		
		LiveInfoResponse response = LiveManager.getInstance().getDetail(request);
		LogUtils.log(String.format("LiveDetail: %s", JsonUtils.toJsonString(response)));
		
		assertNotNull(response);
    	assertEquals("0", response.getErr());
	}
}
