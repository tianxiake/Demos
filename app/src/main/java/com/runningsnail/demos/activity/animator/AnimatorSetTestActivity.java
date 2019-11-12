package com.runningsnail.demos.activity.animator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;

import android.view.View;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * AnimatorSet的简单使用
 *
 * @author yongjie created on 2019/1/7.
 */
public class AnimatorSetTestActivity extends AppCompatActivity {

    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.btn_play_together)
    Button btnPlayTogether;
    @BindView(R.id.btn_play_sequentially)
    Button btnPlaySequentially;

    AnimatorSet playTogether;
    AnimatorSet playSequentially;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_set_test);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn_play_together, R.id.btn_play_sequentially})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_play_together:
                playTogether();
                break;
            case R.id.btn_play_sequentially:
                playSequentially();
                break;
            default:
                break;
        }

    }

    private void playSequentially() {
        ObjectAnimator translationY = ObjectAnimator.ofFloat(tvContent, "translationY", 0, 100, 200, -100, 0);
        translationY.setStartDelay(2000);

        ObjectAnimator rotation = ObjectAnimator.ofFloat(tvContent, "rotation", 0, 300, -300, 0);
        playSequentially = new AnimatorSet();
        playSequentially.playSequentially(translationY,rotation);
        playSequentially.setDuration(2000);
        playSequentially.start();
    }

    private void playTogether() {
        ObjectAnimator translationY = ObjectAnimator.ofFloat(tvContent, "translationY", 0, 100, 200, -100, 0);
        translationY.setStartDelay(2000);

        ObjectAnimator rotation = ObjectAnimator.ofFloat(tvContent, "rotation", 0, 300, -300, 0);
        playTogether = new AnimatorSet();
        playTogether.playTogether(translationY,rotation);
        playTogether.setDuration(2000);
        playTogether.start();
    }
}
