package com.runningsnail.demos.activity.retrofit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.runningsnail.demos.R;
import com.runningsnail.demos.activity.retrofit.fragment.RetrofitOneFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RetrofitDemoActivity extends AppCompatActivity implements View.OnClickListener {

	@BindView(R.id.btn_case_one)
	Button btnCaseOne;

	private FragmentManager fragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_retrofit_demo);
		ButterKnife.bind(this);
		btnCaseOne.setOnClickListener(this);
		fragmentManager = getSupportFragmentManager();
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
			case R.id.btn_case_one:
				replaceFragment(new RetrofitOneFragment());
				break;
		}
	}

	private void replaceFragment(RetrofitOneFragment retrofitOneFragment) {
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.replace(R.id.retrofit_fragment_container, retrofitOneFragment);
		fragmentTransaction.commitNow();
	}
}