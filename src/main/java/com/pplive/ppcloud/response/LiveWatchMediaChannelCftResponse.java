/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */
package com.pplive.ppcloud.response;

/**
 * @author chaogao
 *
 */
public class LiveWatchMediaChannelCftResponse {
	
	/**
	 * 当前清晰度
	 */
	private Integer cur;
	
	/**
	 * 多码流描述
	 */
	private LiveWatchMediaChannelCftItemResponse[] item;

	public Integer getCur() {
		return cur;
	}

	public void setCur(Integer cur) {
		this.cur = cur;
	}

	public LiveWatchMediaChannelCftItemResponse[] getItem() {
		return item;
	}

	public void setItem(LiveWatchMediaChannelCftItemResponse[] item) {
		this.item = item;
	}
	
	
}
