package com.runningsnail.demos.activity.animator;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 一个PropertyValueHolder持有了一个属性值变化
 */
public class PropertyViewHolderActivity extends AppCompatActivity {

    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.btn_work)
    Button btnWork;
    @BindView(R.id.btn_other)
    Button btnOther;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_view_holder);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_work,R.id.btn_other})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_work:
                startPVHAnimator();
                break;
            case R.id.btn_other:
                startNoPVHAnimator();
                break;
            default:
                break;
        }
    }

    private void startNoPVHAnimator() {
        ObjectAnimator translationX = ObjectAnimator.ofFloat(tvContent, "translationX", 0, 100, 0);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(tvContent, "scaleX", 1, 2, 1);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(translationX, scaleX);
        animatorSet.setDuration(2000);
        animatorSet.setInterpolator(new BounceInterpolator());
        animatorSet.start();
    }

    private void startPVHAnimator() {
        PropertyValuesHolder translationX = PropertyValuesHolder.ofFloat("translationX", 0, 100, 0);
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1, 2, 1);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(tvContent, translationX, scaleX);
        objectAnimator.setDuration(2000);
        objectAnimator.setInterpolator(new BounceInterpolator());
        objectAnimator.start();
    }
}
