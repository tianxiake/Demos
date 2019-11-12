package com.runningsnail.demos.activity.arc.jetpack;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import com.runningsnail.demos.common.utils.HiLogger;

/**
 * 直接继承系统默认的实现类然后重现指定的方法即可
 * @author yongjie created on 2019-11-12.
 */
public class MyObserver implements DefaultLifecycleObserver {

	private static final String TAG = "MyObserver";

	@Override
	public void onCreate(@NonNull LifecycleOwner owner) {
		HiLogger.i(TAG, "onCreate");
	}

	@Override
	public void onPause(@NonNull LifecycleOwner owner) {
		HiLogger.i(TAG, "onPause");
	}

	@Override
	public void onDestroy(@NonNull LifecycleOwner owner) {
		HiLogger.i(TAG, "onDestroy");
	}
}
