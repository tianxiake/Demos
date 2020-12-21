package com.runningsnail.demos.activity.other;

import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;

public class KillProcessActivity extends AppCompatActivity implements View.OnClickListener {

	private Button buttonNew;
	private Button buttonExit;
	private Button buttonExit1;
	private Button buttonKillProcess;
	private Button buttonCrash;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kill_process);
		buttonNew = findViewById(R.id.btn_new);
		buttonExit = findViewById(R.id.btn_system_exit);
		buttonKillProcess = findViewById(R.id.btn_kill_process);
		buttonExit1 = findViewById(R.id.btn_system_exit1);
		buttonCrash = findViewById(R.id.btn_crash);

		buttonNew.setOnClickListener(this);
		buttonExit.setOnClickListener(this);
		buttonExit1.setOnClickListener(this);
		buttonKillProcess.setOnClickListener(this);
		buttonCrash.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
			case R.id.btn_new:
				createNewActivity();
				break;
			case R.id.btn_system_exit:
				systemExitActivity();
				break;
			case R.id.btn_kill_process:
				killProcessActivity();
				break;
			case R.id.btn_system_exit1:
				systemExit1Activity();
				break;
			case R.id.btn_crash:
				crashApp();
				break;
			default:
				break;
		}
	}

	private void crashApp() {
		throw new RuntimeException("crash");
	}

	private void systemExit1Activity() {
		System.exit(1);
	}

	private void killProcessActivity() {
		Process.killProcess(Process.myPid());
	}

	private void systemExitActivity() {
		System.exit(0);
	}

	private void createNewActivity() {
		Intent intent = new Intent(this, KillProcessActivity.class);
		startActivity(intent);
	}
}