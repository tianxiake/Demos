package com.runningsnail.demos.pattern.strategy_pattern;

/**
 * @author yongjie created on 2019-12-31.
 */
public class Client {

	public static void main(String[] args) {

		StrategyContext strategyContext = new StrategyContext();
		strategyContext.setCalculateStrategy(new BusPrice());
		int busPrice = strategyContext.calculatePrice(20);
		System.out.println("busPrice:" + busPrice);
	}
}
