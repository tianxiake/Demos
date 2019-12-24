package com.runningsnail.demos.pattern.command;

/**
 * @author yongjie created on 2019-12-24.
 */
public class CommandOne implements Command {
	private CommandHandler handler;

	public CommandOne(CommandHandler handler) {
		this.handler = handler;
	}

	@Override
	public void execute() {
		System.out.println("commandOne");
		handler.doAction();

	}
}
