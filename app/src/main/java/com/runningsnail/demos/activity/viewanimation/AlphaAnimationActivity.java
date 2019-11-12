package com.runningsnail.demos.activity.viewanimation;

import android.os.Bundle;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;
import com.runningsnail.demos.activity.viewanimation.interceptor.MyPathInterceptor;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlphaAnimationActivity extends AppCompatActivity {

    @BindView(R.id.btn_action)
    Button button;

    @BindView(R.id.iv_content)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation);

        ButterKnife.bind(this);
        button.setText("开始Alpha动画");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(AlphaAnimationActivity.this, R.anim.anim_alpha_one);
                android.graphics.Path path = new android.graphics.Path();
                path.moveTo(0, 0);
                path.lineTo(0.5f, 0.5f);
                path.lineTo(0.5f, 0);
                path.lineTo(1f, 1f);
                MyPathInterceptor myPathInterceptor = new MyPathInterceptor(path);
                animation.setInterpolator(myPathInterceptor);
                imageView.startAnimation(animation);
            }
        });
    }
}
