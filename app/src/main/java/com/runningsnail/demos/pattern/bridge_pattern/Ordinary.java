package com.runningsnail.demos.pattern.bridge_pattern;

/**
 * @author yongjie created on 2020-01-09.
 */
public class Ordinary implements CoffeeAdditives {
	@Override
	public String doSomeThing() {
		return "原味";
	}
}
