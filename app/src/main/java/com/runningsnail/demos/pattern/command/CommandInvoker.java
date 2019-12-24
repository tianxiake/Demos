package com.runningsnail.demos.pattern.command;

/**
 * @author yongjie created on 2019-12-24.
 */
public class CommandInvoker {

	private Command command;

	public CommandInvoker(Command command) {
		this.command = command;
	}

	public void invoke() {
		command.execute();
	}
}
