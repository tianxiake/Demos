package com.runningsnail.demos.activity.arc.jetpack.databinding;

import android.view.View;

import com.runningsnail.demos.common.utils.HiLogger;

/**
 * @author yongjie created on 2019-11-15.
 */
public class MyOnClickListener {
	private static final String TAG = "MyOnClickListener";
	public void onViewClick(View view){
		HiLogger.i(TAG,"onViewClick");
	}
}
