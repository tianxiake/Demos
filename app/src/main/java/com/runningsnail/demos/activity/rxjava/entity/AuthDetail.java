package com.runningsnail.demos.activity.rxjava.entity;
/**
 * @author lyj on 2021/1/17
 */
public class AuthDetail {
	private String  result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("AuthDetail{");
		sb.append("result='").append(result).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
