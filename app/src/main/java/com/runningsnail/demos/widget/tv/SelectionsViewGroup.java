package com.runningsnail.demos.widget.tv;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.chances.base.utils.StringUtils;
import com.runningsnail.demos.common.utils.HiLogger;

import java.util.LinkedList;

/**
 * 播放面板展示容器
 *
 * @author yongjie created on 2020/9/10.
 */
public class SelectionsViewGroup extends LinearLayout {

	private static final String TAG = "SelectionsViewGroup";
	private LinkedList<SelectionsView> viewLinkedList;

	public SelectionsViewGroup(Context context) {
		super(context);
		init();
	}

	public SelectionsViewGroup(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public SelectionsViewGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		this.setOrientation(LinearLayout.VERTICAL);
		viewLinkedList = new LinkedList<>();
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		return super.dispatchKeyEvent(event) || executeKeyEvent(event);
	}

	private boolean executeKeyEvent(KeyEvent event) {
		int action = event.getAction();
		if (action == KeyEvent.ACTION_DOWN) {
			int keyCode = event.getKeyCode();
			if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
				showPrev();
				return true;
			} else if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
				showNext();
				return true;
			}
		}
		return false;
	}

	public void showPrev() {
		if (!checkCanDo()) {
			return;
		}
		HiLogger.d(TAG, "showPrev");
		SelectionsView lastSelectionView = viewLinkedList.removeLast();
		viewLinkedList.addFirst(lastSelectionView);
		this.removeView(lastSelectionView);
		this.addView(lastSelectionView, 0);
		lastSelectionView.showFoldView();
		SelectionsView currentSectionView = viewLinkedList.getLast();
		currentSectionView.showUnfoldView();
	}

	private boolean checkCanDo() {
		if (viewLinkedList.isEmpty() || viewLinkedList.size() <= 1) {
			return false;
		}
		return true;
	}

	public void showNext() {
		if (!checkCanDo()) {
			return;
		}
		HiLogger.d(TAG, "showNext");
		SelectionsView lastSelectionView = viewLinkedList.getLast();
		SelectionsView currentSelectionView = viewLinkedList.removeFirst();
		viewLinkedList.add(currentSelectionView);
		this.removeView(currentSelectionView);
		this.addView(currentSelectionView);
		lastSelectionView.showFoldView();
		currentSelectionView.showUnfoldView();
	}

	public void addSelectionsView(SelectionsView selectionsView) {
		if (selectionsView != null) {
			viewLinkedList.add(selectionsView);
			this.addView(selectionsView);
			selectionsView.showFoldView();
		}
	}

	public void removeSelectionsView(String type) {
		if (StringUtils.isEmpty(type)) {
			return;
		}
		int size = viewLinkedList.size();
		for (int i = 0; i < size; i++) {
			SelectionsView selectionsView = viewLinkedList.get(i);
			String selectionType = selectionsView.getType();
			if (StringUtils.isEquals(selectionType, type)) {
				viewLinkedList.remove(selectionsView);
				this.removeView(selectionsView);
				if (i == size - 1) {
					viewLinkedList.getLast().showUnfoldView();
				}
				break;
			}
		}
	}

	public void removeSelectionsView(SelectionsView selectionsView) {
		if (selectionsView != null) {
			removeSelectionsView(selectionsView.getType());
		}
	}

	public void show() {
		show(viewLinkedList.size() - 1);
	}

	public void show(int index) {
		if (viewLinkedList.isEmpty()) {
			return;
		}
		HiLogger.d(TAG, "index: %s", index);
		try {
			if (index == viewLinkedList.size() - 1) {
				viewLinkedList.peekLast().showUnfoldView();
				return;
			}
			if (index == 0) {
				SelectionsView selectionsView = viewLinkedList.removeFirst();
				viewLinkedList.add(selectionsView);
				this.removeView(selectionsView);
				this.addView(selectionsView);
				selectionsView.showUnfoldView();
				return;
			}
			for (int i = 0; i < viewLinkedList.size() - index - 1; i++) {
				SelectionsView selectionsView = viewLinkedList.removeLast();
				viewLinkedList.addFirst(selectionsView);
				this.removeView(selectionsView);
				this.addView(selectionsView, 0);
				selectionsView.showFoldView();
			}
			viewLinkedList.peekLast().showUnfoldView();
		} catch (Exception e) {
			HiLogger.w(TAG, "无效的index:");
		}
	}

	public void show(SelectionsView selectionsView) {
		int index = viewLinkedList.indexOf(selectionsView);
		if (index != -1) {
			show(index);
		}
	}

	public void show(String type) {
		if (StringUtils.isEmpty(type)) {
			return;
		}
		for (int i = 0; i < viewLinkedList.size(); i++) {
			SelectionsView selectionsView = viewLinkedList.get(i);
			String selectionType = selectionsView.getType();
			if (StringUtils.isEquals(selectionType, type)) {
				show(i);
				break;
			}
		}
	}
}
