package com.runningsnail.demos.activity.animator;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;
import com.runningsnail.demos.activity.animator.entity.MyCharacter;

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
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new CharEvaluator(), new MyCharacter('A'), new MyCharacter('Z'));
        valueAnimator.setDuration(10000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                MyCharacter animatedValue = (MyCharacter) animation.getAnimatedValue();
                contentTv.setText(animatedValue.ch + "");
            }
        });
        valueAnimator.start();
    }

}
