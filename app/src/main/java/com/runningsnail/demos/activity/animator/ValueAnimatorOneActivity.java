package com.runningsnail.demos.activity.animator;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLog;

import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ValueAnimatorOneActivity extends AppCompatActivity {

	public static final String TAG = "ValueAnimatorOneActivity";
	@BindView(R.id.btn_do_animator)
	Button btnDoAnimator;
	@BindView(R.id.tv_move)
	TextView tvMove;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_value_animator_one);
		ButterKnife.bind(this);
	}

	@OnClick(R.id.btn_do_animator)
	public void onClick(View view) {
		doAnimation();
	}

	private void doAnimation() {
		ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 400);
		valueAnimator.setDuration(3000);
		valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				Integer animatedValue = (Integer) animation.getAnimatedValue();
				HiLog.d(TAG, "animateValue:" + animatedValue);
				tvMove.layout(animatedValue, animatedValue, animatedValue + tvMove.getWidth(), animatedValue + tvMove.getHeight());
			}
		});
		valueAnimator.start();
	}
}
