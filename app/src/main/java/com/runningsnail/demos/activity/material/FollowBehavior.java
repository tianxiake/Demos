package com.runningsnail.demos.activity.material;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.runningsnail.demos.common.utils.HiLogger;

/**
 * @author yongjie created on 2019-12-09.
 */
public class FollowBehavior extends CoordinatorLayout.Behavior<TextView> {

	private static final String TAG = "FollowBehavior";

	public FollowBehavior() {
	}

	/**
	 * xml中配置此构造起必须重写
	 *
	 * @param context
	 * @param attrs
	 */
	public FollowBehavior(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull TextView child, @NonNull View dependency) {
		HiLogger.e(TAG, "layoutDependsOn", new Throwable());
		HiLogger.i(TAG, "layoutDependsOn child:%s dependency:%s", child, dependency);
		return dependency instanceof TextView;
	}

	@Override
	public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull TextView child, @NonNull View dependency) {
		HiLogger.i(TAG, "onDependentViewChanged %s", dependency);
		float x = dependency.getX();
		float y = dependency.getY();
		child.setX(x);
		child.setY(y);
		return true;
	}
}
