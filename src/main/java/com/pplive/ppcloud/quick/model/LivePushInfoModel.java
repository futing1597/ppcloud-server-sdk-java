/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */
package com.pplive.ppcloud.quick.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author chaogao
 *
 */
public class LivePushInfoModel {
	
	/**
	 * 视频webid
	 */
	@JsonProperty("channel_web_id")
	private String channelWebId;
	
	/**
	 * 推流地址
	 */
	@JsonProperty("push_url")
	private String pushUrl;
	
	/**
	 * 推流鉴权信息
	 */
	private String token;

	/**
	 * 网络检测URL
	 */
	@JsonProperty("network_sense_url")
	private String networkSenseUrl;
	
	/**
	 * 错误代码
	 */
	@JsonIgnore
	public String err = "0";
	
	/**
	 * 错误信息
	 */
	@JsonIgnore
	public String msg;

	public String getChannelWebId() {
		return channelWebId;
	}

	public void setChannelWebId(String channelWebId) {
		this.channelWebId = channelWebId;
	}

	public String getPushUrl() {
		return pushUrl;
	}

	public void setPushUrl(String pushUrl) {
		this.pushUrl = pushUrl;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNetworkSenseUrl() {
		return networkSenseUrl;
	}

	public void setNetworkSenseUrl(String networkSenseUrl) {
		this.networkSenseUrl = networkSenseUrl;
	}

	public String getErr() {
		return err;
	}

	public void setErr(String err) {
		this.err = err;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
