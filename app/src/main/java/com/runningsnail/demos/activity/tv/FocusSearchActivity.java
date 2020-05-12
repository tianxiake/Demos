package com.runningsnail.demos.activity.tv;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.AnimUtil;
import com.runningsnail.demos.common.utils.HiLogger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnFocusChange;

public class FocusSearchActivity extends AppCompatActivity {

	@BindView(R.id.ll_layout)
	LinearLayout llLayout;
	@BindView(R.id.iv_two)
	ImageView ivTwo;
	private static final String TAG = "FocusSearchActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_focus_search);
		ButterKnife.bind(this);


//		View view = llLayout.focusSearch(llLayout, View.FOCUS_RIGHT);
//		HiLogger.d(TAG, "view:%s", view);
//
//		View view1 = llLayout.focusSearch(ivTwo, View.FOCUS_RIGHT);
//		HiLogger.d(TAG, "view1:%s", w1);


	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN) {
			if (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT) {
				if (ivTwo.hasFocus()) {
					View view = llLayout.focusSearch(llLayout, View.FOCUS_RIGHT);
					HiLogger.d(TAG, "view:%s", view);

					View view1 = llLayout.focusSearch(ivTwo, View.FOCUS_RIGHT);
					HiLogger.d(TAG, "view1:%s", view1);
				}
			}

		}
		return super.dispatchKeyEvent(event);
	}

	@OnFocusChange({R.id.iv_two, R.id.iv_one, R.id.iv_three})
	public void onFocusChange(boolean hasFocus, View view) {
		AnimUtil.scaleView(view, hasFocus);
	}

}
