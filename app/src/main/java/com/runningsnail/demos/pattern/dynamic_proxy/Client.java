package com.runningsnail.demos.pattern.dynamic_proxy;

import java.lang.reflect.Proxy;

/**
 * @author yongjie created on 2020-01-09.
 */
public class Client {

	public static void main(String[] args) {
		DynamicSubject dynamicSubject = new DynamicRealSubject();
		InvocationHandlerImpl invocationHandler = new InvocationHandlerImpl(dynamicSubject);
		DynamicSubject subject = (DynamicSubject) Proxy.newProxyInstance(DynamicSubject.class.getClassLoader(), new Class[]{DynamicSubject.class}, invocationHandler);
		subject.request("HelloWorld");
	}
}
