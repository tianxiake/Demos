package com.runningsnail.demos.activity.bottomnavigation;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.runningsnail.demos.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 使用三方框架实现
 * 优点：
 */
public class BottomWayThreeActivity extends AppCompatActivity {

    public static final String TAG = "BottomWayThreeActivity";
    @BindView(R.id.bnb_bottom_bar)
    BottomNavigationBar bottomNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_botoom_way_three);
        ButterKnife.bind(this);


        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.bottom_home_select, "主页")
                .setInactiveIconResource(R.drawable.bottom_home_unselect))
                .addItem(new BottomNavigationItem(R.drawable.bottom_mind_unselect, "创意")
                .setActiveColor(Color.RED)
                .setInActiveColor(Color.GRAY))
                .addItem(new BottomNavigationItem(R.drawable.bottom_money_unselect, "市场")
                        .setActiveColor(Color.BLUE)
                        .setInActiveColor(Color.GRAY))
                .addItem(new BottomNavigationItem(R.drawable.bottom_my_unselect, "我的")
                        .setActiveColor(Color.GREEN)
                        .setInActiveColor(Color.GRAY))
                .setFirstSelectedPosition(0)
                .setMode(BottomNavigationBar.MODE_FIXED)
                .initialise();


        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                Log.d(TAG, "onTabSelected position:" + position);
            }

            @Override
            public void onTabUnselected(int position) {
                Log.d(TAG, "onTabUnselected position:" + position);

            }

            @Override
            public void onTabReselected(int position) {
                Log.d(TAG, "onTabReselected position:" + position);

            }
        });
    }
}
