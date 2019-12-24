package com.runningsnail.demos.activity.arc.jetpack.databinding;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;
import com.runningsnail.demos.databinding.ActivityDatabindingBinding;

public class DatabindingActivity extends AppCompatActivity {
	private static final String TAG = "DatabindingActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_databinding);
		User user = new User();
		user.setName("liuyongjie");
		user.setAge("25");

		//使用DataBindingUtil来生成
		ActivityDatabindingBinding activityDatabindingBinding = DataBindingUtil.setContentView(this, R.layout.activity_databinding);
		activityDatabindingBinding.setUser(user);
		View root = activityDatabindingBinding.getRoot();
		HiLogger.i(TAG, "root %s", root.getClass().getName());

		activityDatabindingBinding.setMyTask(new MyTask("我可以的Yeah"));
		activityDatabindingBinding.setMyPresenter(new Presenter());
		activityDatabindingBinding.setMyOnClick(new MyOnClickListener());

	}
}
