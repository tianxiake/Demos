package com.runningsnail.demos.activity.animator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 浮动菜单
 *
 * @author yongjie created on 2019/1/8.
 */
public class FloatMenuActivity extends AppCompatActivity {

    @BindView(R.id.tv_setting)
    TextView tvSetting;
    @BindView(R.id.tv_copy)
    TextView tvCopy;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.tv_star)
    TextView tvStar;
    @BindView(R.id.tv_menu)
    TextView tvMenu;

    private boolean isMenuOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_menu);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_setting, R.id.tv_copy, R.id.tv_share, R.id.tv_star, R.id.tv_menu})
    public void onViewClicked(View view) {
        Toast.makeText(this,view.getId()+"",Toast.LENGTH_LONG).show();
        if (!isMenuOpen) {
            openMenu();
            isMenuOpen = true;
        } else {
            closeMenu();
            isMenuOpen = false;
        }
    }

    private void closeMenu() {

    }

    private void openMenu() {
        doAnimationOpen(tvSetting, 0, 4, 300);
        doAnimationOpen(tvCopy, 1, 4, 300);
        doAnimationOpen(tvShare, 2, 4, 300);
        doAnimationOpen(tvStar, 3, 4, 300);
    }

    private void doAnimationOpen(View view, int index, int total, int radius) {

        double degree = Math.toRadians(90) / (total - 1) * index;
        int translationX = -(int) Math.sin(degree) * radius;
        int translationY = -(int) Math.cos(degree) * radius;

        ObjectAnimator translationXObj = ObjectAnimator.ofFloat(view, "translationX", 0, translationX);
        ObjectAnimator translationYObj = ObjectAnimator.ofFloat(view, "translationY", 0, translationY);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 0, 1);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 0, 1);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 0, 1);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(500);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.playTogether(translationXObj, translationYObj, scaleX, scaleY, alpha);
        animatorSet.start();
    }

}
