/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */

package com.pplive.ppcloud.response;

/**
 * @author chaogao
 *
 */
public class LiveWatchMediaChannelResponse {

	/**
	 * 开始时间
	 */
	private Long starttime;
	
	/**
	 * 结束时间
	 */
	private Long endtime;
	
	/**
	 * 直播流guid
	 */
	private String name;
	
	/**
	 * 直播流根路径
	 */
	private String path;
	
	/**
	 * 参数
	 */
	private String[] args;
	
	/**
	 * 码流率
	 */
	private Integer ft;
	
	/**
	 * 直播流状态
	 */
	private String streamstatus;
	
	/**
	 * 直播流服务器地址列表
	 */
	private String[] addr;
	
	/**
	 * bwt
	 */
	private Integer bwt;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String[] getArgs() {
		return args;
	}

	public void setArgs(String[] args) {
		this.args = args;
	}

	public Integer getFt() {
		return ft;
	}

	public void setFt(Integer ft) {
		this.ft = ft;
	}

	public String getStreamstatus() {
		return streamstatus;
	}

	public void setStreamstatus(String streamstatus) {
		this.streamstatus = streamstatus;
	}

	public String[] getAddr() {
		return addr;
	}

	public void setAddr(String[] addr) {
		this.addr = addr;
	}

	public Integer getBwt() {
		return bwt;
	}

	public void setBwt(Integer bwt) {
		this.bwt = bwt;
	}
}
