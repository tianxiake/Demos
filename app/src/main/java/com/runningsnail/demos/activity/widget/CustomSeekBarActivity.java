package com.runningsnail.demos.activity.widget;

import android.os.Bundle;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomSeekBarActivity extends AppCompatActivity {
	private static final String TAG = "CustomSeekBarActivity";
	@BindView(R.id.system_default_seek_bar)
	SeekBar systemDefaultSeekBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_seek_bar);
		ButterKnife.bind(this);
		systemDefaultSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				HiLogger.d(TAG, "onProgressChanged");
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				HiLogger.d(TAG, "onStartTrackingTouch");
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				HiLogger.d(TAG, "onStopTrackingTouch");
			}
		});
	}
}