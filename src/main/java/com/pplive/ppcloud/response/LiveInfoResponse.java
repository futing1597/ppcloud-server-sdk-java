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
public class LiveInfoResponse extends BaseResponse {
	
	/**
	 * id
	 */
	private Long id;
	
	/**
	 * 视频时长(单位:秒)
	 */
	private Long duration;
	
	/**
	 * 有效观看次数
	 */
	private Long vv;
	
	/**
	 * 是否可播放
	 */
	private Integer displayable;
	
	/**
	 * 分类id
	 */
	@JsonProperty("category_id")
	private Long categoryId;
	
	/**
	 * 用户id
	 */
	@JsonProperty("user_id")
	private Long userId;
	
	/**
	 * 视频名称
	 */
	@JsonProperty("channel_name")
	private String channelName;
	
	/**
	 * 视频描述
	 */
	@JsonProperty("channel_summary")
	private String channelSummary;
	
	/**
	 * 封面图片
	 */
	@JsonProperty("cover_image")
	private String coverImage;
	
	/**
	 * 视频id
	 */
	@JsonProperty("channel_id")
	private Long channelId;
	
	/**
	 * 视频类型(1:点播;2:直播)
	 */
	@JsonProperty("channel_type")
	private Integer channelType;
	
	/**
	 * 创建时间
	 */
	@JsonProperty("create_time")
	private String createTime;
	
	/**
	 * 视频webid
	 */
	@JsonProperty("channel_web_id")
	private String channelWebId;
	
	/**
	 * 转码状态
	 */
	@JsonProperty("transcode_status")
	private Integer transcodeStatus;
	
	/**
	 * 直播状态
	 */
	@JsonProperty("live_status")
	private String liveStatus;
	
	/**
	 * 视频时长(单位:秒)
	 */
	@JsonProperty("real_duration")
	private Integer realDuration;
	
	/**
	 * 视频截图
	 */
	@JsonProperty("screen_shot")
	private String screenShot;
	
	/**
	 * 直播开始时间
	 */
	@JsonProperty("live_start_time")
	private String liveStartTime;
	
	/**
	 * 直播结束时间
	 */
	@JsonProperty("live_end_time")
	private String liveEndTime;
	
	/**
	 * 直播类型(1:限时直播;0:24小时直播)
	 */
	@JsonProperty("time_limit")
	private Integer timeLimit;
	
	/**
	 * 直转点状态(dealing:正在处理中;fail:直转点失败;success:直转点成功)
	 */
	@JsonProperty("live_vod_status")
	private Integer liveVodStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public Long getVv() {
		return vv;
	}

	public void setVv(Long vv) {
		this.vv = vv;
	}

	public Integer getDisplayable() {
		return displayable;
	}

	public void setDisplayable(Integer displayable) {
		this.displayable = displayable;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChannelSummary() {
		return channelSummary;
	}

	public void setChannelSummary(String channelSummary) {
		this.channelSummary = channelSummary;
	}

	public String getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public Integer getChannelType() {
		return channelType;
	}

	public void setChannelType(Integer channelType) {
		this.channelType = channelType;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getChannelWebId() {
		return channelWebId;
	}

	public void setChannelWebId(String channelWebId) {
		this.channelWebId = channelWebId;
	}

	public Integer getTranscodeStatus() {
		return transcodeStatus;
	}

	public void setTranscodeStatus(Integer transcodeStatus) {
		this.transcodeStatus = transcodeStatus;
	}

	public String getLiveStatus() {
		return liveStatus;
	}

	public void setLiveStatus(String liveStatus) {
		this.liveStatus = liveStatus;
	}

	public Integer getRealDuration() {
		return realDuration;
	}

	public void setRealDuration(Integer realDuration) {
		this.realDuration = realDuration;
	}

	public String getScreenShot() {
		return screenShot;
	}

	public void setScreenShot(String screenShot) {
		this.screenShot = screenShot;
	}

	public String getLiveStartTime() {
		return liveStartTime;
	}

	public void setLiveStartTime(String liveStartTime) {
		this.liveStartTime = liveStartTime;
	}

	public String getLiveEndTime() {
		return liveEndTime;
	}

	public void setLiveEndTime(String liveEndTime) {
		this.liveEndTime = liveEndTime;
	}

	public Integer getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}

	public Integer getLiveVodStatus() {
		return liveVodStatus;
	}

	public void setLiveVodStatus(Integer liveVodStatus) {
		this.liveVodStatus = liveVodStatus;
	}
}
