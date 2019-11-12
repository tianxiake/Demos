package com.runningsnail.demos.activity.view;

import android.os.Bundle;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CoordinatorLayoutWithAppBarLayoutActivity extends AppCompatActivity {

    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout_with_app_bar_layout);
        ButterKnife.bind(this);

        for (int i = 0; i < 100; i++) {
            tv.append(i + "");
            tv.append("\n");
        }
    }
}
