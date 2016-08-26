/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */

package com.pplive.ppcloud.response;

/**
 * @author chaogao
 *
 */
public class LivePublishUrlData extends BaseResponse {
	/**
	 * 数据实体
	 */
	private LivePublishUrlResponse data;

	public LivePublishUrlResponse getData() {
		return data;
	}

	public void setData(LivePublishUrlResponse data) {
		this.data = data;
	}
}
