package com.runningsnail.demos.pattern.state_pattern;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomActivity extends AppCompatActivity {

	@BindView(R.id.btn_transmit)
	Button btnTransmit;
	@BindView(R.id.btn_logi)
	Button btnLogi;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcom);
		ButterKnife.bind(this);
		this.context = this;
	}

	@OnClick({R.id.btn_transmit, R.id.btn_logi})
	public void onClicked(View view) {
		switch (view.getId()) {
			case R.id.btn_transmit:
				UserStateController.getInstance().forward(this);
				break;
			case R.id.btn_logi:
				UserStateController.getInstance().comment(this);
				break;
		}
	}

}
