/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */
package com.pplive.ppcloud;

/**
 * Created by chaogao on 2016/10/11.
 */
public enum  TranscodeStatus {
    CREATE("0"),
    WAITTRANSCODE("101"),
    TRANSCODING("102"),
    PLAYABLE("200");

    private String transStatus;

    private TranscodeStatus(String transStatus) {
        this.transStatus = transStatus;
    }

    @Override
    public String toString() {
        return this.transStatus;
    }

    public boolean equals(String code) {
        return this.transStatus.equals(code);
    }
}
