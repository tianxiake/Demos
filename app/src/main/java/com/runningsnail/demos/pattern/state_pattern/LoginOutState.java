package com.runningsnail.demos.pattern.state_pattern;

import android.content.Context;
import android.content.Intent;

/**
 * @author yongjie created on 2020-01-02.
 */
public class LoginOutState implements UserState {

	@Override
	public void forward(Context context) {
		goToLoginActivity(context);
	}


	@Override
	public void comment(Context context) {
		goToLoginActivity(context);
	}


	private void goToLoginActivity(Context context) {
		Intent intent = new Intent(context, LoginActivity.class);
		context.startActivity(intent);
	}

}

