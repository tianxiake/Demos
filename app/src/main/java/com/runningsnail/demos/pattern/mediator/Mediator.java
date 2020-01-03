package com.runningsnail.demos.pattern.mediator;

/**
 * @author yongjie created on 2020-01-02.
 */
public class Mediator {

	private NodeOne nodeOne;
	private NodeTwo nodeTwo;

	public Mediator() {
	}

	public NodeOne getNodeOne() {
		return nodeOne;
	}

	public void setNodeOne(NodeOne nodeOne) {
		this.nodeOne = nodeOne;
	}

	public NodeTwo getNodeTwo() {
		return nodeTwo;
	}

	public void setNodeTwo(NodeTwo nodeTwo) {
		this.nodeTwo = nodeTwo;
	}

	public void doSomething() {
		nodeTwo.doSomething();
	}

	public void doAnotherthing() {
		nodeOne.doSomething();
	}
}
