package com.runningsnail.demos.activity.webview;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;

public class OriginAppActivity extends AppCompatActivity {
	private static final String TAG = "OriginAppActivity";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_origin_app);
		Intent intent = getIntent();
		HiLogger.i(TAG, "intent %s", intent);
		if (intent != null) {
			Uri uri = intent.getData();
			String id = uri.getQueryParameter("id");
			String name = uri.getQueryParameter("name");
			String age = uri.getQueryParameter("age");
			HiLogger.i(TAG, "id:%s name:%s age:%s ", id, name, age);

		}
	}
}
