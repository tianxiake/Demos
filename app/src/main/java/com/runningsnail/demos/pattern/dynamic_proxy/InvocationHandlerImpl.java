package com.runningsnail.demos.pattern.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

import io.reactivex.subjects.Subject;

/**
 * @author yongjie created on 2020-01-09.
 */
public class InvocationHandlerImpl implements InvocationHandler {


	private DynamicSubject subject;

	public InvocationHandlerImpl(DynamicSubject subject) {
		this.subject = subject;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Class<?> declaringClass = method.getDeclaringClass();
		System.out.println("declaringClass:" + declaringClass.getName());
		System.out.println("proxy:" + proxy.getClass().getName());
		System.out.println(method.getName());
		System.out.println(Arrays.toString(args));
		Object invoke = method.invoke(subject, args);
		return invoke;
	}
}
