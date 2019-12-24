package com.runningsnail.demos.activity.arc.jetpack.combine;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;
import com.runningsnail.demos.databinding.ActivityCombineOneBinding;

/**
 * ViewModle+DataBinding+LiveData
 */
public class CombineOneActivity extends AppCompatActivity {
	private static final String TAG = "CombineOneActivity";
	private CombineOneViewModel combineOneViewModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//第一步:拿到ViewModel对象
		combineOneViewModel = ViewModelProviders.of(this).get(CombineOneViewModel.class);
		ActivityCombineOneBinding activityCombineOneBinding = DataBindingUtil.setContentView(this, R.layout.activity_combine_one);
		//第二步:配置LifecycleOwner,生命周期的释放
		activityCombineOneBinding.setLifecycleOwner(this);
		//第三步注入ViewModel
		activityCombineOneBinding.setUserName(combineOneViewModel);
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		HiLogger.i(TAG, "onDestroy");
	}
}
