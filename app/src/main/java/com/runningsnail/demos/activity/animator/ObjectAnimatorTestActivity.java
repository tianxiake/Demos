package com.runningsnail.demos.activity.animator;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 体验ObjectAnimator动画
 */
public class ObjectAnimatorTestActivity extends AppCompatActivity {

    @BindView(R.id.btn_scale)
    Button btnScale;
    @BindView(R.id.btn_translate)
    Button btnTranslate;
    @BindView(R.id.tv_animator)
    TextView tvAnimator;
    @BindView(R.id.btn_rotate)
    Button btnRotate;
    @BindView(R.id.btn_alpha)
    Button btnAlpha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animator_test);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn_scale, R.id.btn_translate, R.id.btn_rotate, R.id.btn_alpha})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_scale:
                scaleAnimation();
                break;
            case R.id.btn_translate:
                translateAnimation();
                break;
            case R.id.btn_rotate:
                rotateAnimation();
                break;
            case R.id.btn_alpha:
                alphaAnimation();
                break;
            default:
                break;
        }

    }

    private void scaleAnimation() {
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(tvAnimator, "ScaleX", 1f, 2f, 3f, 2f, 1f);
        scaleX.setDuration(3000);
        scaleX.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleX.start();
    }

    private void translateAnimation() {
        ObjectAnimator translationZ = ObjectAnimator.ofFloat(tvAnimator, "TranslationZ", 0, 100f, 200f, 100);
        translationZ.setDuration(5000);
        translationZ.setInterpolator(new LinearInterpolator());
        translationZ.start();
    }

    private void rotateAnimation() {
        ObjectAnimator rotationY = ObjectAnimator.ofFloat(tvAnimator, "RotationY", 0, 270, -270, 0);
        rotationY.setDuration(5000);
        rotationY.setInterpolator(new LinearInterpolator());
        rotationY.start();

    }

    private void alphaAnimation() {
        ObjectAnimator alpha = ObjectAnimator.ofFloat(tvAnimator, "alpha", 1, 0, 0.5f, 1);
        alpha.setDuration(5000);
        alpha.setInterpolator(new LinearInterpolator());
        alpha.start();
    }


}
