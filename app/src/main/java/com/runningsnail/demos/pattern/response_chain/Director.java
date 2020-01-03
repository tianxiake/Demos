package com.runningsnail.demos.pattern.response_chain;

/**
 * @author yongjie created on 2019-12-26.
 */
public class Director extends Leader {

	@Override
	public void process(int money) {
		System.out.println("director process:" + money);
	}

	@Override
	public int getLimit() {
		return 10000;
	}
}
