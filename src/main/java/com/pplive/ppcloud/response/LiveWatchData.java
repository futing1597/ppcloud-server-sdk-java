/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */

package com.pplive.ppcloud.response;

/**
 * @author chaogao
 *
 */
public class LiveWatchData extends BaseResponse {

	/**
	 * 数据实体
	 */
	private LiveWatchResponse data;

	public LiveWatchResponse getData() {
		return data;
	}

	public void setData(LiveWatchResponse data) {
		this.data = data;
	}
}
