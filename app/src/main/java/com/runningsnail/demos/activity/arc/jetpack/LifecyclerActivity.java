package com.runningsnail.demos.activity.arc.jetpack;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;

public class LifecyclerActivity extends AppCompatActivity {
	private static final String TAG = "LifecyclerActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lifecycler);
		getLifecycle().addObserver(new MyObserver());

		HiLogger.i(TAG,"onCreate");
	}


	@Override
	protected void onPause() {
		super.onPause();
		HiLogger.i(TAG, "onPause");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		HiLogger.i(TAG,"onDestroy");
	}
}
