package com.runningsnail.demos.pattern.strategy_pattern;

/**
 * @author yongjie created on 2019-12-31.
 */
public class SubwayPrice implements CalculateStrategy {
	@Override
	public int calculatePrice(int km) {
		return 20;
	}
}
