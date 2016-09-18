package com.pplive.ppcloud;

public enum PlayDeviceType {
	WEB_CLOUDPLAY("web.cloudplay"),
	CLIENT_CLOUDPLAY("client.cloudplay"),
	PHONE_IOS_CLOUDPLAY("phone.ios.cloudplay"),
	PHONE_ANDROID_CLOUDPLAY("phone.android.cloudplay");
	
	private String ptype;
	
	private PlayDeviceType(String ptype) {
		this.ptype = ptype;
	}
	
	@Override
    public String toString() {
        return this.ptype;
    }

    public boolean equals(String code) {
        return this.ptype.equals(code);
    }
}
