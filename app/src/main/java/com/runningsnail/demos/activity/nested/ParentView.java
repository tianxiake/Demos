package com.runningsnail.demos.activity.nested;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.NestedScrollingParent2;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.ViewCompat;

import com.runningsnail.demos.common.utils.HiLogger;

/**
 * @author yongjie created on 2019-12-02.
 */
public class ParentView extends LinearLayout implements NestedScrollingParent2 {

	private static final String TAG = "ParentView";
	private NestedScrollingParentHelper parentHelper;
	private int scrollDistance = 0;

	public ParentView(Context context) {
		super(context);
		init();
	}

	public ParentView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ParentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		parentHelper = new NestedScrollingParentHelper(this);
	}

	@Override
	public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes, int type) {
		HiLogger.i(TAG, "onStartNestedScroll %s %s %s %s ", child, target, axes, type);
		if (ViewCompat.SCROLL_AXIS_VERTICAL == axes) {
			return true;
		}
		return false;
	}

	public void setScrollDistance(int scrollDistance) {
		HiLogger.i(TAG, "scrollDistance %s", scrollDistance);
		this.scrollDistance = scrollDistance;
	}

	@Override
	public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes, int type) {
		HiLogger.i(TAG, "onNestedScrollAccepted");
		parentHelper.onNestedScrollAccepted(child, target, axes, type);
	}

	@Override
	public void onStopNestedScroll(@NonNull View target, int type) {
		HiLogger.i(TAG, "onStopNestedScroll");
		parentHelper.onStopNestedScroll(target, type);
	}

	@Override
	public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
		HiLogger.i(TAG, "onNestedScroll");
	}

	@Override
	public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
		HiLogger.i(TAG, "onNestedPreScroll");
		if ((dy > 0 && getScrollY() < scrollDistance) || (dy < 0 && getScrollY() > 0)) {
			consumed[1] = dy;
			scrollBy(dx, dy);
		}
	}
}
