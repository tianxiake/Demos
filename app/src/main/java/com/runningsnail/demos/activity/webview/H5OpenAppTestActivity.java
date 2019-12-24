package com.runningsnail.demos.activity.webview;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * test.html在文件上面
 */
public class H5OpenAppTestActivity extends AppCompatActivity {


	@BindView(R.id.btn_open_browser)
	Button btnOpenBrowser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_h5_open_app_test);
		ButterKnife.bind(this);
	}


	@OnClick(R.id.btn_open_browser)
	public void onViewClicked() {
		openBrowser();
	}

	private void openBrowser() {
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://192.168.222.133:8080/test.html"));
		startActivity(intent);
	}
}
