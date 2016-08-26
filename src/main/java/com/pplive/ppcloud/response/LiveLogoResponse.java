/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */

package com.pplive.ppcloud.response;

/**
 * @author chaogao
 *
 */
public class LiveLogoResponse {

	/**
	 * logo是否显示
	 */
	private Integer isTurnOn;
	
	/**
	 * logo链接
	 */
	private String url;
	
	/**
	 * logo位置
	 */
	private String align;
	
	/**
	 * 透明度
	 */
	private Integer diaphaneity;
	
	/**
	 * ax
	 */
	private Double ax;
	
	/**
	 * ay
	 */
	private Double ay;
	
	/**
	 * logo 宽度
	 */
	private Double width;

	public Integer getIsTurnOn() {
		return isTurnOn;
	}

	public void setIsTurnOn(Integer isTurnOn) {
		this.isTurnOn = isTurnOn;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public Integer getDiaphaneity() {
		return diaphaneity;
	}

	public void setDiaphaneity(Integer diaphaneity) {
		this.diaphaneity = diaphaneity;
	}

	public Double getAx() {
		return ax;
	}

	public void setAx(Double ax) {
		this.ax = ax;
	}

	public Double getAy() {
		return ay;
	}

	public void setAy(Double ay) {
		this.ay = ay;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}
}
