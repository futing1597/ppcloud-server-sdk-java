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
public class LivePublishUrlRequest {

	/**
	 * 视频webid
	 */
	@JsonProperty("channel_web_id")
	private String channelWebId;
	
	/**
	 * 是否编码(true:编码-path以publish结尾;false:不编码-path以push结尾)
	 */
	private String coded;
	
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

	public String getCoded() {
		return coded;
	}

	public void setCoded(String coded) {
		this.coded = coded;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
}
