/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */

package com.pplive.ppcloud;

/**
 * @author chaogao
 *
 */
public enum LiveMode {
	CAMERA("camera"),
	XSPLIT("xsplit"),
	RTMP("rtmp");
	
	private String mode;
	
	private LiveMode(String mode) {
		this.mode = mode;
	}
	
	@Override
    public String toString() {
        return this.mode;
    }

    public boolean equals(String code) {
        return this.mode.equals(code);
    }
}
