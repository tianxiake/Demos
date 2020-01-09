package com.runningsnail.demos.pattern.dynamic_proxy;

/**
 * @author yongjie created on 2020-01-09.
 */
public class DynamicRealSubject implements DynamicSubject {

	@Override
	public void request(String name) {
		System.out.println("DynamicRealSubject");
	}
}
