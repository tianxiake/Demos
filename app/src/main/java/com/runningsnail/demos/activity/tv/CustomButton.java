package com.runningsnail.demos.activity.tv;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.Button;

import com.runningsnail.demos.common.utils.HiLogger;

/**
 * @author yongjie created on 2020/10/24.
 */
public class CustomButton extends Button {
	private static final String TAG = "CustomButton";

	public CustomButton(Context context) {
		super(context);
	}

	public CustomButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		HiLogger.d(TAG, "event %s", event);
		if (event.getAction() == KeyEvent.ACTION_DOWN) {
			HiLogger.e(TAG, "keyEvent=> activity dispatchKeyEvent", new Throwable());
		}

		return super.dispatchKeyEvent(event);
	}

	@Override
	protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
		super.onFocusChanged(focused, direction, previouslyFocusedRect);
		HiLogger.e(TAG, "onFocusChanged=> focused:" + focused + ",direction:" + direction, new Throwable());
	}
}
