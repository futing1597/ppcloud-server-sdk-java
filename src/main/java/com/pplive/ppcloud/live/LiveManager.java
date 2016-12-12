/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */

package com.pplive.ppcloud.live;

import com.pplive.ppcloud.HostConstants;
import com.pplive.ppcloud.VersionConstants;
import com.pplive.ppcloud.auth.AccessTokenSigner;
import com.pplive.ppcloud.http.HttpClientManager;
import com.pplive.ppcloud.http.HttpProxyConfig;
import com.pplive.ppcloud.quick.model.LivePreviewInfoModel;
import com.pplive.ppcloud.request.*;
import com.pplive.ppcloud.response.*;
import com.pplive.ppcloud.utils.JsonUtils;
import com.pplive.ppcloud.utils.LogUtils;
import org.apache.commons.lang3.StringUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

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
	private HttpProxyConfig proxyConfig = null;
	
	static
	{
		headerMap = new HashMap<String, String>();
		headerMap.put("version", VersionConstants.VERSION30);
	}
	
	/**
	 * 创建直播
	 * @param request LiveCreateRequest 实体对象
	 * @return LiveCreateResponse 实体对象
	 */
	public LiveCreateResponse create(LiveCreateRequest request) {
		LogUtils.log(String.format("create request: %s", JsonUtils.toJsonString(request)));
		
		LiveCreateResponse response = null;
		setHeader();
		URI uri = getUri(HostConstants.HOST_URL+HostConstants.CREATE_LIVE_URL);
		String jsonRes = HttpClientManager.getInstance().execPostRequestWithHeaders(uri, headerMap, request, proxyConfig);
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
	 */
	public BaseResponse update(LiveUpdateRequest request) {
		LogUtils.log(String.format("update request: %s", JsonUtils.toJsonString(request)));
		
		BaseResponse response = null;
		setHeader();
		URI uri = getUri(String.format(HostConstants.HOST_URL+HostConstants.UPDATE_LIVE_URL, request.getChannelWebId()));
		String jsonRes = HttpClientManager.getInstance().execPostRequestWithHeaders(uri, headerMap, request, proxyConfig);
		LogUtils.log(String.format("update response: %s", jsonRes));
		response = JsonUtils.fromJsonString(jsonRes, BaseResponse.class);
		return response;
	}
	
	/**
	 * 获取直播状态
	 * 需要定时获取以判断当前状态是否正常
	 * @param request LiveStatusRequest 实体对象
	 * @return LiveStatusResponse 实体对象
	 */
	public LiveStatusResponse status(LiveStatusRequest request) {
		LogUtils.log(String.format("status request: %s", JsonUtils.toJsonString(request)));
		
		LiveStatusResponse response = null;
		setHeader();
		headerMap.put("x-forwarded-for", request.getClientIp());
		String jsonRes = HttpClientManager.getInstance().execGetRequestWithHeader(
				String.format(HostConstants.HOST_URL+HostConstants.GET_LIVE_STATUS_URL, request.getChannelWebId())
				, headerMap, proxyConfig);
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
	 */
	public BaseResponse statusControll(LiveStatusControlRequest request) {
		LogUtils.log(String.format("statusControll request: %s", JsonUtils.toJsonString(request)));
		
		BaseResponse response = null;
		setHeader();
		URI uri = getUri(String.format(HostConstants.HOST_URL+HostConstants.CONTROL_LIVE_STATUS_URL, request.getChannelWebId()));
		String jsonRes = HttpClientManager.getInstance().execPostRequestWithHeaders(uri, headerMap, request, proxyConfig);
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
				headerMap, proxyConfig);
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
		String jsonRes = HttpClientManager.getInstance().execGetRequestWithHeader(
				String.format(HostConstants.HOST_URL+HostConstants.WATCH_LIVE_URL, request.getChannelWebId()),
				headerMap, proxyConfig);
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
				headerMap, proxyConfig);
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
				headerMap, proxyConfig);
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
	 */
	public LiveInfoListData getLiveList(LiveInfoRequest request) {
		LiveInfoListData liveInfoListData = null;
		setHeader();
		URI uri = getUri(HostConstants.HOST_URL+HostConstants.GET_LIVE_LIST_URL);
		String jsonRes = HttpClientManager.getInstance().execPostRequestWithHeaders(uri, headerMap,request, proxyConfig);
		LogUtils.log(String.format("getLiveList response: %s", jsonRes));
		if (StringUtils.isNotEmpty(jsonRes)) {
			liveInfoListData = JsonUtils.fromJsonString(jsonRes, LiveInfoListData.class);
		}
		return liveInfoListData;
	}

	/**
	 * 获取播放地址
	 * 播放地址包含直点播链接和有效期
	 * 播放协议：RTMP/HDL/HLS
	 * rtmps_url 提供多码率选择
	 * @param request LiveWatchRequest 实体对象
	 * @return LivePreviewInfoModel 实体对象
	 */
	public LivePreviewInfoModel getPlayStr(LiveWatchRequest request) {
		LogUtils.log(String.format("getPlayStr request: %s", JsonUtils.toJsonString(request)));

		LivePreviewInfoModel response = null;
		setHeader(request.getExpireInMinutes());
		headerMap.put("x-forwarded-for", request.getClientIp());
		String jsonRes = HttpClientManager.getInstance().execGetRequestWithHeader(
				String.format(HostConstants.HOST_URL+HostConstants.PLAY_STR_URL, request.getChannelWebId()),
				headerMap, proxyConfig);
		LogUtils.log(String.format("getPlayStr response: %s", jsonRes));
		if (StringUtils.isNotEmpty(jsonRes)) {
			LivePlayStrData livePlayStrData = JsonUtils.fromJsonString(jsonRes, LivePlayStrData.class);
			if ("0".equals(livePlayStrData.getErr())) {
				response = livePlayStrData.getData();
			} else {
				response = new LivePreviewInfoModel();
			}
			response.setErr(livePlayStrData.getErr());
			response.setMsg(livePlayStrData.getMsg());
		}
		return response;
	}
	
	private void setHeader() {
		headerMap.put("Authorization", AccessTokenSigner.getInstance().getAccessToken());
	}

	private void setHeader(Integer expireInMinutes) {
		headerMap.put("Authorization", AccessTokenSigner.getInstance().getAccessToken(expireInMinutes));
	}

	public HttpProxyConfig getProxyConfig() {
		return proxyConfig;
	}

	public void setProxyConfig(HttpProxyConfig proxyConfig) {
		this.proxyConfig = proxyConfig;
	}

	private URI getUri(String url) {
		try {
			return new URI(url);
		} catch (URISyntaxException e) {
			// 不向外抛出该异常增加外部处理的麻烦，url地址都由内部组装可保证格式正确性
			return null;
		}
	}
}
