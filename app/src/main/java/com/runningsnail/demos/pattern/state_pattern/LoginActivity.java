package com.runningsnail.demos.pattern.state_pattern;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

	@BindView(R.id.et_input_user_name)
	EditText etInputUserName;
	@BindView(R.id.et_input_password)
	EditText etInputPassword;
	@BindView(R.id.btn_login)
	Button btnLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		ButterKnife.bind(this);
	}

	@OnClick(R.id.btn_login)
	public void onViewClicked(View view) {
		int id = view.getId();
		switch (id) {
			case R.id.btn_login:
				login();
				break;
		}
	}

	private void login() {
		UserStateController.getInstance().setUserState(new LoginInState());
		finish();
	}

}
