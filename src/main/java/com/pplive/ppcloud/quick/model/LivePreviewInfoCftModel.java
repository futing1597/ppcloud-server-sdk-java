/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */
package com.pplive.ppcloud.quick.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author chaogao
 *
 */
public class LivePreviewInfoCftModel {

    /**
     * rtmp 播放地址
     */
    @JsonProperty("rtmp_url")
    private String rtmpUrl;

    /**
     * 码流率
     */
    private Integer ft;

    /**
     * 码流率唯一标识
     */
    private String name;

    /**
     * 码流率中文标签
     */
    @JsonProperty("ft_cn")
    private String ftCn;

    public String getRtmpUrl() {
        return rtmpUrl;
    }

    public void setRtmpUrl(String rtmpUrl) {
        this.rtmpUrl = rtmpUrl;
    }

    public Integer getFt() {
        return ft;
    }

    public void setFt(Integer ft) {
        this.ft = ft;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getftCn() {
        return ftCn;
    }

    public void setftCn(String ftCn) {
        this.ftCn = ftCn;
    }
}
