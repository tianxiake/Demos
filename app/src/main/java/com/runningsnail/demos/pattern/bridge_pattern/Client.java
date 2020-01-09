package com.runningsnail.demos.pattern.bridge_pattern;

/**
 * @author yongjie created on 2020-01-09.
 */
public class Client {

	public static void main(String[] args) {
		Sugar sugar = new Sugar();
		Ordinary ordinary = new Ordinary();

		LagerCoffee lagerCoffee = new LagerCoffee(sugar);
		lagerCoffee.makeCoffee();


		LagerCoffee lagerCoffee1 = new LagerCoffee(ordinary);
		lagerCoffee1.makeCoffee();
	}
}
