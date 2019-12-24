package com.runningsnail.demos.activity.material;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author yongjie created on 2019-12-09.
 */
public class CordinatorLayoutTestActivity extends AppCompatActivity {

	@BindView(R.id.btn_observer)
	Button btnObserver;
	@BindView(R.id.btn_observer2)
	Button btnObserver2;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cordinator_layout_test);
		ButterKnife.bind(this);

		btnObserver.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_MOVE) {
					v.setX(event.getRawX() - v.getWidth() / 2);
					v.setY(event.getRawY() - v.getHeight() / 2);
				}
				return true;
			}
		});

		btnObserver2.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_MOVE) {
					v.setX(event.getRawX() - v.getWidth() / 2);
					v.setY(event.getRawY() - v.getHeight() / 2);
				}
				return true;
			}
		});
	}

}
