package com.runningsnail.demos.activity.other;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SpTestActivity extends AppCompatActivity implements View.OnClickListener {
	private static final String TAG = "SpTestActivity";

	private Button btnWrite;
	private Button btnRead;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sp_test);
		btnWrite = findViewById(R.id.btn_write);
		btnRead = findViewById(R.id.btn_read);

		btnWrite.setOnClickListener(this);
		btnRead.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
			case R.id.btn_write:
				cacheSetSp();
				break;
			case R.id.btn_read:
				readSetSp();
				break;
			default:
				break;
		}
	}

	private void readSetSp() {
		SharedPreferences sharedPreferences = this.getSharedPreferences("emergencyUrl", Context.MODE_PRIVATE);
		Set<String> emergencyUrls = sharedPreferences.getStringSet("emergencyUrls", null);
		Iterator<String> iterator = emergencyUrls.iterator();
		while (iterator.hasNext()) {
			String next = iterator.next();
			HiLogger.d(TAG, "next: %s", next);
		}
	}

	private void cacheSetSp() {
		SharedPreferences sharedPreferences = this.getSharedPreferences("emergencyUrl", Context.MODE_PRIVATE);
		SharedPreferences.Editor edit = sharedPreferences.edit();
		HashSet<String> urls = new HashSet<>();
		urls.add("http://xxx");
		urls.add("http://xxx1");
		urls.add("http://xxx2");
		edit.putStringSet("emergencyUrls", urls);
		edit.commit();
	}
}