/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */

package com.pplive.ppcloud.response;

/**
 * @author chaogao
 *
 */
public class LiveStatusData extends BaseResponse {
	/**
	 * 数据实体
	 */
	private LiveStatusResponse data;

	public LiveStatusResponse getData() {
		return data;
	}

	public void setData(LiveStatusResponse data) {
		this.data = data;
	}
}
