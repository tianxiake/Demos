package com.runningsnail.demos.activity.tv;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.AnimUtil;

import butterknife.ButterKnife;
import butterknife.OnFocusChange;

public class ClipPaddingAndChildTestActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clip_padding_and_child_test);
		ButterKnife.bind(this);
	}

	@OnFocusChange({R.id.text_one, R.id.text_two,
			R.id.text_three, R.id.text_four,
			R.id.text_five, R.id.text_six,
			R.id.text_seven, R.id.text_eight,
			R.id.text_nine, R.id.text_ten,
			R.id.text_eleven, R.id.text_twelve})
	public void onFocusChange(View v, boolean hasFocus) {
		AnimUtil.scaleView(v, hasFocus);
	}
}
