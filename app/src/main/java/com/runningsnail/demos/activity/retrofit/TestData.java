package com.runningsnail.demos.activity.retrofit;

import java.io.Serializable;

/**
 * @author yongjie created on 2020/10/25.
 */
public class TestData implements Serializable {


	/**
	 * status : 200
	 * content : helloWorld
	 */

	private int status;
	private String content;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("TestData{");
		sb.append("status=").append(status);
		sb.append(", content='").append(content).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
