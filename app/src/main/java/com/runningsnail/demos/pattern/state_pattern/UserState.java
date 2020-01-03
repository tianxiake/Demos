package com.runningsnail.demos.pattern.state_pattern;

import android.content.Context;

/**
 * @author yongjie created on 2020-01-02.
 */
public interface UserState {

	void forward(Context context);

	void comment(Context context);
}
