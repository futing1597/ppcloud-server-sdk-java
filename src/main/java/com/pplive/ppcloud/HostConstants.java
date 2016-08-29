/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */

package com.pplive.ppcloud;

/**
 * @author chaogao
 *
 */
public class HostConstants {
	
	/**
	 * 服务域名 
	 */
	public static final String HOST_URL = "http://svc.pptvyun.com";
	
	/**
	 * m3u8播放地址
	 */
	public static final String M3U8_PLAY_URL = "http://player.pptvyun.com/svc/m3u8player/pl/%s.m3u8";
	
	/**
	 * 创建直播接口
	 */
	public static final String CREATE_LIVE_URL = "/svc/api3/live";
	
	/**
	 * 修改直播详情接口
	 */
	public static final String UPDATE_LIVE_URL = "/svc/api3/live/%s";
	
	/**
	 * 获取直播状态接口
	 */
	public static final String GET_LIVE_STATUS_URL = "/svc/api3/live/%s/livestatus";
	
	/**
	 * 直播状态控制接口
	 */
	public static final String CONTROL_LIVE_STATUS_URL = "/svc/api3/live/%s/livestatus";
	
	/**
	 * 请求推流地址接口
	 */
	public static final String GET_LIVE_PUBLISH_URL = "/svc/api3/live/%s/publish";
	
	/**
	 * Watch接口
	 */
	public static final String WATCH_LIVE_URL = "/svc/api3/live/%s/watch";
	
	/**
	 * Preview接口
	 */
	public static final String PREVIEW_LIVE_URL = "/svc/api3/live/%s/preview";
	
	/**
	 * 获取单个视频接口
	 */
	public static final String GET_SINGLE_LIVE_URL = "/svc/api3/channel/%s";
}
