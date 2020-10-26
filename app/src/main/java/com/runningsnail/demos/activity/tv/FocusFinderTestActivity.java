package com.runningsnail.demos.activity.tv;

import android.os.Bundle;
import android.view.KeyEvent;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FocusFinderTestActivity extends AppCompatActivity {
	private static final String TAG = "FocusFinderTestActivity";
	@BindView(R.id.btn_find_test_one)
	CustomButton btnFindTestOne;
	@BindView(R.id.btn_find_test_two)
	CustomButton btnFindTestTwo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_focus_finder_test);
		ButterKnife.bind(this);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		HiLogger.d(TAG, "event", event);
		return super.dispatchKeyEvent(event);
	}
}