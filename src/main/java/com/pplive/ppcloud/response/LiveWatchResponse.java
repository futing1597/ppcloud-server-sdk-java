/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */

package com.pplive.ppcloud.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author chaogao
 *
 */
public class LiveWatchResponse extends BaseResponse {

	/**
	 * 播放方式数组
	 */
	private LiveWatchMediaResponse[] medias;
	
	/**
	 * 推荐直播流方式
	 */
	private LiveWatchMediaRecommendResponse recommend;
	
	/**
	 * Logo
	 */
	private LiveLogoResponse logo;
	
	/**
	 * 封面图片
	 */
	private String coverimg;
	
	/**
	 * 频道id
	 */
	private String ppyunid;
	
	/**
	 * cpn
	 */
	private String cpn;

	/**
	 * 视频类型
	 */
	@JsonProperty("ydpf_pt")
	private String ydpfPt;

	public LiveWatchMediaResponse[] getMedias() {
		return medias;
	}

	public void setMedias(LiveWatchMediaResponse[] medias) {
		this.medias = medias;
	}

	public LiveWatchMediaRecommendResponse getRecommend() {
		return recommend;
	}

	public void setRecommend(LiveWatchMediaRecommendResponse recommend) {
		this.recommend = recommend;
	}

	public LiveLogoResponse getLogo() {
		return logo;
	}

	public void setLogo(LiveLogoResponse logo) {
		this.logo = logo;
	}

	public String getCoverimg() {
		return coverimg;
	}

	public void setCoverimg(String coverimg) {
		this.coverimg = coverimg;
	}

	public String getPpyunid() {
		return ppyunid;
	}

	public void setPpyunid(String ppyunid) {
		this.ppyunid = ppyunid;
	}

	public String getCpn() {
		return cpn;
	}

	public void setCpn(String cpn) {
		this.cpn = cpn;
	}

	public String getYdpfPt() {
		return ydpfPt;
	}

	public void setYdpfPt(String ydpfPt) {
		this.ydpfPt = ydpfPt;
	}
}
