/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */

package com.pplive.ppcloud.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author chaogao
 *
 */
public class LiveStatusResponse extends BaseResponse {
	
	/**
	 * 频道id
	 */
	@JsonProperty("channel_id")
	private Long channelId;
	
	/**
	 * 直播状态(参考直播状态表)
	 */
	@JsonProperty("live_status")
	private String liveStatus;
	
	/**
	 * 直播流状态
	 */
	@JsonProperty("stream_status")
	private String streamStatus;
	
	/**
	 * 下次请求延迟时间(单位:秒)
	 */
	private Integer delay;

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public String getLiveStatus() {
		return liveStatus;
	}

	public void setLiveStatus(String liveStatus) {
		this.liveStatus = liveStatus;
	}

	public String getStreamStatus() {
		return streamStatus;
	}

	public void setStreamStatus(String streamStatus) {
		this.streamStatus = streamStatus;
	}

	public Integer getDelay() {
		return delay;
	}

	public void setDelay(Integer delay) {
		this.delay = delay;
	}
}
