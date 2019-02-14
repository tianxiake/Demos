package com.runningsnail.demos.tvwidget.powerfulrecyclerview.listener;

/**
 * 这个接口就是处理上下或者左右滑动指示器的显示
 */
public interface OnIndicatorListener {
	/**
	 * 只展示开始箭头,表示到了头部了
	 */
	void showBeginIndicator();

	/**
	 * 只展示结束的箭头，表示到了尾部了
	 */
	void showEndIndicator();

	/**
	 * 开始和结束的箭头都展示,表示处于中间部分
	 */
	void showBeginAndEndIndicator();

}