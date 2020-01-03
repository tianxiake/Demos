package com.runningsnail.demos.pattern.state_pattern;

import android.content.Context;

/**
 * @author yongjie created on 2020-01-02.
 */
public class UserStateController {


	private UserState userState = new LoginOutState();
	private static UserStateController userStateController;

	private UserStateController() {
	}

	public static UserStateController getInstance() {
		return Holder.userStateController;
	}


	public void setUserState(UserState userState) {
		this.userState = userState;
	}

	public void loginInState() {
		setUserState(new LoginInState());
	}

	public void loginOutState() {
		setUserState(new LoginOutState());
	}

	public UserState getLoginState() {
		return userState;
	}


	public void forward(Context context) {
		userState.forward(context);
	}


	public void comment(Context context) {
		userState.comment(context);
	}

	public static class Holder {
		public static UserStateController userStateController = new UserStateController();
	}

}
