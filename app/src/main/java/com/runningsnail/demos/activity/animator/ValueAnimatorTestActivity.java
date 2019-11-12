package com.runningsnail.demos.activity.animator;

import android.animation.ValueAnimator;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;

/**
 * ValueAnimator简单使用
 */
public class ValueAnimatorTestActivity extends AppCompatActivity {

    public static final String TAG = "ValueAnimatorTestActivity";

    private ValueAnimator valueAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animator_test);


        valueAnimator = ValueAnimator.ofInt(0, 400);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                HiLogger.d(TAG, "value" + animation.getAnimatedValue() + ",time:" + animation.getCurrentPlayTime());
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                valueAnimator.start();
            }
        }, 500);
    }
}
