package com.pplive.ppcloud;

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
		LivePreviewInfoModel lPreviewInfoModel = LiveStartManager.getInstance().start("0a2dnqyboKChoquL4K2hoqrhoaSioaucqQ");
		LogUtils.log(String.format("start: %s", JsonUtils.toJsonString(lPreviewInfoModel)));
	}

	/**
	 * 获取直播播放地址
	 * 替换IP 117.135.159.4 为本机出口IP
	 */
	public void testWatch()
    {
    	LivePreviewInfoModel lPreviewInfoModel = LiveWatchManager.getInstance().watch("0a2dnqyboKChoquL4K2hoqrhoaSioaucqQ", "117.135.159.4");
		LogUtils.log(String.format("watch: %s", JsonUtils.toJsonString(lPreviewInfoModel)));
    }

	/**
	 * 停止直播
	 */
	public void testStop()
    {
		LiveStopManager.getInstance().stop("0a2dnqyaqaijmqmL4K2hoqrhoaSioaeXqA");
	}

	/**
	 * 获取直播状态
	 * 替换IP 117.135.159.4 为本机出口IP
	 */
	public void testStatus()
    {
		LiveStatusManager.getInstance().status("0a2dnqyaqaijmqmL4K2hoqrhoaSioaeXqA", "117.135.159.4");
	}
}
