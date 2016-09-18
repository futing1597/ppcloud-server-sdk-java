/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */

package com.pplive.ppcloud.live;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.pplive.ppcloud.HostConstants;
import com.pplive.ppcloud.VersionConstants;
import com.pplive.ppcloud.auth.AccessTokenSigner;
import com.pplive.ppcloud.http.HttpClientManager;
import com.pplive.ppcloud.request.LiveCreateRequest;
import com.pplive.ppcloud.request.LiveInfoRequest;
import com.pplive.ppcloud.request.LivePublishUrlRequest;
import com.pplive.ppcloud.request.LiveStatusControlRequest;
import com.pplive.ppcloud.request.LiveStatusRequest;
import com.pplive.ppcloud.request.LiveUpdateRequest;
import com.pplive.ppcloud.request.LiveWatchRequest;
import com.pplive.ppcloud.response.BaseResponse;
import com.pplive.ppcloud.response.LiveCreateData;
import com.pplive.ppcloud.response.LiveCreateResponse;
import com.pplive.ppcloud.response.LiveInfoData;
import com.pplive.ppcloud.response.LiveInfoListData;
import com.pplive.ppcloud.response.LiveInfoResponse;
import com.pplive.ppcloud.response.LivePublishUrlData;
import com.pplive.ppcloud.response.LivePublishUrlResponse;
import com.pplive.ppcloud.response.LiveStatusData;
import com.pplive.ppcloud.response.LiveStatusResponse;
import com.pplive.ppcloud.response.LiveWatchData;
import com.pplive.ppcloud.response.LiveWatchResponse;
import com.pplive.ppcloud.utils.JsonUtils;
import com.pplive.ppcloud.utils.LogUtils;

/**
 * @author chaogao
 *
 */
public class LiveManager {

	private LiveManager(){}
	
	public static LiveManager getInstance() {
		return SingletonHolder.instance;
	}
	
	private static class SingletonHolder {
		private static LiveManager instance = new LiveManager();
	}
	
	private static Map<String, String> headerMap = null;
	
	static
	{
		headerMap = new HashMap<String, String>();
		headerMap.put("version", VersionConstants.VERSION30);
	}
	
	/**
	 * 创建直播
	 * @param request LiveCreateRequest 实体对象
	 * @return LiveCreateResponse 实体对象
	 * @throws URISyntaxException 异常
	 */
	public LiveCreateResponse create(LiveCreateRequest request) throws URISyntaxException{
		LogUtils.log(String.format("create request: %s", JsonUtils.toJsonString(request)));
		
		LiveCreateResponse response = null;
		setHeader();
		String jsonRes = HttpClientManager.getInstance().execPostRequestWithHeaders(
				new URI(HostConstants.HOST_URL+HostConstants.CREATE_LIVE_URL), 
				headerMap, 
				request);
		LogUtils.log(String.format("create response: %s", jsonRes));
		if (StringUtils.isNotEmpty(jsonRes)) {
			LiveCreateData liveCreateData = JsonUtils.fromJsonString(jsonRes, LiveCreateData.class);
			if ("0".equals(liveCreateData.getErr())) {
				response = liveCreateData.getData();
			} else {
				response = new LiveCreateResponse();
			}
			response.setErr(liveCreateData.getErr());
			response.setMsg(liveCreateData.getMsg());
		}
		
		return response;
	}
	
	/**
	 * 更新直播
	 * 延迟直播时长可以使用该接口
	 * @param request liveUpdateRequest 实体对象
	 * @return BaseResponse 实体对象
	 * @throws URISyntaxException 异常
	 */
	public BaseResponse update(LiveUpdateRequest request) throws URISyntaxException {
		LogUtils.log(String.format("update request: %s", JsonUtils.toJsonString(request)));
		
		BaseResponse response = null;
		setHeader();
		String jsonRes = HttpClientManager.getInstance().execPostRequestWithHeaders(
				new URI(String.format(HostConstants.HOST_URL+HostConstants.UPDATE_LIVE_URL, request.getChannelWebId())), 
				headerMap, 
				request);
		LogUtils.log(String.format("update response: %s", jsonRes));
		response = JsonUtils.fromJsonString(jsonRes, BaseResponse.class);
		return response;
	}
	
