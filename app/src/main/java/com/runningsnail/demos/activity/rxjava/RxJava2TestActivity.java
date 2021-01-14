package com.runningsnail.demos.activity.rxjava;

import androidx.fragment.app.Fragment;

import com.runningsnail.demos.activity.rxjava.fragment.RxJavaMainFragment;
import com.runningsnail.demos.base.BaseDemoActivity;

/**
 * RxJava测试Activity
 */
public class RxJava2TestActivity extends BaseDemoActivity {

	public static final String TAG = "RxJava2TestActivity";

	@Override
	protected Class<? extends Fragment> getMainFragment() {
		return RxJavaMainFragment.class;
	}

}
