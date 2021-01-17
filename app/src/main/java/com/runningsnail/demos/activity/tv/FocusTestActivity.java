package com.runningsnail.demos.activity.tv;

import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.VideoView;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FocusTestActivity extends AppCompatActivity {
	private static final String TAG = "FocusTestActivity";
	@BindView(R.id.fl_container)
	FrameLayout flContainer;
	@BindView(R.id.btn_button)
	Button btnButton;
	@BindView(R.id.video_view)
	VideoView videoView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_focus_test2);
		ButterKnife.bind(this);
		videoView.getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
			@Override
			public void onGlobalFocusChanged(View oldFocus, View newFocus) {
				HiLogger.d(TAG, "oldFocus:%s,newFocus:%s", oldFocus, newFocus);
			}
		});
		btnButton.requestFocus();
		btnButton.postDelayed(new Runnable() {
			@Override
			public void run() {
				videoView.setVideoPath("http://vfx.mtime.cn/Video/2019/06/29/mp4/190629004821240734.mp4");
				videoView.start();

			}
		}, 3000);
	}
}