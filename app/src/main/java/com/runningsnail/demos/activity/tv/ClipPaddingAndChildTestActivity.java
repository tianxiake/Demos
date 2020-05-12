package com.runningsnail.demos.activity.tv;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.AnimUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClipPaddingAndChildTestActivity extends AppCompatActivity {

	@BindView(R.id.iv_image_one)
	ImageView ivImageOne;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clip_padding_and_child_test);
		ButterKnife.bind(this);
		ivImageOne.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				AnimUtil.scaleView(v, hasFocus);
			}
		});
	}
}
