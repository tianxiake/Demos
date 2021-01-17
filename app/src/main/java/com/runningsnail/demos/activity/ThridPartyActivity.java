package com.runningsnail.demos.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;

public class ThridPartyActivity extends AppCompatActivity {
	private static final String TAG = "ThridPartyActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thrid_party);
		HiLogger.d(TAG, "onCreate");
	}

	@Override
	protected void onStart() {
		super.onStart();
		HiLogger.d(TAG, "onStart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		HiLogger.d(TAG, "onResume");
	}
}