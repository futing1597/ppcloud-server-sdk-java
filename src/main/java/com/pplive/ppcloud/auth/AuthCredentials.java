/**
 * PPLive Inc.
 * Copyright (c) 2007-2016 All Rights Reserved.
 */

package com.pplive.ppcloud.auth;

/**
 * @author chaogao
 *
 */
public class AuthCredentials {
	
	/**
	 * AK
	 * 由PP云发放
	 */
	private String accessKey;
	
	/**
	 * SK
	 * 由PP云发放
	 */
	private String secretKey;
	
	public String getAccessKey() {
		return accessKey;
	}
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
}
