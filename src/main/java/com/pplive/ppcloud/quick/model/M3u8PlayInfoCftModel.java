/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */
package com.pplive.ppcloud.quick.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by chaogao on 2017/2/7.
 */
public class M3u8PlayInfoCftModel {

    /**
     * m3u8 播放地址
     */
    @JsonProperty("m3u8_url")
    private String m3u8Url;

    /**
     * 码流率
     */
    private Integer ft;

    public String getM3u8Url() {
        return m3u8Url;
    }

    public void setM3u8Url(String m3u8Url) {
        this.m3u8Url = m3u8Url;
    }

    public Integer getFt() {
        return ft;
    }

    public void setFt(Integer ft) {
        this.ft = ft;
    }
}
