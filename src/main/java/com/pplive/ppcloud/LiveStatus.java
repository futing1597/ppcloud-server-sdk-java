/**
 * 
 */
package com.pplive.ppcloud;

/**
 * @author chaogao
 *
 */
public enum LiveStatus {
	NOTSTART("notstart"),
	INIT("init"),
	PREVIEW("preview"),
	LIVING("living"),
	PAUSE("pause"),
	STOPPED("stopped");
	
	private String lstat;
	
	private LiveStatus(String lstat) {
		this.lstat = lstat;
	}
	
	@Override
    public String toString() {
        return this.lstat;
    }

    public boolean equals(String code) {
        return this.lstat.equals(code);
    }
}
