/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */

package com.pplive.ppcloud.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author chaogao
 *
 */
public class LiveWatchRequest {

	/**
	 * 视频webid
	 */
	@JsonProperty("channel_web_id")
	private String channelWebId;
	
	/**
	 * 直播别名
	 */
	@JsonProperty("nickname")
	private String nickName;
	
	/**
	 * 客户端Ip
	 */
	@JsonIgnore
	private String clientIp;

	public String getChannelWebId() {
		return channelWebId;
	}

	public void setChannelWebId(String channelWebId) {
		this.channelWebId = channelWebId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
}
