package com.runningsnail.demos.activity.tv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *
 */
public class KeyEventTestActivity extends AppCompatActivity {

	public static final String TAG = "KeyEvent_TestActivity";
	@BindView(R.id.btn_one)
	Button btnOne;
	@BindView(R.id.btn_two)
	Button btnTwo;
	@BindView(R.id.rv_content)
	RecyclerView rvContent;
	@BindView(R.id.btn_three)
	Button btnThree;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_key_event_test);
		ButterKnife.bind(this);
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

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		HiLogger.d(TAG, "onNewIntent");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		HiLogger.d(TAG, "onRestart");
	}

	@Override
	protected void onPause() {
		super.onPause();
		HiLogger.d(TAG, "onPause");
	}

	@Override
	protected void onStop() {
		super.onStop();
		HiLogger.d(TAG, "onStop");
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		HiLogger.d(TAG, "KeyEvent" + event.getKeyCode());
		return super.dispatchKeyEvent(event);
	}

	@OnClick(R.id.btn_three)
	public void onClick(View view) {
		Intent intent = new Intent(this, ShadowEffectActivity.class);
		startActivity(intent);
	}


}
