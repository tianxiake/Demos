package com.runningsnail.demos.pattern.command;

/**
 * @author yongjie created on 2019-12-24.
 */
public class CommandTestClient {
	public static void main(String[] args) {
		CommandHandler commandHandler = new CommandHandler();
		CommandTwo commandOne = new CommandTwo(commandHandler);

		CommandInvoker commandInvoker = new CommandInvoker(commandOne);
		commandInvoker.invoke();
	}
}
