/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */
package com.pplive.ppcloud.quick.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author chaogao
 *
 */
public class LivePreviewInfoModel {
	
	/**
	 * 视频webid
	 */
	private String channelWebId;
	
	/**
	 * rtmp 播放地址
	 */
	private String rtmpUrl;
	
	/**
	 * live2 播放地址
	 */
	private String live2Url;
	
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

	public String getRtmpUrl() {
		return rtmpUrl;
	}

	public void setRtmpUrl(String rtmpUrl) {
		this.rtmpUrl = rtmpUrl;
	}

	public String getLive2Url() {
		return live2Url;
	}

	public void setLive2Url(String live2Url) {
		this.live2Url = live2Url;
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
