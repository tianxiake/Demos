package com.runningsnail.demos.pattern.mediator;

/**
 * @author yongjie created on 2020-01-02.
 */
public class NodeTwo extends AbstractNode {

	public NodeTwo(Mediator mediator) {
		super(mediator);
	}

	@Override
	public void doSomething() {
		mediator.doAnotherthing();
	}
}
