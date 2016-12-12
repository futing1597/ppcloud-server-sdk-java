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
public class LiveInfoRequest {
	
	/**
	 * 视频webid
	 */
	@JsonProperty("channel_web_id")
	private String channelWebId;
	
	/**
	 * 搜索关键字
	 */
	@JsonProperty("key")
	private String key;
	
	/**
	 * 视频类型
	 */
	@JsonProperty("channel_type")
	private String channelType;
	
	/**
	 * 直播状态
	 */
	@JsonProperty("live_status")
	private String liveStatus;
	
	/**
	 * 页码
	 */
	@JsonProperty("page_num")
	private Integer pageNum;
	
	/**
	 * 每页记录数量
	 */
	@JsonProperty("page_size")
	private Integer pageSize;

	/**
	 * 大于记录时间
	 */
	@JsonProperty("create_time_start")
	private Long createTimeStart;

	/**
	 * 小于记录时间
	 */
	@JsonProperty("create_time_end")
	private Long createTimeEnd;

	/**
	 * 转码状态
	 */
	@JsonProperty("transcode_status")
	private String transcodeStatus;

	/**
	 * 过滤异常直播和直转点记录
	 * null: 无操作
	 * 0: 过滤直播直转点失败和不正常视频
	 * 1: 返回需要过滤的直播直转点失败和不正常视频
	 */
	@JsonProperty("abnormal_live")
	private String abnormalLive;

	public String getChannelWebId() {
		return channelWebId;
	}

	public void setChannelWebId(String channelWebId) {
		this.channelWebId = channelWebId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public String getLiveStatus() {
		return liveStatus;
	}

	public void setLiveStatus(String liveStatus) {
		this.liveStatus = liveStatus;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(Long createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public Long getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Long createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public String getTranscodeStatus() {
		return transcodeStatus;
	}

	public void setTranscodeStatus(String transcodeStatus) {
		this.transcodeStatus = transcodeStatus;
	}

	public String getAbnormalLive() {
		return abnormalLive;
	}

	public void setAbnormalLive(String abnormalLive) {
		this.abnormalLive = abnormalLive;
	}
}
