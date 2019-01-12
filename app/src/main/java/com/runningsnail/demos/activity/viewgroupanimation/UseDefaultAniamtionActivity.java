package com.runningsnail.demos.activity.viewgroupanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.runningsnail.demos.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UseDefaultAniamtionActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_use_default_aniamtion);
        ButterKnife.bind(this);
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
