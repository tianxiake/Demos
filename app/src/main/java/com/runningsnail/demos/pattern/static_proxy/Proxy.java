package com.runningsnail.demos.pattern.static_proxy;

/**
 * @author yongjie created on 2020-01-09.
 */
public class Proxy implements Subject {

	private Subject subject;

	public Proxy(Subject subject) {
		this.subject = subject;
	}

	@Override
	public String getName() {
		//加入拦截操作
		if ("小明".equals(subject.getName())) {
			return "我是小明";
		}
		return subject.getName();
	}

	@Override
	public int getAge() {
		return subject.getAge();
	}

	@Override
	public String getAddress() {
		return subject.getAddress();
	}
}
