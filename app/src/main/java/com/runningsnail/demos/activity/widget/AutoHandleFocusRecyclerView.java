package com.runningsnail.demos.activity.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author liuyongjie create on 2018/12/20.
 */
public class AutoHandleFocusRecyclerView extends RecyclerView {
	private static final String TAG = "AutoHandleFocusRecyclerView";

	public AutoHandleFocusRecyclerView(Context context) {
		super(context);
	}

	public AutoHandleFocusRecyclerView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	public AutoHandleFocusRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/**
	 * 拦截拦截事件
	 *
	 * @param event
	 * @return
	 */
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		int action = event.getAction();
		int keyCode = event.getKeyCode();
		if (action == KeyEvent.ACTION_DOWN) {
			if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
				//先让View进行滑动
				handleLeftMove();
//				//然后处理到达view的边界的焦点拦截操作
//				LayoutManager layoutManager = getLayoutManager();
//				View childFocus = this.getFocusedChild();
//				if (layoutManager instanceof GridLayoutManager) {
//					//处理GridLayout到最左边焦点的问题
//
//				} else if (layoutManager instanceof LinearLayoutManager) {
//					LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
//					//处理滑动
//					handleLeftMove(linearLayoutManager);
//
//
//
//
//
//					//处理非GridLayout到最左边焦点的问题
////					LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
//					int position = linearLayoutManager.getPosition(childFocus);
//					HiLogger.d(TAG, "focusChildView position:" + position);
//					if (position == 0) {
//						//到了最左边,拦截系统自动寻找焦点
//						return true;
//					}
//				}

			} else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
				LayoutManager layoutManager = getLayoutManager();
				View childFocus = this.getFocusedChild();
				if (layoutManager instanceof GridLayoutManager) {

				} else if (layoutManager instanceof LinearLayoutManager) {

				}
			}
		}
		return super.dispatchKeyEvent(event);
	}

	private void handleLeftMove() {
		LayoutManager layoutManager = getLayoutManager();
		if (layoutManager instanceof GridLayoutManager) {

		} else if (layoutManager instanceof LinearLayoutManager) {
			LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
			//第一个

		}
	}
}
