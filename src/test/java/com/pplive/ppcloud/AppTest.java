package com.pplive.ppcloud;

import java.net.URISyntaxException;

import com.pplive.ppcloud.quick.LiveCreateManager;
import com.pplive.ppcloud.quick.LiveStartManager;
import com.pplive.ppcloud.quick.LiveStatusManager;
import com.pplive.ppcloud.quick.LiveStopManager;
import com.pplive.ppcloud.quick.LiveWatchManager;
import com.pplive.ppcloud.quick.model.LivePreviewInfoModel;
import com.pplive.ppcloud.quick.model.LivePushInfoModel;
import com.pplive.ppcloud.utils.JsonUtils;
import com.pplive.ppcloud.utils.LogUtils;

import junit.framework.TestCase;

public class AppTest 
    extends TestCase
{
    
    public void testCreate()
    {
    	try {
			LivePushInfoModel lPushInfoModel = LiveCreateManager.getInstance().create(LiveMode.XSPLIT.toString(), "SDKTest_quick_001", "117.135.159.4");
			LogUtils.log(String.format("create: %s", JsonUtils.toJsonString(lPushInfoModel)));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
    }
    
    public void testStart()
    {
    	try {
			LivePreviewInfoModel lPreviewInfoModel = LiveStartManager.getInstance().start("0a2dnq6aoaSgm6yL4K2dnqfhoaajma6cow", "117.135.159.4");
			LogUtils.log(String.format("start: %s", JsonUtils.toJsonString(lPreviewInfoModel)));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
    }
    
    public void testWatch()
    {
    	LivePreviewInfoModel lPreviewInfoModel = LiveWatchManager.getInstance().watchForNickname("SDKTest_quick_001", "117.135.159.4");
		LogUtils.log(String.format("watch: %s", JsonUtils.toJsonString(lPreviewInfoModel)));
    }
    
    public void testStop()
    {
    	try {
			LiveStopManager.getInstance().stop("0a2dnq6aoaSgm6yL4K2dnqfhoaajma6cow");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
    }
    
    public void testStatus()
    {
    	try {
			LiveStatusManager.getInstance().status("0a2dnq6aoaSgm6yL4K2dnqfhoaajma6cow", "117.135.159.4");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
    }
}
