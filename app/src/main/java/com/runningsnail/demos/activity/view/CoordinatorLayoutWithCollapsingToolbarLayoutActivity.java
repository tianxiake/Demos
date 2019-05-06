package com.runningsnail.demos.activity.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.widget.TextView;

import com.runningsnail.demos.R;

public class CoordinatorLayoutWithCollapsingToolbarLayoutActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout_with_collapsing_toolbar_layout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("我是标题");
        toolbar.setLogo(getResources().getDrawable(R.mipmap.ic_launcher));
        setSupportActionBar(toolbar);

        TextView tv = findViewById(R.id.tv);
        for (int i = 0; i < 100; i++) {
            tv.append(i + "");
            tv.append("\n");
        }

    }
}
