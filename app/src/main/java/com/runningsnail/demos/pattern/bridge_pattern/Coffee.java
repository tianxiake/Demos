package com.runningsnail.demos.pattern.bridge_pattern;

/**
 * @author yongjie created on 2020-01-09.
 */
public abstract class Coffee {

	protected CoffeeAdditives coffeeAdditives;

	public Coffee(CoffeeAdditives coffeeAdditives) {
		this.coffeeAdditives = coffeeAdditives;
	}

	public abstract void makeCoffee();

}
