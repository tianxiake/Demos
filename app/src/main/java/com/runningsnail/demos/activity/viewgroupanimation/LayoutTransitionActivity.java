package com.runningsnail.demos.activity.viewgroupanimation;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LayoutTransitionActivity extends AppCompatActivity {

    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.btn_remove)
    Button btnRemove;
    @BindView(R.id.ll_container)
    LinearLayout llContainer;

    int index = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_transation);
        ButterKnife.bind(this);

        //构建对象
        LayoutTransition layoutTransition = new LayoutTransition();

//        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0, 1);
//        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1.5f, 1f);
//        ObjectAnimator objectAnimator = (ObjectAnimator) ObjectAnimator.ofPropertyValuesHolder(null, alpha, scaleX);
        ObjectAnimator animIn = ObjectAnimator.ofFloat(null, "scaleX", 1.5f, 1);
        animIn.setDuration(500);
        layoutTransition.setAnimator(LayoutTransition.APPEARING, animIn);
        ObjectAnimator animOut = ObjectAnimator.ofFloat(null, "alpha", 1, 0);
        animOut.setDuration(500);
        layoutTransition.setAnimator(LayoutTransition.DISAPPEARING,animOut);

        //注入给容器
        llContainer.setLayoutTransition(layoutTransition);
    }

    @OnClick({R.id.btn_add, R.id.btn_remove})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_add:
                addChild();
                break;
            case R.id.btn_remove:
                removeChild();
                break;
            default:
                break;
        }
    }

    private void removeChild() {
        int childCount = llContainer.getChildCount();
        if (childCount > 0) {
            llContainer.removeViewAt(0);
        }
    }

    private void addChild() {
        Button button = new Button(this);
        button.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        button.setText("button" + index);
        llContainer.addView(button);
        index++;
    }
}
