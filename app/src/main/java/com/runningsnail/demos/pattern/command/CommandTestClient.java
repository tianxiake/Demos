package com.runningsnail.demos.pattern.command;

/**
 * 命令模式
 * 命令的调用者只是知道调用了一个命令但是不知道这个命令的处理者是谁
 * 解耦了命令的调用者和命令的处理者
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
