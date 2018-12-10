package com.runningsnail.demos.activity.viewanimation;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.runningsnail.demos.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 帧动画，这种方式其实使用自动动画的Drawable来实现的
 */
public class FrameAnimActivity extends AppCompatActivity {


    @BindView(R.id.iv_content)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_anim);
        ButterKnife.bind(this);


        AnimationDrawable anim = (AnimationDrawable) imageView.getDrawable();
        anim.setOneShot(false);
        //启动动画
        anim.start();
    }
}
