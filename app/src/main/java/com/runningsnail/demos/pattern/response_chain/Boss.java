package com.runningsnail.demos.pattern.response_chain;

/**
 * @author yongjie created on 2019-12-26.
 */
public class Boss extends Leader {
	@Override
	public void process(int money) {
		System.out.println("boss process:" + money);
	}

	@Override
	public int getLimit() {
		return Integer.MAX_VALUE;
	}
}
