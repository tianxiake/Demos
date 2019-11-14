package com.runningsnail.demos.activity.arc.jetpack;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewModelActivity extends AppCompatActivity {

	private static final String TAG = "ViewModelActivity";
	@BindView(R.id.tv_name)
	AppCompatTextView tvName;
	private MyViewModel myViewModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_model);
		ButterKnife.bind(this);
		HiLogger.i(TAG,"onCreate");

		myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);

		myViewModel.getNameLiveData().observe(this, new Observer<String>() {
			@Override
			public void onChanged(String s) {
				HiLogger.i(TAG, "%s", s);
				tvName.setText(s);
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		HiLogger.i(TAG,"onDestroy");
	}
}
