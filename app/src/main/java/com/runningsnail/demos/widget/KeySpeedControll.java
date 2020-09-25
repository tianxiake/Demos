package com.runningsnail.demos.widget;

import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

/**
 * @author yongjie created on 2020/9/10.
 */
public class KeySpeedControll {
	private static final String TAG = "KeySpeedController";
	public final static int TIME_INTERVAL = 300;

	private static long mLastKeyDownTime = 0;

	public static boolean control(int speed, KeyEvent event, View focusView) {
		if (focusView != null && focusView instanceof TextView) {
			return false;
		}
		if (event.getAction() == KeyEvent.ACTION_DOWN) {
			long current = System.currentTimeMillis();
			if (current - mLastKeyDownTime < speed) {
				return true;
			} else {
				mLastKeyDownTime = current;
				switch (event.getKeyCode()) {
					case KeyEvent.KEYCODE_DPAD_LEFT:
						return false;
					case KeyEvent.KEYCODE_DPAD_RIGHT:
						return false;
					case KeyEvent.KEYCODE_DPAD_DOWN:
						return false;
					case KeyEvent.KEYCODE_DPAD_UP:
						return false;
					default:
						return false;
				}
			}
		}
		return false;
	}

	public static boolean control(int speed, KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_UP) {
			long current = System.currentTimeMillis();
			if (current - mLastKeyDownTime < speed && event.getRepeatCount() > 1) {
				return true;
			} else {
				mLastKeyDownTime = current;
			}
		}
		return false;
	}

	public static int getSuggestedSpeed(KeyEvent event) {
		int repeatCount = event.getRepeatCount();
		int speed;
		if (repeatCount > 20) {
			speed = 300;
		} else if (repeatCount > 10) {
			speed = 250;
		} else if (repeatCount > 5) {
			speed = 180;
		} else {
			speed = 100;
		}
		return speed;
	}
}
