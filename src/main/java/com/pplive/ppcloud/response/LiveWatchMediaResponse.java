/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */

package com.pplive.ppcloud.response;

/**
 * @author chaogao
 *
 */
public class LiveWatchMediaResponse {

	/**
	 * 协议(live2,rtmp)
	 */
	private String protocol;
	
	/**
	 * 延迟
	 */
	private Integer delay;
	
	/**
	 * pid
	 */
	private Integer pid;
	
	/**
	 * 开始时间
	 */
	private Long starttime;
	
	/**
	 * 结束时间
	 */
	private Long endtime;
	
	/**
	 * 当前时间
	 */
	private Long now;
	
	/**
	 * 间隔
	 */
	private Integer interval;
	
	/**
	 * 播放地址信息列表
	 */
	private LiveWatchMediaChannelResponse[] channels;

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public Integer getDelay() {
		return delay;
	}

	public void setDelay(Integer delay) {
		this.delay = delay;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Long getStarttime() {
		return starttime;
	}

	public void setStarttime(Long starttime) {
		this.starttime = starttime;
	}

	public Long getEndtime() {
		return endtime;
	}

	public void setEndtime(Long endtime) {
		this.endtime = endtime;
	}

	public Long getNow() {
		return now;
	}

	public void setNow(Long now) {
		this.now = now;
	}

	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}

	public LiveWatchMediaChannelResponse[] getChannels() {
		return channels;
	}

	public void setChannels(LiveWatchMediaChannelResponse[] channels) {
		this.channels = channels;
	}
}
