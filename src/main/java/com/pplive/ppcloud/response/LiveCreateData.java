/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */

package com.pplive.ppcloud.response;

/**
 * @author chaogao
 *
 */
public class LiveCreateData extends BaseResponse {
	
	/**
	 * 数据实体
	 */
	private LiveCreateResponse data;

	public LiveCreateResponse getData() {
		return data;
	}

	public void setData(LiveCreateResponse data) {
		this.data = data;
	}
}
