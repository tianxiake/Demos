package com.runningsnail.demos.pattern.state_pattern;

import android.content.Context;
import android.widget.Toast;

/**
 * @author yongjie created on 2020-01-02.
 */
public class LoginInState implements UserState {

	@Override
	public void forward(Context context) {
		Toast.makeText(context, "转发", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void comment(Context context) {
		Toast.makeText(context, "评论", Toast.LENGTH_SHORT).show();
	}
}
