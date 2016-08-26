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
public class LiveCreateResponse extends BaseResponse {
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 描述
	 */
	private String intro;
	
	/**
	 * 直播类型(camera,xsplit,rtmp)
	 */
	private String mode;
	
	/**
	 * 创建时间
	 */
	@JsonProperty("insert_time")
	private Long insertTime;
	
	/**
	 * 封面
	 */
	@JsonProperty("cover_url")
	private String coverUrl;
	
	/**
	 * 源地址
	 */
	@JsonProperty("external_url")
	private String externalUrl;
	
	/**
	 * 播放协议
	 */
	@JsonProperty("watch_protocols")
	private String[] watchProtocols;
	
	/**
	 * 视频id
	 */
	@JsonProperty("channel_id")
	private Long channelId;
	
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
	
	/**
	 * 截图
	 */
	@JsonProperty("screenshot_url")
	private String screenshotUrl;
	
	/**
	 * 分类Id
	 */
	@JsonProperty("category_id")
	private Long categoryId;
	
	/**
	 * 视频webid
	 */
	@JsonProperty("channel_web_id")
	private String channelWebId;

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

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public Long getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Long insertTime) {
		this.insertTime = insertTime;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public String getExternalUrl() {
		return externalUrl;
	}

	public void setExternalUrl(String externalUrl) {
		this.externalUrl = externalUrl;
	}

	public String[] getWatchProtocols() {
		return watchProtocols;
	}

	public void setWatchProtocols(String[] watchProtocols) {
		this.watchProtocols = watchProtocols;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
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

	public String getScreenshotUrl() {
		return screenshotUrl;
	}

	public void setScreenshotUrl(String screenshotUrl) {
		this.screenshotUrl = screenshotUrl;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getChannelWebId() {
		return channelWebId;
	}

	public void setChannelWebId(String channelWebId) {
		this.channelWebId = channelWebId;
	}
}
