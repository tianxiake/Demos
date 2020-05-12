package com.runningsnail.demos.activity.tv;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.AnimUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LinearLayoutTVActivity extends AppCompatActivity {
	@BindView(R.id.iv_focus_one)
	ImageView ivFocusOne;
	@BindView(R.id.fl_one)
	FrameLayout flOne;
	@BindView(R.id.iv_focus_two)
	ImageView ivFocusTwo;
	@BindView(R.id.fl_two)
	FrameLayout flTwo;

//	LinearLayout linearLayout;
//	private int focusIndex = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_linear_layout_tv);
		ButterKnife.bind(this);
//		linearLayout = findViewById(R.id.ll_container);
//		linearLayout.setClipBounds(new Rect(-40, -40, 300, 362));
////
//		linearLayout.addView(createView("我是中国_1"));
//		linearLayout.addView(createView("我是中国_2"));
//		linearLayout.addView(createView("我是中国_3"));
//		linearLayout.addView(createView("我是中国_4"));
//		linearLayout.addView(createView("我是中国_5"));
//		linearLayout.addView(createView("我是中国_6"));
//		linearLayout.addView(createView("我是中国_7"));
//		linearLayout.addView(createView("我是中国_8"));
//		linearLayout.addView(createView("我是中国_9"));
//		linearLayout.addView(createView("我是中国_10"));

		flTwo.setClipBounds(new Rect(0, 0, 300, 300));
		ivFocusOne.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				AnimUtil.scaleView(v, hasFocus);
			}
		});

		ivFocusTwo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				AnimUtil.scaleView(v, hasFocus);
			}
		});
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		int action = event.getAction();
		if (action == KeyEvent.ACTION_DOWN) {
			int keyCode = event.getKeyCode();
//			if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
//				if (linearLayout.hasFocus()) {
//					linearLayout.scrollBy(0, 78);
//				}
//			} else if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
//				if (linearLayout.hasFocus()) {
//					linearLayout.scrollBy(0, -78);
//				}
//			}
		}
		return super.dispatchKeyEvent(event);
	}

	public View createView(String text) {
		TextView textView = new TextView(this);
		textView.setBackgroundColor(Color.RED);
		textView.setFocusable(true);
		textView.setText(text);
		textView.setTextSize(30);
		textView.setTextColor(Color.WHITE);
		textView.setGravity(Gravity.CENTER);
		ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 78);
		textView.setLayoutParams(layoutParams);
		textView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				AnimUtil.scaleView(v, hasFocus);
				if (hasFocus) {
					textView.setTextColor(Color.BLUE);
				} else {
					textView.setTextColor(Color.WHITE);
				}
			}
		});
		return textView;
	}
}
