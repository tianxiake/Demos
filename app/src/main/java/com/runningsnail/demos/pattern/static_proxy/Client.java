package com.runningsnail.demos.pattern.static_proxy;


/**
 * @author yongjie created on 2020-01-09.
 */
public class Client {
	public static void main(String[] args) {

		RealSubject realSubject = new RealSubject();
		Proxy proxy = new Proxy(realSubject);
		System.out.println();
	}
}
