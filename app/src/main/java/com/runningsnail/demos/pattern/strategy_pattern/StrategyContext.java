package com.runningsnail.demos.pattern.strategy_pattern;

/**
 * @author yongjie created on 2019-12-31.
 */
public class StrategyContext {

	private CalculateStrategy calculateStrategy;

	public void setCalculateStrategy(CalculateStrategy calculateStrategy) {
		this.calculateStrategy = calculateStrategy;
	}


	public int calculatePrice(int km) {
		return calculateStrategy.calculatePrice(km);
	}
}
