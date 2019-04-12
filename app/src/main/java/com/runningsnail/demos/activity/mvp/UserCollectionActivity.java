package com.runningsnail.demos.activity.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.runningsnail.demos.R;


public class UserCollectionActivity extends AppCompatActivity implements UserCollectionView{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_collection);
	}

	@Override
	public void showAddErrorProgressBar() {

	}
}
