package com.runningsnail.demos.activity.tv;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import com.runningsnail.demos.common.utils.HiLogger;

/**
 * @author yongjie created on 2020/10/24.
 */
public class CustomFrameLayout extends FrameLayout {
	private static final String TAG = "CustomFrameLayout";

	public CustomFrameLayout(Context context) {
		super(context);
	}

	public CustomFrameLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CustomFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		HiLogger.d(TAG, "event", event);
		return super.dispatchKeyEvent(event);
	}
}
