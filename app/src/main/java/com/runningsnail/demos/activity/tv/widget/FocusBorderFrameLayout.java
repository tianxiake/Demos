package com.runningsnail.demos.activity.tv.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.runningsnail.demos.common.utils.HiLogger;


/**
 * @author yongjie created on 2020/4/15.
 */
public class FocusBorderFrameLayout extends FrameLayout {
	private static final String TAG = "FocusBorderFrameLayout";
	private CommonFocusEventHelper commonFocusEventHelper = new CommonFocusEventHelper();

	public CommonFocusEventHelper getCommonFocusEventHelper() {
		return commonFocusEventHelper;
	}


	public FocusBorderFrameLayout(Context context) {
		this(context, null);
	}

	public FocusBorderFrameLayout(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public FocusBorderFrameLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void onFocusChanged(boolean gainFocus, int direction, @Nullable Rect previouslyFocusedRect) {
		super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
		commonFocusEventHelper.handleFocus(this.getContext(), gainFocus, this, this);
		int measuredHeight = commonFocusEventHelper.getFocusBorder().getMeasuredHeight();
		int measuredWidth = commonFocusEventHelper.getFocusBorder().getMeasuredWidth();
		HiLogger.d(TAG, "measuredWidth:%s measuredHeight:%s", measuredWidth, measuredHeight);
	}
}
