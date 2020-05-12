package com.runningsnail.demos.activity.tv;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScrollViewActivity extends AppCompatActivity {
	@BindView(R.id.scroll_view)
	ScrollView scrollView;
	@BindView(R.id.btn_scroll)
	Button btnScroll;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scroll_view);
		ButterKnife.bind(this);

	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		int action = event.getAction();
//		if (action == KeyEvent.ACTION_DOWN) {
//			int keyCode = event.getKeyCode();
//			if (btnScroll.hasFocus()) {
//				if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
//					scrollView.canScrollVertically(1);
//					scrollView.pageScroll()
//					scrollView.smoothScrollBy();
//				} else if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
//
//				}
//			}
//		}
		return super.dispatchKeyEvent(event);

	}
}
