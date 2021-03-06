package com.runningsnail.demos.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SingleTaskActivity extends AppCompatActivity {

	public static final String TAG = "SingleTaskActivity";

	@BindView(R.id.btn_single_task)
	Button button;

	@BindView(R.id.btn_show_dialog)
	Button showDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_task);
		ButterKnife.bind(this);
		HiLogger.d(TAG, "onCreate()");
	}

	@OnClick({R.id.btn_single_task, R.id.btn_show_dialog})
	public void onClick(View view) {
		int id = view.getId();
		switch (id) {
			case R.id.btn_single_task:
				Intent intent = new Intent(this, SingleTaskActivity.class);
				startActivity(intent);

				break;
			case R.id.btn_show_dialog:
				Dialog dialogOne = createDialogOne();
				dialogOne.show();
				break;
			default:
				break;
		}

	}


	@Override
	protected void onStart() {
		super.onStart();
		HiLogger.d(TAG, "onStart()");
	}

	@Override
	protected void onResume() {
		super.onResume();
		HiLogger.d(TAG, "onResume()");

	}


	@Override
	protected void onPause() {
		super.onPause();
		HiLogger.d(TAG, "onPause()");
	}

	@Override
	protected void onStop() {
		super.onStop();
		HiLogger.d(TAG, "onStop()");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		HiLogger.d(TAG, "onDestroy()");
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		HiLogger.d(TAG, "onNewIntent()");
	}

	/**
	 * Dialog的基本构成部分全部展示
	 */
	public Dialog createDialogOne() {
		AlertDialog build = new AlertDialog.Builder(this)
				.setTitle("Hello AlertDialog")
				.setIcon(R.mipmap.ic_launcher)
				.setMessage("你好我是一个对话框")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(SingleTaskActivity.this, "确定", Toast.LENGTH_SHORT).show();
					}
				}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(SingleTaskActivity.this, "取消", Toast.LENGTH_SHORT).show();
					}
				}).setNeutralButton("中间", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(SingleTaskActivity.this, "中间", Toast.LENGTH_SHORT).show();
					}
				}).create();

		return build;
	}
}
