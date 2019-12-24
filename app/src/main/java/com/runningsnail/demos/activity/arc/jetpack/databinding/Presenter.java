package com.runningsnail.demos.activity.arc.jetpack.databinding;

import com.runningsnail.demos.common.utils.HiLogger;

/**
 * @author yongjie created on 2019-11-15.
 */
public class Presenter {
	private static final String TAG = "Presenter";

	public void onTaskClick(MyTask myTask) {

		HiLogger.i(TAG, myTask.toString());
	}

}
