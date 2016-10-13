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
public class LiveCreateRequest {
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 描述
	 */
	private String intro;
	
	/**
	 * 封面
	 */
	@JsonProperty("cover_url")
	private String coverUrl;
	
	/**
	 * 直播类型(camera,xsplit,rtmp)
	 */
	private String mode;
	
	/**
	 * 源地址(mode为rtmp时必填)
	 */
	@JsonProperty("external_url")
	private String externalUrl;
	
	/**
	 * 开始时间
	 */
	@JsonProperty("start_time")
	private Long startTime;
	
	/**
	 * 结束时间
	 */
	@JsonProperty("end_time")
	private Long endTime;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getExternalUrl() {
		return externalUrl;
	}

	public void setExternalUrl(String externalUrl) {
		this.externalUrl = externalUrl;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

}
