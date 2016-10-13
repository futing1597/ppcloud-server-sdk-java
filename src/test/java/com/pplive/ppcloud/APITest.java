/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */

package com.pplive.ppcloud;

import com.pplive.ppcloud.live.LiveManager;
import com.pplive.ppcloud.request.*;
import com.pplive.ppcloud.response.*;
import com.pplive.ppcloud.utils.JsonUtils;
import com.pplive.ppcloud.utils.LogUtils;
import junit.framework.TestCase;

import java.util.List;

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
		
		LiveCreateResponse response = null;
		response = LiveManager.getInstance().create(request);

		LogUtils.log(String.format("LiveCreate: %s", JsonUtils.toJsonString(response)));
    	LogUtils.log(String.format("channel_web_id: %s", response.getChannelWebId()));
    	
    	assertNotNull(response);
    	assertEquals("0", response.getErr());
    }
	
	public void testLiveUpdate()
	{
		LiveUpdateRequest request = new LiveUpdateRequest();
		request.setChannelWebId("0a2dnqyaqaSjoa-L4K0");
		request.setEndTime(System.currentTimeMillis()+3600000);
		
		BaseResponse response = null;
		response = LiveManager.getInstance().update(request);
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
		response = LiveManager.getInstance().status(request);
		LogUtils.log(String.format("LiveStatus: %s", JsonUtils.toJsonString(response)));
		
		assertNotNull(response);
    	assertEquals("0", response.getErr());
	}
	
	public void testLiveStatusControll()
	{
		LiveStatusControlRequest request = new LiveStatusControlRequest();
		request.setChannelWebId("0a2dnqyaqaSkoKqL4K2hoqrhoaSioKaaqA");
		request.setLiveStatus(LiveStatus.INIT.toString());
		
		BaseResponse response = null;
		response = LiveManager.getInstance().statusControll(request);
		LogUtils.log(String.format("LiveStatusControll: %s", JsonUtils.toJsonString(response)));
		
		assertNotNull(response);
    	assertEquals("0", response.getErr());
	}
	
	public void testLivePublishUrl()
	{
		LivePublishUrlRequest request = new LivePublishUrlRequest();
		request.setChannelWebId("0a2dnq6aoaSgnKmL4K2dnqfhoaajma6bow");
		request.setClientIp("117.135.159.4");
		
		LivePublishUrlResponse response = LiveManager.getInstance().getPublishUrl(request);
		LogUtils.log(String.format("LivePublishUrl: %s", JsonUtils.toJsonString(response)));
		
		assertNotNull(response);
    	assertEquals("0", response.getErr());
	}
	
	public void testLivePreview()
	{
		LiveWatchRequest request = new LiveWatchRequest();
		request.setChannelWebId("0a2dnqyaqaSkoKqL4K2hoqrhoaSioKaaqA");
		request.setClientIp("117.135.159.4");
		
		LiveWatchResponse response = LiveManager.getInstance().preview(request);
		LogUtils.log(String.format("LivePreview: %s", JsonUtils.toJsonString(response)));
		
		assertNotNull(response);
    	assertEquals("0", response.getErr());
	}
	
	public void testLiveWatch()
	{
		LiveWatchRequest request = new LiveWatchRequest();
		request.setChannelWebId("0a2dnqyaqaSkoKqL4K2hoqrhoaSioKaaqA");
		request.setClientIp("117.135.159.4");
		
		LiveWatchResponse response = LiveManager.getInstance().watch(request);
		LogUtils.log(String.format("LivePreview: %s", JsonUtils.toJsonString(response)));
		
		assertNotNull(response);
    	assertEquals("0", response.getErr());
	}
	
	public void testLiveDetail()
	{
		LiveInfoRequest request = new LiveInfoRequest();
		request.setChannelWebId("0a2dnqyaqaihna-L4K2hoqrhoaSioaabpg");
		
		LiveInfoResponse response = LiveManager.getInstance().getDetail(request);
		LogUtils.log(String.format("LiveDetail: %s", JsonUtils.toJsonString(response)));
		
		assertNotNull(response);
    	assertEquals("0", response.getErr());
	}
	
	public void testLivingList()
	{
		LiveInfoRequest request = new LiveInfoRequest();
		request.setChannelType(ChannelType.LIVE.toString());
		request.setLiveStatus(LiveStatus.LIVING.toString());
		request.setPageSize(100);

		LiveInfoListData liveInfoListData = LiveManager.getInstance().getLiveList(request);
		LogUtils.log(String.format("LivingList: %s", JsonUtils.toJsonString(liveInfoListData)));
		
		assertNotNull(liveInfoListData);
		assertEquals("0", liveInfoListData.getErr());
	}

	public void testLiveStoppedList()
	{
		LiveInfoRequest request = new LiveInfoRequest();
		request.setChannelType(ChannelType.LIVE.toString());
		request.setLiveStatus(LiveStatus.STOPPED.toString());
		request.setPageNum(1);
		request.setPageSize(10);
		request.setCreateTimeStart(System.currentTimeMillis()-48*3600000);//2天

		LiveInfoListData liveInfoListData = LiveManager.getInstance().getLiveList(request);
		LogUtils.log(String.format("LivingList: %s", JsonUtils.toJsonString(liveInfoListData)));

		assertNotNull(liveInfoListData);
		assertEquals("0", liveInfoListData.getErr());
	}

	public void testLive2VodList()
	{
		LiveInfoRequest request = new LiveInfoRequest();
		request.setChannelType(ChannelType.VOD.toString());
		request.setLiveStatus(LiveStatus.STOPPED.toString());
		request.setPageNum(1);
		request.setPageSize(10);
		request.setTranscodeStatus(TranscodeStatus.PLAYABLE.toString());

		LiveInfoListData liveInfoListData = LiveManager.getInstance().getLiveList(request);
		LogUtils.log(String.format("LivingList: %s", JsonUtils.toJsonString(liveInfoListData)));

		assertNotNull(liveInfoListData);
		assertEquals("0", liveInfoListData.getErr());
	}
}
