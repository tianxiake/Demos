package com.runningsnail.demos.activity.animator;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnimatorScrollActivity extends AppCompatActivity {
	public static final String TAG = "AnimatorScrollActivity";

	@BindView(R.id.fl_container)
	FrameLayout flContainer;
	@BindView(R.id.btn_scroll)
	Button btnScroll;
	private boolean responseKeyEvent = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_animator_scroll);
		ButterKnife.bind(this);
		btnScroll.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d(TAG, "滑动");
				startAnimator();
			}
		});
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (!responseKeyEvent) {
			Log.d(TAG, "不响应按键事件");
			return true;
		}
		return super.dispatchKeyEvent(event);
	}

	public void startAnimator() {
		PropertyValuesHolder scrollY = PropertyValuesHolder.ofInt("ScrollY", flContainer.getScrollY(), (flContainer.getScrollY() -100));
		ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(flContainer, scrollY);
		objectAnimator.setDuration(100);
		objectAnimator.addListener(new Animator.AnimatorListener() {
			@Override
			public void onAnimationStart(Animator animation) {
				Log.d(TAG, "onAnimationStart");
				responseKeyEvent = false;
			}

			@Override
			public void onAnimationEnd(Animator animation) {
				Log.d(TAG, "onAnimationEnd");
				responseKeyEvent = true;
			}

			@Override
			public void onAnimationCancel(Animator animation) {

			}

			@Override
			public void onAnimationRepeat(Animator animation) {

			}
		});
		objectAnimator.start();
	}
}
