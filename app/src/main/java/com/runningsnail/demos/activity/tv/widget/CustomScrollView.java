package com.runningsnail.demos.activity.tv.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ScrollView;

import androidx.annotation.Nullable;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author yongjie created on 2020/4/28.
 */
public class CustomScrollView extends ScrollView {
	private static final String TAG = "CustomScrollView";

	public CustomScrollView(Context context) {
		super(context);
	}

	public CustomScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		int action = event.getAction();
		if (action == KeyEvent.ACTION_DOWN) {
			int keyCode = event.getKeyCode();
			if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
				if (this.pageScroll(View.FOCUS_UP)) {
					return true;
				}

			} else if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
				if (this.pageScroll(View.FOCUS_DOWN)) {
					return true;
				}
			}
		}
		return super.dispatchKeyEvent(event);
	}

	@Override
	protected void onFocusChanged(boolean gainFocus, int direction, @Nullable Rect previouslyFocusedRect) {
		super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
		if (gainFocus) {
			try {
				Class<?> viewClass = View.class;
				HiLogger.d(TAG, "scrollViewClass: %s", viewClass);
				Field mScrollCaches = viewClass.getDeclaredField("mScrollCache");
				mScrollCaches.setAccessible(true);

				Object mScrollCache = mScrollCaches.get(this);
				Class<?> scrollCachesClass = mScrollCaches.getType();
				HiLogger.d(TAG, "type: %s", scrollCachesClass);
				Field scrollBar = scrollCachesClass.getDeclaredField("scrollBar");
				scrollBar.setAccessible(true);
				Object scrollBarObj = scrollBar.get(mScrollCache);

				Class<?> scrollBarClass = scrollBar.getType();
				HiLogger.d(TAG, "scrollBarClass: %s", scrollBarClass);
				Method setVerticalThumbDrawable = scrollBarClass.getDeclaredMethod("setVerticalThumbDrawable", Drawable.class);
				setVerticalThumbDrawable.setAccessible(true);
				setVerticalThumbDrawable.invoke(scrollBarObj, this.getResources().getDrawable(R.drawable.scroll_thumb));
			} catch (Exception e) {
				HiLogger.e(TAG, "OttVerticalScrollView", e);
			}
		} else {
			try {
				Class<?> viewClass = View.class;
				HiLogger.d(TAG, "scrollViewClass: %s", viewClass);
				Field mScrollCaches = viewClass.getDeclaredField("mScrollCache");
				mScrollCaches.setAccessible(true);

				Object mScrollCache = mScrollCaches.get(this);
				Class<?> scrollCachesClass = mScrollCaches.getType();
				HiLogger.d(TAG, "type: %s", scrollCachesClass);
				Field scrollBar = scrollCachesClass.getDeclaredField("scrollBar");
				scrollBar.setAccessible(true);
				Object scrollBarObj = scrollBar.get(mScrollCache);

				Class<?> scrollBarClass = scrollBar.getType();
				HiLogger.d(TAG, "scrollBarClass: %s", scrollBarClass);
				Method setVerticalThumbDrawable = scrollBarClass.getDeclaredMethod("setVerticalThumbDrawable", Drawable.class);
				setVerticalThumbDrawable.setAccessible(true);
				setVerticalThumbDrawable.invoke(scrollBarObj, this.getResources().getDrawable(R.drawable.default_shape));
			} catch (Exception e) {
				HiLogger.e(TAG, "OttVerticalScrollView", e);
			}
		}

	}
}
