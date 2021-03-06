package com.runningsnail.demos.activity.viewanimation;

import android.os.Bundle;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SetAnimationActivity extends AppCompatActivity {

    @BindView(R.id.btn_action)
    Button button;

    @BindView(R.id.iv_content)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation);

        ButterKnife.bind(this);
        button.setText("开始Set动画集合");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(SetAnimationActivity.this, R.anim.anim_set_one);
                imageView.startAnimation(animation);

            }
        });
    }
}
