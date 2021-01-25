package com.runningsnail.demos.entity;

/**
 * @author yongjie created on 2021/1/19.
 */
public class Channel {


	/**
	 * channel : 哈哈
	 */

	private String channel;

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Channel{");
		sb.append("channel='").append(channel).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
