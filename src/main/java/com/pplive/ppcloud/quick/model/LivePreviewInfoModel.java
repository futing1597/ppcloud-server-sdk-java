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
	 * HDL 播放地址
	 */
	private String hdlUrl;
	
	/**
	 * m3u8 播放地址
	 */
	private String m3u8Url;
	
	/**
	 * 多码流 播放地址
	 */
	private String[] rtmpsUrl;
	
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

	public String getHdlUrl() {
		return hdlUrl;
	}

	public void setHdlUrl(String hdlUrl) {
		this.hdlUrl = hdlUrl;
	}

	public String getM3u8Url() {
		return m3u8Url;
	}

	public void setM3u8Url(String m3u8Url) {
		this.m3u8Url = m3u8Url;
	}

	public String[] getRtmpsUrl() {
		return rtmpsUrl;
	}

	public void setRtmpsUrl(String[] rtmpsUrl) {
		this.rtmpsUrl = rtmpsUrl;
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
