package com.runningsnail.demos.pattern.static_proxy;

/**
 * @author yongjie created on 2020-01-09.
 */
public class RealSubject implements Subject {

	@Override
	public String getName() {
		return "MyName";
	}

	@Override
	public int getAge() {
		return 24;
	}

	@Override
	public String getAddress() {
		return "unknown";
	}
}
