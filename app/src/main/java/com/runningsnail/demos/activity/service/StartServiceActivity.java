package com.runningsnail.demos.activity.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartServiceActivity extends AppCompatActivity {

	@BindView(R.id.btn_start_service)
	Button startServiceBtn;
	@BindView(R.id.btn_stop_service)
	Button stopServiceBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_service);
		ButterKnife.bind(this);
	}


	@OnClick({R.id.btn_start_service, R.id.btn_stop_service})
	public void onClick(View view) {
		int id = view.getId();
		switch (id) {
			case R.id.btn_start_service:
				testStartService();
				break;
			case R.id.btn_stop_service:
				testStopService();
				break;
			default:
				break;
		}
	}

	private void testStopService() {
		Intent intent = new Intent(this, MyService.class);
		stopService(intent);
	}

	private void testStartService() {
		Intent intent = new Intent(this, MyService.class);
		startService(intent);
		ToastUtil.showToast(this,"启动服务成功");
	}
}
