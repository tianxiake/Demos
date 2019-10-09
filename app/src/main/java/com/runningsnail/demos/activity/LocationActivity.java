package com.runningsnail.demos.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.runningsnail.demos.MainHandler;
import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocationActivity extends AppCompatActivity {
	private static final String TAG = "LocationActivity";
	@BindView(R.id.iv_image_one)
	ImageView ivImageOne;
	@BindView(R.id.iv_image_two)
	ImageView ivImageTwo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location);
		ButterKnife.bind(this);


		MainHandler.getInstance().postDelayed(new Runnable() {
			@Override
			public void run() {
				int[] locationInWindow = getLocationInWindow(ivImageOne);
				int[] locationInWindow1 = getLocationInWindow(ivImageTwo);
				HiLogger.i(TAG, "ivOne:" + Arrays.toString(locationInWindow) + ",ivTwo:" + Arrays.toString(locationInWindow1));
			}
		}, 3000);
	}

	public int[] getLocationInWindow(View view) {
		int[] top = new int[2];
		view.getLocationInWindow(top);
		return top;
	}
}
