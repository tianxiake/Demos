package com.runningsnail.demos.activity.viewanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.runningsnail.demos.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScanActivity extends AppCompatActivity {

    @BindView(R.id.iv_one)
    ImageView oneIv;

    @BindView(R.id.iv_two)
    ImageView twoIv;

    @BindView(R.id.iv_three)
    ImageView threeIv;

    @BindView(R.id.iv_content)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        ButterKnife.bind(this);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation(oneIv, 0);
                startAnimation(twoIv, 600);
                startAnimation(threeIv, 1200);
            }
        });
    }


    public void startAnimation(View view, long offset) {
        view.clearAnimation();
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setDuration(1800);

        ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 3f, 1f, 3f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setRepeatCount(Animation.INFINITE);
        scaleAnimation.setRepeatMode(Animation.RESTART);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
        alphaAnimation.setRepeatCount(Animation.INFINITE);
        alphaAnimation.setRepeatMode(Animation.RESTART);

        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setStartOffset(offset);
        view.startAnimation(animationSet);
    }
}
