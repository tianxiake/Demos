package com.runningsnail.demos.tvwidget.powerfulrecyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * # 框架解决的问题
 * 1>上下左右到了四个方向的边缘焦点事件的回调
 * 2>解决上下左右快速滑动焦点丢失问题
 * 3>解决数据集的抽象
 * 4>数据的传递支持传入数据和内部网络请求两种方式，交由子类处理
 * 5>事件的处理
 * 6>焦点的通用处理
 * 7>回调数据集的支持
 * 8>支持LinearLayout和GridLayoutManager
 * 9>自动处理间隔让每个Item正好显示,支持不同的策略 比如上下等分、左右等分、右边填充、左边填充等，当然支持
 * 用户自己接管处理，如果上下的不等间隔分
 * 10>支持焦点的记忆处理
 * 11>支持分页加载的个性化处理
 * 12>支持开启可关闭延迟选中功能，一种策略方式，两种策略、一种是不延迟选中、一种是延迟选中
 *
 * @author yongjie created on 2019/1/15.
 */
public abstract class BaseRecyclerView extends RecyclerView {

	public BaseRecyclerView(Context context) {
		super(context);
	}

	public BaseRecyclerView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	public BaseRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		return super.dispatchKeyEvent(event);
	}

	/**
	 * 请求Item获取焦点，默认聚焦到当前可见的第一个，
	 * 如果开启了焦点的记忆功能会聚焦到上一次的位置
	 */
	public void requestItemFocus() {

	}

	/**
	 * 请求指定位置的Item获取焦点
	 */
	public void requestItemFocus(int position) {

	}


	/**
	 * 启用焦点记忆功能,下一次这个控件获得焦点会聚集到
	 * 上一次焦点记忆的地方
	 */
	public void setFocusMemoryEnable(boolean focusMemory) {

	}

	/**
	 * 绑定Item的布局样式
	 */
	protected abstract int bindLayoutResId();

	/**
	 * 绑定布局Item的样式view,优先级低于{@link #bindLayoutResId()}，
	 * 如果{@link #bindLayoutResId()}方法生效了，此方法不会被调用
	 */
	protected abstract View bindLayoutView();

}