	/**
	 * 获取直播状态
	 * 需要定时获取以判断当前状态是否正常
	 * @param request LiveStatusRequest 实体对象
	 * @return LiveStatusResponse 实体对象
	 * @throws URISyntaxException 异常
	 */
	public LiveStatusResponse status(LiveStatusRequest request) throws URISyntaxException {
		LogUtils.log(String.format("status request: %s", JsonUtils.toJsonString(request)));
		
		LiveStatusResponse response = null;
		setHeader();
		headerMap.put("x-forwarded-for", request.getClientIp());
		String jsonRes = HttpClientManager.getInstance().execGetRequestWithHeader(
				String.format(HostConstants.HOST_URL+HostConstants.GET_LIVE_STATUS_URL, request.getChannelWebId())
				, headerMap);
		LogUtils.log(String.format("status response: %s", jsonRes));
		if (StringUtils.isNotEmpty(jsonRes)) {
			LiveStatusData liveStatusData = JsonUtils.fromJsonString(jsonRes, LiveStatusData.class);
			if ("0".equals(liveStatusData.getErr())) {
				response = liveStatusData.getData();
			} else {
				response = new LiveStatusResponse();
			}
			response.setErr(liveStatusData.getErr());
			response.setMsg(liveStatusData.getMsg());
		}
		return response;
	}
	
	/**
	 * 直播状态控制
	 * @param request LiveStatusControlRequest 实体对象
	 * @return BaseResponse 实体对象
	 * @throws URISyntaxException 异常
	 */
	public BaseResponse statusControll(LiveStatusControlRequest request) throws URISyntaxException {
		LogUtils.log(String.format("statusControll request: %s", JsonUtils.toJsonString(request)));
		
		BaseResponse response = null;
		setHeader();
		String jsonRes = HttpClientManager.getInstance().execPostRequestWithHeaders(
				new URI(String.format(HostConstants.HOST_URL+HostConstants.CONTROL_LIVE_STATUS_URL, request.getChannelWebId())), 
				headerMap, 
				request);
		LogUtils.log(String.format("statusControll response: %s", jsonRes));
		response = JsonUtils.fromJsonString(jsonRes, BaseResponse.class);
		return response;
	}
	
	/**
	 * 获取直播推流信息
	 * @param request LivePublishUrlRequest 实体对象
	 * @return LivePublishUrlResponse 实体对象
	 */
	public LivePublishUrlResponse getPublishUrl(LivePublishUrlRequest request) {
		LogUtils.log(String.format("getPublishUrl request: %s", JsonUtils.toJsonString(request)));
		
		LivePublishUrlResponse response = null;
		setHeader();
		headerMap.put("x-forwarded-for", request.getClientIp());
		String jsonRes = HttpClientManager.getInstance().execGetRequestWithHeader(
				String.format(HostConstants.HOST_URL+HostConstants.GET_LIVE_PUBLISH_URL, request.getChannelWebId()), 
				headerMap);
		LogUtils.log(String.format("getPublishUrl response: %s", jsonRes));
		if (StringUtils.isNotEmpty(jsonRes)) {
			LivePublishUrlData livePublishUrlData = JsonUtils.fromJsonString(jsonRes, LivePublishUrlData.class);
			if ("0".equals(livePublishUrlData.getErr())) {
				response = livePublishUrlData.getData();
			} else {
				response = new LivePublishUrlResponse();
			}
			response.setErr(livePublishUrlData.getErr());
			response.setMsg(livePublishUrlData.getMsg());
		}
		return response;
	}
	
	/**
	 * 获取观看地址信息
	 * 观看地址 包含rtmp和live2
	 * 信息中包含 Logo
	 * @param request LiveWatchRequest 实体对象
	 * @return LiveWatchResponse 实体对象
	 */
	public LiveWatchResponse watch(LiveWatchRequest request) {
		LogUtils.log(String.format("watch request: %s", JsonUtils.toJsonString(request)));
		
		LiveWatchResponse response = null;
		setHeader();
		headerMap.put("x-forwarded-for", request.getClientIp());
		String cWebId = StringUtils.isNotEmpty(request.getChannelWebId())?request.getChannelWebId():"nn";
		String nn = StringUtils.isNotEmpty(request.getChannelWebId())?"":"?nn="+request.getNickName();
		String jsonRes = HttpClientManager.getInstance().execGetRequestWithHeader(
				String.format(HostConstants.HOST_URL+HostConstants.WATCH_LIVE_URL+nn, cWebId), 
				headerMap);
		LogUtils.log(String.format("watch response: %s", jsonRes));
		if (StringUtils.isNotEmpty(jsonRes)) {
			LiveWatchData liveWatchData = JsonUtils.fromJsonString(jsonRes, LiveWatchData.class);
			if ("0".equals(liveWatchData.getErr())) {
				response = liveWatchData.getData();
			} else {
				response = new LiveWatchResponse();
			}
			response.setErr(liveWatchData.getErr());
			response.setMsg(liveWatchData.getMsg());
		}
		return response;
	}
	
