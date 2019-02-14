package com.runningsnail.demos.tvwidget.powerfulrecyclerview.listener;

import android.view.View;

public interface OnHiItemFocusChangeListener<E> {
	/**
	 * 点击事件的回调
	 *
	 * @param hasFocus      是否获得了焦点
	 * @param focusView     点击的View
	 * @param focusPosition 点击view的位置
	 * @param data          对应位置的数据
	 */
	void onItemFocusChange(boolean hasFocus, View focusView, int focusPosition, E data);
}
