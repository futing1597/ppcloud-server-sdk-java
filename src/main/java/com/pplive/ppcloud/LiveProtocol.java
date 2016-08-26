package com.pplive.ppcloud;

public enum LiveProtocol {
	RTMP("rtmp"),
	LIVE2("live2");
	
	private String protocol;
	
	private LiveProtocol(String protocol) {
		this.protocol = protocol;
	}
	
	@Override
    public String toString() {
        return this.protocol;
    }

    public boolean equals(String code) {
        return this.protocol.equals(code);
    }
}
