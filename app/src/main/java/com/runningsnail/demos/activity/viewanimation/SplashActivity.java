package com.runningsnail.demos.activity.viewanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.runningsnail.demos.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 镜头由远到近的效果本质使用的就是BounceInterceptor这个动画曲线
 */

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.btn_action)
    Button button;

    @BindView(R.id.iv_content)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setVisibility(View.GONE);
                imageView.setImageResource(R.drawable.kuiba_manji);
                ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 1.2f, 1f, 1.2f, Animation.RELATIVE_TO_SELF,
                        0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                scaleAnimation.setInterpolator(new BounceInterpolator());
                scaleAnimation.setDuration(6000);
                imageView.startAnimation(scaleAnimation);
            }
        });

    }
}
