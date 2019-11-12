package com.runningsnail.demos;

import android.graphics.Rect;
import android.os.Bundle;

import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.common.utils.HiLogger;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity {
	private static final String TAG = "Main2Activity";
	@BindView(R.id.iv_img)
	ImageView ivImg;
	@BindView(R.id.fl_content)
	FrameLayout flContent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		ButterKnife.bind(this);

	}

	@Override
	protected void onResume() {
		super.onResume();


		ivImg.post(new Runnable() {
			@Override
			public void run() {
				Rect rect1 = new Rect();
				flContent.offsetDescendantRectToMyCoords(ivImg, rect1);
				HiLogger.i(TAG, "rect1 %s", rect1);

				Rect rect2 = new Rect();
				flContent.offsetRectIntoDescendantCoords(ivImg, rect2);
				HiLogger.i(TAG, "rect2 %s", rect2);
			}
		});
	}
}
