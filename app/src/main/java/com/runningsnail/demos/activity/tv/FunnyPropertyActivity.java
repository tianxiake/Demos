package com.runningsnail.demos.activity.tv;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnFocusChange;

public class FunnyPropertyActivity extends AppCompatActivity {
	private static final String TAG = "FunnyPropertyActivity";
	@BindView(R.id.fl_area_one_child)
	FrameLayout flChild;
	@BindView(R.id.fl_area_one_parent)
	FrameLayout flParent;
	@BindView(R.id.fl_area_two_child)
	FrameLayout flAreaTwoChild;
	@BindView(R.id.fl_area_two_parent)
	FrameLayout flAreaTwoParent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_funny_property);
		ButterKnife.bind(this);

	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		HiLogger.d(TAG, "dispatchKeyEvent start");
		boolean result = super.dispatchKeyEvent(event);
		HiLogger.d(TAG, "dispatchKeyEvent end");
		return result;
	}

	@OnFocusChange({R.id.fl_area_one_child, R.id.fl_area_one_parent,
			R.id.fl_area_two_parent, R.id.fl_area_two_child})
	public void onFocusChange(View v, boolean hasFocus) {
		int id = v.getId();
		switch (id) {
			case R.id.fl_area_one_parent:
				HiLogger.e(TAG, "", new Throwable());
				HiLogger.i(TAG, "fl_area_one_parent %s", hasFocus);
				break;
			case R.id.fl_area_one_child:
				HiLogger.i(TAG, "fl_area_one_child %s", hasFocus);
				break;
			case R.id.fl_area_two_parent:
				HiLogger.i(TAG, "fl_area_two_parent %s", hasFocus);
				break;
			case R.id.fl_area_two_child:
				HiLogger.i(TAG, "fl_area_two_child %s", hasFocus);
				break;
			default:
				break;
		}
	}
}
