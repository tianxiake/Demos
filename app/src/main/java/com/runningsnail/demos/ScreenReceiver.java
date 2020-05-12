package com.runningsnail.demos;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.runningsnail.demos.common.utils.HiLogger;

/**
 * @author yongjie created on 2020/3/11.
 */
public class ScreenReceiver extends BroadcastReceiver {
	private static final String TAG = "ScreenReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		HiLogger.d(TAG, "intent: %s", intent);
	}
}
