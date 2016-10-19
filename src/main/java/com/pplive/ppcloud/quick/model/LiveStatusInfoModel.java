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
public class LiveStatusInfoModel {
	
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
	
	/**
	 * 直播流状态
	 */
	@JsonProperty("stream_status")
	private String streamStatus;
	
	/**
	 * 下次请求延迟时间(单位:秒)
	 */
	private Integer delay;
	
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
