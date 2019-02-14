package com.runningsnail.demos.tvwidget.powerfulrecyclerview.listener;

/**
 * 这个接口用于告知内部数据条目信息用于外部去显示数据Index
 */
public interface OnItemPostionListener {
	/**
	 * 列表数据总数,这个可能依赖于数据集合
	 *
	 * @param count
	 */
	void onItemTotalCount(int count);

	/**
	 * 当前聚焦的Item的Index
	 *
	 * @param index
	 */
	void onItemCurrentFocusItemIndex(int index);
}
