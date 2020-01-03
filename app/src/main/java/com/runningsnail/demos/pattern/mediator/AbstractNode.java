package com.runningsnail.demos.pattern.mediator;

/**
 * @author yongjie created on 2020-01-02.
 */
public abstract class AbstractNode {

	protected Mediator mediator;

	public AbstractNode(Mediator mediator) {
		this.mediator = mediator;
	}

	public abstract void doSomething();
}
