package com.runningsnail.demos.activity.tv;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;

import java.io.FileOutputStream;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RuntimeExecuteActivity extends AppCompatActivity {

	public static final String TAG = "RuntimeExecuteActivity";
	@BindView(R.id.btn_tv_test_logcat)
	Button btnTvTestLogcat;
	@BindView(R.id.btn_tv_test_tcpdump)
	Button btnTvTestTcpdump;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_runtime_execute);
		ButterKnife.bind(this);
	}


	@OnClick({R.id.btn_tv_test_logcat, R.id.btn_tv_test_tcpdump})
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
			case R.id.btn_tv_test_logcat:
				testLogcat();
				break;
			case R.id.btn_tv_test_tcpdump:
				testTcpdump();
				break;
			default:
				break;
		}
	}

	private void testTcpdump() {
		HiLogger.i(TAG, "test tcpdump start");
		try {
			String command = "tcpdump -i any -p -s 0 -l";
			Runtime runtime = Runtime.getRuntime();
			Process exec = runtime.exec(command);
			InputStream inputStream = exec.getErrorStream();
			FileOutputStream fileOutputStream = new FileOutputStream("/sdcard/test_capture.pcap");
			byte[] bufferBytes = new byte[1 * 1024 * 1024];
			int length = 0;
			int times = 0;
			while ((length = inputStream.read(bufferBytes)) != -1) {
				HiLogger.d(TAG, "length:" + length + ",times:" + times);
				times++;
				fileOutputStream.write(bufferBytes, 0, bufferBytes.length);
				if (times > 100) {
					break;
				}
			}
			inputStream.close();
			fileOutputStream.flush();
			fileOutputStream.close();
			HiLogger.i(TAG, "test tcpdump end");
		} catch (Exception e) {
			HiLogger.e(TAG, "tcpdump命令执行失败", e);
		}
	}

	private void testLogcat() {
		HiLogger.i(TAG, "test logcat start");
		try {
			String command = "logcat";
			Runtime runtime = Runtime.getRuntime();
			Process exec = runtime.exec(command);
			InputStream inputStream = exec.getInputStream();
			FileOutputStream fileOutputStream = new FileOutputStream("/sdcard/test_logcat.logcat");
			byte[] bufferBytes = new byte[1 * 1024 * 1024];
			int length = 0;
			int times = 0;
			while ((length = inputStream.read(bufferBytes)) != -1) {
				HiLogger.d(TAG, "length:" + length + ",times:" + times);
				times++;
				fileOutputStream.write(bufferBytes, 0, bufferBytes.length);
				if (times > 100) {
					break;
				}
			}
			inputStream.close();
			fileOutputStream.flush();
			fileOutputStream.close();
			HiLogger.i(TAG, "test logcat end");
		} catch (Exception e) {
			HiLogger.e(TAG, "logcat命令执行失败", e);
		}
	}
}
