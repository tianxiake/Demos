package com.runningsnail.demos.activity.androidapi;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AndroidApiTestActivity extends AppCompatActivity {

    private static final String TAG = "AndroidApiTestActivity";
    @BindView(R.id.btn_count_down)
    Button btnCountDown;
    @BindView(R.id.tv_count_down)
    TextView tvCountDown;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_api_test);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_count_down})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_count_down:
                countDown();
                break;
            default:
                break;
        }
    }

    /**
     * 倒计时API
     */
    private void countDown() {
        long time = 30100;
        final long interval = 1000;
        tvCountDown.setText((time / interval) + "");
        countDownTimer = new CountDownTimer(time, interval) {
            @Override
            public void onTick(long millisUntilFinished) {
                HiLogger.i(TAG, "millisUntilFinished:%s", millisUntilFinished);
                tvCountDown.setText((millisUntilFinished / interval) + "");
            }

            @Override
            public void onFinish() {
                HiLogger.i(TAG, "onFinish");
                tvCountDown.setText("倒计时完成");
            }
        };
        countDownTimer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
