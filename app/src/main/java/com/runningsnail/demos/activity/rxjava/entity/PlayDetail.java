package com.runningsnail.demos.activity.rxjava.entity;
/**
 * @author lyj on 2021/1/17
 */

/**
 * @author wyb
 */
public class PlayDetail {
	private String  playUrl;

	public String getPlayUrl() {
		return playUrl;
	}

	public void setPlayUrl(String playUrl) {
		this.playUrl = playUrl;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("PlayDetail{");
		sb.append("playUrl='").append(playUrl).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
