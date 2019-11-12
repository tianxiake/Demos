package com.runningsnail.demos.activity.viewanimation;

import android.os.Bundle;

import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 本质就是一个旋转动画
 */
public class LoaddingActivity extends AppCompatActivity {


    @BindView(R.id.iv_loading)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadding);

        ButterKnife.bind(this);

        imageView.post(new Runnable() {
            @Override
            public void run() {
                RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setDuration(500);
                rotateAnimation.setRepeatCount(Animation.INFINITE);
                rotateAnimation.setRepeatMode(Animation.RESTART);
                imageView.startAnimation(rotateAnimation);
            }
        });
    }
}
