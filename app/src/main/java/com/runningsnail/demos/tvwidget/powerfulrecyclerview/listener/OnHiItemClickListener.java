package com.runningsnail.demos.tvwidget.powerfulrecyclerview.listener;

import android.view.View;

public interface OnHiItemClickListener<E> {

	/**
	 * 点击事件的回调
	 *
	 * @param clickView     点击的View
	 * @param clickPosition 点击view的位置
	 * @param data          对应位置的数据
	 */
	void onItemClick(View clickView, int clickPosition, E data);
}