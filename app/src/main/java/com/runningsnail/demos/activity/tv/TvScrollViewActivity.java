package com.runningsnail.demos.activity.tv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.runningsnail.demos.R;

public class TvScrollViewActivity extends AppCompatActivity {

	private LinearLayout container;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tv_scroll_view);
		container = findViewById(R.id.ll_tv_scroll_view_container);

		createLineView();
	}

	private void createLineView() {
		for (int i = 0; i < 120; i++) {
			View view = createView();
			container.addView(view);
		}
	}

	private View createView() {
		View inflate = LayoutInflater.from(this).inflate(R.layout.scroll_view_item, container, false);
		return inflate;
	}
}