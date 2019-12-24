package com.runningsnail.demos.pattern.command;

/**
 * @author yongjie created on 2019-12-24.
 */
public class CommandTwo implements Command {

	private CommandHandler commandHandler;

	public CommandTwo(CommandHandler commandHandler) {
		this.commandHandler = commandHandler;
	}

	@Override
	public void execute() {
		System.out.println("commandTwo");
		commandHandler.doAction();
	}
}
