package com.runningsnail.demos.activity.rxjava.entity;
/**
 * @author lyj on 2021/1/17
 */
public class VodDetail {
	private String  code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("VodDetail{");
		sb.append("code='").append(code).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
