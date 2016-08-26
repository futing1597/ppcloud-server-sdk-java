/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */

package com.pplive.ppcloud.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author chaogao
 *
 */
public class LiveStatusControlRequest {

	/**
	 * 视频webid
	 */
	@JsonProperty("channel_web_id")
	private String channelWebId;
	
	/**
	 * 直播状态(参考直播状态表)
	 */
	@JsonProperty("live_status")
	private String liveStatus;

	public String getChannelWebId() {
		return channelWebId;
	}

	public void setChannelWebId(String channelWebId) {
		this.channelWebId = channelWebId;
	}

	public String getLiveStatus() {
		return liveStatus;
	}

	public void setLiveStatus(String liveStatus) {
		this.liveStatus = liveStatus;
	}
}
