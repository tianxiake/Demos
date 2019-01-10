package com.runningsnail.demos.activity.animator;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.runningsnail.demos.R;
import com.runningsnail.demos.activity.animator.entity.MyCharacter;
import com.runningsnail.demos.activity.animator.widget.MyTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * PropertyValueHolder OfObject() 方法的使用
 *
 * @author yongjie created on 2019/1/10.
 */
public class PropertyValueHolderOfObjectActivity extends AppCompatActivity {

    @BindView(R.id.tv_content)
    MyTextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_value_holder_of_object);
        ButterKnife.bind(this);

        tvContent.postDelayed(new Runnable() {
            @Override
            public void run() {

                doAnimation();

            }
        }, 3000);
    }

    private void doAnimation() {
        PropertyValuesHolder propertyValuesHolder = PropertyValuesHolder.ofObject("CharText", new CharEvaluator(),
                new MyCharacter('A'), new MyCharacter('Z'));
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(tvContent, propertyValuesHolder);
        objectAnimator.setDuration(3000).start();
    }
}
