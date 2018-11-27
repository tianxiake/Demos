package com.runningsnail.demos.activity.bottomnavigation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.runningsnail.demos.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * LinearLayout实现底部导航栏
 */

public class BottomWayOneActivity extends AppCompatActivity {

    @BindView(R.id.ll_bottom_bar)
    LinearLayout bottomBar;

    @BindView(R.id.fl_container)
    FrameLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_way_one);
        ButterKnife.bind(this);
        initBottomBar();
    }

    private void initBottomBar() {
        View bottomView = LayoutInflater.from(this).inflate(R.layout.layout_item_one, null);
        View viewById = bottomView.findViewById(R.id.tv_text);
    }
}
