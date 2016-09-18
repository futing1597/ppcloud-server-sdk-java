package com.pplive.ppcloud;

public enum ChannelType {
	VOD(1),
	LIVE(2);
	
	private Integer ctype;
	
	private ChannelType(Integer ctype) {
		this.ctype = ctype;
	}
	
	@Override
    public String toString() {
        return this.ctype.toString();
    }

    public boolean equals(Integer code) {
        return this.ctype.equals(code);
    }
}
