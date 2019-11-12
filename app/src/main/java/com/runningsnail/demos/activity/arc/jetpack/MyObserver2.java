package com.runningsnail.demos.activity.arc.jetpack;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import com.runningsnail.demos.common.utils.HiLogger;

/**
 * 继承自原始的Observer类 通过注解的方式指定被调用的方法
 * @author yongjie created on 2019-11-12.
 */
public class MyObserver2 implements LifecycleObserver {

	private static final String TAG = "MyObserver";

	@OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
	public void onCreate(@NonNull LifecycleOwner owner) {
		HiLogger.i(TAG,"onCreate");
	}

	@OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
	public void onPause(@NonNull LifecycleOwner owner) {
		HiLogger.i(TAG,"onPause");
	}

	@OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
	public void onDestroy(@NonNull LifecycleOwner owner) {
		HiLogger.i(TAG, "onDestroy");
	}
}
