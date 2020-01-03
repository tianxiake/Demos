package com.runningsnail.demos.pattern.mediator;

/**
 * @author yongjie created on 2020-01-02.
 */
public class Client {

	public static void main(String[] args) {
		Mediator mediator = new Mediator();
		NodeOne nodeOne = new NodeOne(mediator);

		NodeTwo nodeTwo = new NodeTwo(mediator);

		mediator.setNodeOne(nodeOne);
		mediator.setNodeTwo(nodeTwo);
		String type = "a";

		if ("a".equals(type)) {
			nodeOne.doSomething();
		} else {
			nodeTwo.doSomething();
		}
	}
}
