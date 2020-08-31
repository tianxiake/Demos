package com.runningsnail.demos.activity.widget;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomToastActivity extends AppCompatActivity {

	@BindView(R.id.btn_bottom_20)
	Button btnBottom20;
	@BindView(R.id.btn_top_20)
	Button btnTop20;
	@BindView(R.id.btn_default)
	Button btnDefault;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_toast);
		ButterKnife.bind(this);
	}

	@OnClick({R.id.btn_bottom_20, R.id.btn_top_20, R.id.btn_default})
	public void onClick(View view) {
		int id = view.getId();
		switch (id) {
			case R.id.btn_bottom_20:
				ToastUtil.showToastBottom20(this, "1111");
				break;
			case R.id.btn_top_20:
				ToastUtil.showToastTop20(this, "2222");
				break;
			case R.id.btn_default:
				ToastUtil.showToast(this, "3333");
				break;
			default:
				break;
		}
	}
}