/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */
package com.pplive.ppcloud.response;

/**
 * @author chaogao
 *
 */
public class LiveInfoListData extends BaseResponse {
	/**
	 * 数据实体
	 */
	private LiveInfoResponse[] data;

	/**
	 * 记录总数
	 */
	public Integer totalnum;

	public LiveInfoResponse[] getData() {
		return data;
	}

	public void setData(LiveInfoResponse[] data) {
		this.data = data;
	}

	public Integer getTotalnum() {
		return totalnum;
	}

	public void setTotalnum(Integer totalnum) {
		this.totalnum = totalnum;
	}
}
