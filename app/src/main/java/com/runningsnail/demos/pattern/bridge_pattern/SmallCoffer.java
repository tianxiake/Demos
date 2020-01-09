package com.runningsnail.demos.pattern.bridge_pattern;

/**
 * @author yongjie created on 2020-01-09.
 */
public class SmallCoffer extends Coffee {

	public SmallCoffer(CoffeeAdditives coffeeAdditives) {
		super(coffeeAdditives);
	}

	@Override
	public void makeCoffee() {
		String s = coffeeAdditives.doSomeThing();
		System.out.println(s + "coffee");
	}
}
