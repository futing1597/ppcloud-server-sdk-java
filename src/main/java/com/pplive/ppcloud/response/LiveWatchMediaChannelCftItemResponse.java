/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */
package com.pplive.ppcloud.response;

/**
 * @author chaogao
 *
 */
public class LiveWatchMediaChannelCftItemResponse {
	
	/**
	 * 码流率
	 */
	private Integer ft;
	
	/**
	 * 码流率唯一标识
	 */
	private String name;
	
	/**
	 * 码流率中文标签
	 */
	private String ft_cn;

	public Integer getFt() {
		return ft;
	}

	public void setFt(Integer ft) {
		this.ft = ft;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFt_cn() {
		return ft_cn;
	}

	public void setFt_cn(String ft_cn) {
		this.ft_cn = ft_cn;
	}
}
