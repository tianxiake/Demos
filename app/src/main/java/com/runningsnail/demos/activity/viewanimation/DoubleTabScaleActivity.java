package com.runningsnail.demos.activity.viewanimation;


import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DoubleTabScaleActivity extends AppCompatActivity {

    @BindView(R.id.iv_content)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_tab_scale);

        ButterKnife.bind(this);

    }
}
