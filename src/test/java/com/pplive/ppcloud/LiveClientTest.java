/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */

package com.pplive.ppcloud;

import com.pplive.ppcloud.quick.model.LivePreviewInfoModel;
import com.pplive.ppcloud.quick.model.LivePushInfoModel;
import com.pplive.ppcloud.quick.model.LiveStatusInfoModel;
import com.pplive.ppcloud.utils.JsonUtils;
import com.pplive.ppcloud.utils.LogUtils;
import junit.framework.TestCase;

/**
 * LiveClient Tester.
 *
 * @author yancongliu
 * @version 1.0
 * @since <pre>10/11/2016</pre>
 */
public class LiveClientTest extends TestCase {

    public void testExample() throws Exception {
        String clientIP = "117.135.159.4";
        String title = "SDK 测试直播-" + System.currentTimeMillis();

        // 0. 初始化Client
        String accessKey = "替换您的 AccessKey";
        String secretKey = "替换您的 SecretKey";

        LiveClient client = new LiveClient(accessKey, secretKey);


        // 1. 创建直播
        LivePushInfoModel pushInfo = client.create(title, LiveMode.CAMERA.toString(), clientIP);
        LogUtils.log("create result=====================\n" + JsonUtils.toJsonString(pushInfo));

        // 2. 推流
        // 如使用OBS推流，分别配置URL和串码流为：pushInfo.getPushUrl()和pushInfo.getToken()

        // 3. 开始直播, 返回预览地址
        LivePreviewInfoModel previewInfo = client.start(pushInfo.getChannelWebId(), clientIP);
        LogUtils.log("start result=====================\n" + JsonUtils.toJsonString(previewInfo));

        // 4. 观看地址，返回观看地址
        LivePreviewInfoModel watchInfo = client.watch(pushInfo.getChannelWebId(), clientIP);
        LogUtils.log("watch result=====================\n" + JsonUtils.toJsonString(watchInfo));

        // 5. 查看直播状态，创建后随时可以查看直播状态
        LiveStatusInfoModel status = client.status(pushInfo.getChannelWebId(), clientIP);
        LogUtils.log("status result====================\n" + JsonUtils.toJsonString(status));

        // 6. 停止直播
        client.stop(pushInfo.getChannelWebId());
    }

} 
