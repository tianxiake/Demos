package com.runningsnail.demos.activity.animator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.runningsnail.demos.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewPropertyAnimatorActivity extends AppCompatActivity {

    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.btn_do_animator)
    Button btnDoAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_property_animator);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.btn_do_animator)
    public void onClick(View view){

        tvContent.animate().scaleX(2f).alpha(0.5f).scaleX(0.5f).scaleY(2f).alpha(1).setDuration(5000);
    }
}
