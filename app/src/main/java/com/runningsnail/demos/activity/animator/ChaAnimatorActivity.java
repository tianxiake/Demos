package com.runningsnail.demos.activity.animator;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.runningsnail.demos.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChaAnimatorActivity extends AppCompatActivity {

    @BindView(R.id.tv_content)
    TextView contentTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cha_animator);
        ButterKnife.bind(this);
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new CharEvaluator(), new CharObject('A'), new CharObject('Z'));
        valueAnimator.setDuration(10000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                CharObject animatedValue = (CharObject) animation.getAnimatedValue();
                contentTv.setText(animatedValue.ch + "");
            }
        });
        valueAnimator.start();
    }


    public static class CharObject {
        char ch;

        public CharObject(char ch) {
            this.ch = ch;
        }

        @Override
        public String toString() {
            return "CharObject{" +
                    "ch=" + ch +
                    '}';
        }
    }
}
