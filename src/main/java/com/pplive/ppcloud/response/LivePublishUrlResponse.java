/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */

package com.pplive.ppcloud.response;

/**
 * @author chaogao
 *
 */
public class LivePublishUrlResponse extends BaseResponse {
	
	/**
	 * 流名称
	 */
	private String name;
	
	/**
	 * 推流方式(push,publish)
	 */
	private String path;
	
	/**
	 * 协议
	 */
	private String protocol;
	
	/**
	 * 参数
	 */
	private String[] args;
	
	/**
	 * 频道id
	 */
	private Long channelid;
	
	/**
	 * 当前时间戳
	 */
	private Long now;
	
	/**
	 * 收流地址
	 */
	private String[] addr;
	
	/**
	 * 视频webid
	 */
	private String publishCode;
	
	/**
	 * Logo
	 */
	private LiveLogoResponse logo;

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

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String[] getArgs() {
		return args;
	}

	public void setArgs(String[] args) {
		this.args = args;
	}

	public Long getChannelid() {
		return channelid;
	}

	public void setChannelid(Long channelid) {
		this.channelid = channelid;
	}

	public Long getNow() {
		return now;
	}

	public void setNow(Long now) {
		this.now = now;
	}

	public String[] getAddr() {
		return addr;
	}

	public void setAddr(String[] addr) {
		this.addr = addr;
	}

	public String getPublishCode() {
		return publishCode;
	}

	public void setPublishCode(String publishCode) {
		this.publishCode = publishCode;
	}

	public LiveLogoResponse getLogo() {
		return logo;
	}

	public void setLogo(LiveLogoResponse logo) {
		this.logo = logo;
	}
}
