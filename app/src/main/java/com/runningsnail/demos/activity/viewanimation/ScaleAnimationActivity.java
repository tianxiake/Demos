package com.runningsnail.demos.activity.viewanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.runningsnail.demos.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScaleAnimationActivity extends AppCompatActivity {


    @BindView(R.id.btn_action)
    Button button;

    @BindView(R.id.iv_content)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation);
        ButterKnife.bind(this);

        button.setText("开始Scale动画");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(ScaleAnimationActivity.this, R.anim.anim_scale_one);
                animation.cancel();
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                imageView.startAnimation(animation);

            }
        });
    }
}
