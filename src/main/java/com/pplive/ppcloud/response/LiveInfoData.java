/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */

package com.pplive.ppcloud.response;

/**
 * @author chaogao
 *
 */
public class LiveInfoData extends BaseResponse {
	/**
	 * 数据实体
	 */
	private LiveInfoResponse data;

	public LiveInfoResponse getData() {
		return data;
	}

	public void setData(LiveInfoResponse data) {
		this.data = data;
	}
}
