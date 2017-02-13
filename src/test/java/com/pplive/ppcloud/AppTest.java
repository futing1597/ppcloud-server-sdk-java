package com.pplive.ppcloud;

import com.pplive.ppcloud.http.HttpProxyConfig;
import com.pplive.ppcloud.live.LiveManager;
import com.pplive.ppcloud.quick.*;
import com.pplive.ppcloud.quick.model.LivePreviewInfoModel;
import com.pplive.ppcloud.quick.model.LivePushInfoModel;
import com.pplive.ppcloud.utils.JsonUtils;
import com.pplive.ppcloud.utils.LogUtils;
import junit.framework.TestCase;

public class AppTest 
    extends TestCase
{

	/**
	 * 设置代理
	 */
	public void testProxy()
	{
		LiveManager.getInstance().setProxyConfig(new HttpProxyConfig("127.0.0.1", 1089));
//		LiveManager.getInstance().setProxyConfig(null);
	}

	/**
	 * 创建直播
	 * 替换IP 117.135.159.4 为本机出口IP
	 */
	public void testCreate()
    {
		LivePushInfoModel lPushInfoModel = LiveCreateManager.getInstance().create(LiveMode.CAMERA.toString(), "117.135.159.4");
		LogUtils.log(String.format("create: %s", JsonUtils.toJsonString(lPushInfoModel)));
	}

	/**
	 * 开始直播
	 */
	public void testStart()
    {
		LivePreviewInfoModel lPreviewInfoModel = LiveStartManager.getInstance().start("0a2dnq6bp6GjnKmL4K2dnqfhoamknK2YpQ");
		LogUtils.log(String.format("start: %s", JsonUtils.toJsonString(lPreviewInfoModel)));
	}

	/**
	 * 获取播放地址
	 * 替换IP 117.135.159.4 为本机出口IP
	 */
	public void testGetPlayStr()
    {
    	LivePreviewInfoModel lPreviewInfoModel = LivePlayStrManager.getInstance().getPlayStr("0a2dnqyboKOkm62L4K2km6nhoaikm6iWow", "117.135.159.4", 60*12);
		LogUtils.log(String.format("getPlayStr: %s", JsonUtils.toJsonString(lPreviewInfoModel)));
    }

	/**
	 * 停止直播
	 */
	public void testStop()
    {
		LiveStopManager.getInstance().stop("0a2dnq6bp6GkmayL4K2dnqfhoamknK6cow");
	}

	/**
	 * 获取直播状态
	 * 替换IP 117.135.159.4 为本机出口IP
	 */
	public void testStatus()
    {
		LiveStatusManager.getInstance().status("0a2dnq6bp6GkmayL4K2dnqfhoamknK6cow", "117.135.159.4");
	}

	/**
	 * 更改直播状态
	 */
	public void testStatusChange()
	{
		LiveStatusControllManager.getInstance().statusChange("0a2dnq6bp6GjnKmL4K2dnqfhoamknK2YpQ", LiveStatus.STOPPED.toString());
	}
}