	/**
	 * 获取观看地址信息
	 * 观看地址 包含rtmp和live2
	 * 信息中包含 Logo
	 * @param request LiveWatchRequest 实体对象
	 * @return LiveWatchResponse 实体对象
	 */
	public LiveWatchResponse preview(LiveWatchRequest request) {
		LogUtils.log(String.format("preview request: %s", JsonUtils.toJsonString(request)));
		
		LiveWatchResponse response = null;
		setHeader();
		headerMap.put("x-forwarded-for", request.getClientIp());
		String jsonRes = HttpClientManager.getInstance().execGetRequestWithHeader(
				String.format(HostConstants.HOST_URL+HostConstants.PREVIEW_LIVE_URL, request.getChannelWebId()), 
				headerMap);
		LogUtils.log(String.format("preview response: %s", jsonRes));
		if (StringUtils.isNotEmpty(jsonRes)) {
			LiveWatchData liveWatchData = JsonUtils.fromJsonString(jsonRes, LiveWatchData.class);
			if ("0".equals(liveWatchData.getErr())) {
				response = liveWatchData.getData();
			} else {
				response = new LiveWatchResponse();
			}
			response.setErr(liveWatchData.getErr());
			response.setMsg(liveWatchData.getMsg());
		}
		return response;
	}
	
	/**
	 * 获取直播详情
	 * @param request LiveInfoRequest 实体对象
	 * @return LiveInfoResponse 实体对象
	 */
	public LiveInfoResponse getDetail(LiveInfoRequest request) {
		LogUtils.log(String.format("getDetail request: %s", JsonUtils.toJsonString(request)));
		
		LiveInfoResponse response = null;
		setHeader();
		String jsonRes = HttpClientManager.getInstance().execGetRequestWithHeader(
				String.format(HostConstants.HOST_URL+HostConstants.GET_SINGLE_LIVE_URL, request.getChannelWebId()), 
				headerMap);
		LogUtils.log(String.format("getDetail response: %s", jsonRes));
		if (StringUtils.isNotEmpty(jsonRes)) {
			LiveInfoData liveInfoData = JsonUtils.fromJsonString(jsonRes, LiveInfoData.class);
			if ("0".equals(liveInfoData.getErr())) {
				response = liveInfoData.getData();
			} else {
				response = new LiveInfoResponse();
			}
			response.setErr(liveInfoData.getErr());
			response.setMsg(liveInfoData.getMsg());
		}
		return response;
	}
	
	/**
	 * 获取直播列表
	 * @param request LiveInfoRequest 实体对象
	 * @return LiveInfoResponse 实体对象列表
	 * @throws URISyntaxException 
	 */
	public List<LiveInfoResponse> getLivingList(LiveInfoRequest request) throws URISyntaxException {
		List<LiveInfoResponse> responseList = null;
		setHeader();
		String jsonRes = HttpClientManager.getInstance().execPostRequestWithHeaders(new URI(HostConstants.HOST_URL+HostConstants.GET_LIVE_LIST_URL), 
				headerMap,request);
		LogUtils.log(String.format("getLivingList response: %s", jsonRes));
		if (StringUtils.isNotEmpty(jsonRes)) {
			LiveInfoListData liveInfoListData = JsonUtils.fromJsonString(jsonRes, LiveInfoListData.class);
			if ("0".equals(liveInfoListData.getErr())) {
				responseList = Arrays.asList(liveInfoListData.getData());
			} else {
				responseList = new ArrayList<LiveInfoResponse>();
			}
		}
		return responseList;
	}
	
	private void setHeader() {
		headerMap.put("Authorization", AccessTokenSigner.getInstance().getAccessToken());
	}
}
