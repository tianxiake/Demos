package com.runningsnail.demos.pattern.response_chain;

/**
 * @author yongjie created on 2019-12-26.
 */
public abstract class Leader {
	protected Leader nextLeader;


	public final void handleRequest(int money) {
		if (money <= getLimit()) {
			process(money);
		} else {
			if (nextLeader != null) {
				nextLeader.handleRequest(money);
			}
		}
	}

	public abstract void process(int money);

	public abstract int getLimit();
}
