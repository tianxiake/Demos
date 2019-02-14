package com.runningsnail.demos.tvwidget.powerfulrecyclerview.listener;

import android.view.View;

import com.runningsnail.demos.tvwidget.powerfulrecyclerview.BaseRecyclerView;

/**
 * 焦点到了四个边缘即将脱离RecyclerView时的回调
 * 用于处理焦点的定向处理
 */
public interface OnFocusInEdgeListener {

	/**
	 * 此时聚焦在RecyclerView的上边缘，再次按上键RecyclerView可能会失去焦点
	 *
	 * @param recyclerView  子类实现的RecyclerView
	 * @param focusItemView 聚焦的ItemView
	 * @param focusPosition 聚焦的ItemView的位置
	 * @return
	 */
	boolean onFocusOnTopEdge(BaseRecyclerView recyclerView, View focusItemView, int focusPosition);

	/**
	 * 此时聚焦在RecyclerView的下边缘，再次按上键RecyclerView可能会失去焦点
	 *
	 * @param recyclerView  子类实现的RecyclerView
	 * @param focusItemView 聚焦的ItemView
	 * @param focusPosition 聚焦的ItemView的位置
	 * @return
	 */
	boolean onFocusOnBottomEdge(BaseRecyclerView recyclerView, View focusItemView, int focusPosition);

	/**
	 * 此时聚焦在RecyclerView的左边缘，再次按上键RecyclerView可能会失去焦点
	 *
	 * @param recyclerView  子类实现的RecyclerView
	 * @param focusItemView 聚焦的ItemView
	 * @param focusPosition 聚焦的ItemView的位置
	 * @return
	 */
	boolean onFocusOnLeftEdge(BaseRecyclerView recyclerView, View focusItemView, int focusPosition);

	/**
	 * 此时聚焦在RecyclerView的右边缘，再次按上键RecyclerView可能会失去焦点
	 *
	 * @param recyclerView  子类实现的RecyclerView
	 * @param focusItemView 聚焦的ItemView
	 * @param focusPosition 聚焦的ItemView的位置
	 * @return
	 */
	boolean onFocusOnRightEdge(BaseRecyclerView recyclerView, View focusItemView, int focusPosition);

}