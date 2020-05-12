package com.runningsnail.demos.activity.tv;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FlyEffectActivity extends AppCompatActivity {

	@BindView(R.id.fl_image)
	FlyImageView flImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fly_effect);
		ButterKnife.bind(this);
	}
}
