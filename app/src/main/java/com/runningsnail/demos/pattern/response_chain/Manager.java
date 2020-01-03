package com.runningsnail.demos.pattern.response_chain;

/**
 * @author yongjie created on 2019-12-26.
 */
public class Manager extends Leader {
	@Override
	public void process(int money) {
		System.out.println("manager process:" + money);
	}

	@Override
	public int getLimit() {
		return 50000;
	}
}
