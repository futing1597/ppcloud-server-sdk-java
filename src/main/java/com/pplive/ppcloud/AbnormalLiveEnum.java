/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */
package com.pplive.ppcloud;

/**
 * Created by chaogao on 2016/12/6.
 */
public enum  AbnormalLiveEnum {
    FILTER("0"),
    FILTERCONTENT("1");

    private String abnormalLive;

    private AbnormalLiveEnum(String abnormalLive) {
        this.abnormalLive = abnormalLive;
    }

    @Override
    public String toString() {
        return this.abnormalLive;
    }

    public boolean equals(String code) {
        return this.abnormalLive.equals(code);
    }
}
