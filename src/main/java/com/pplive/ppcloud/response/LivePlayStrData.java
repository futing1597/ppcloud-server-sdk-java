/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */

package com.pplive.ppcloud.response;

import com.pplive.ppcloud.quick.model.LivePreviewInfoModel;

/**
 * @author chaogao
 *
 */
public class LivePlayStrData extends BaseResponse {

    private LivePreviewInfoModel data;

    public LivePreviewInfoModel getData() {
        return data;
    }

    public void setData(LivePreviewInfoModel data) {
        this.data = data;
    }
}
