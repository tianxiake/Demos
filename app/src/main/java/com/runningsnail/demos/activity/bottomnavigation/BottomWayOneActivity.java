package com.runningsnail.demos.activity.bottomnavigation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.runningsnail.demos.common.utils.HiLog;
import com.runningsnail.demos.R;
import com.runningsnail.demos.fragment.PageFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * LinearLayout+TextView实现底部导航栏
 * 本质是使用TextView drawableTop/Right/Bottom/Left这个特殊属性来实现图片显示
 *
 * 缺点：
 * 1>图片的尺寸大小我们不能指定大小，如果图片过大，字体会显示不全，这个没有用一个单独的ImageView好用
 * 2>对连续点击要自己手动处理
 *
 * 优点：
 * 简单
 */

public class BottomWayOneActivity extends AppCompatActivity implements TextView.OnClickListener {

    public static final String TAG = "BottomWayOneActivity";
    @BindView(R.id.ll_bottom_bar)
    LinearLayout bottomBar;
    @BindView(R.id.fl_container)
    FrameLayout container;
    @BindView(R.id.tv_home)
    TextView homeTv;
    @BindView(R.id.tv_mind)
    TextView mindTv;
    @BindView(R.id.tv_money)
    TextView moneyTv;
    @BindView(R.id.tv_my)
    TextView myTv;

    private View currentSelectView = homeTv;

    private List<TextView> bottomViews = new ArrayList<>();

    private List<Fragment> fragments = new ArrayList<>();

    private Fragment homeFragment;
    private Fragment mindFragment;
    private Fragment moneyFragment;
    private Fragment myFragment;
    private FragmentManager supportFragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_way_one);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        bottomViews.add(homeTv);
        bottomViews.add(mindTv);
        bottomViews.add(moneyTv);
        bottomViews.add(myTv);
        //初始化选中一个
        setBottomViewSelect(homeTv);

        homeTv.setOnClickListener(this);
        mindTv.setOnClickListener(this);
        moneyTv.setOnClickListener(this);
        myTv.setOnClickListener(this);

        //首先显示一个Fragment
        supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("content", "第一个Fragment");
        homeFragment = PageFragment.newInstance(bundle);
        fragmentTransaction.add(R.id.fl_container, homeFragment);
        fragmentTransaction.commit();
        fragments.add(homeFragment);
    }


    @Override
    public void onClick(View v) {
        if (v != currentSelectView) {
            HiLog.d(TAG, "onClick:" + v);
            FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
            switch (v.getId()) {
                case R.id.tv_home:
                    setBottomViewSelect(homeTv);
                    if (homeFragment == null) {
                        Bundle bundle = new Bundle();
                        bundle.putString("content", "第一个Fragment");
                        homeFragment = PageFragment.newInstance(bundle);
                        fragmentTransaction.add(R.id.fl_container, homeFragment);
                        fragments.add(homeFragment);
                    }
                    showFragment(homeFragment);
                    break;
                case R.id.tv_mind:
                    setBottomViewSelect(mindTv);
                    if (mindFragment == null) {
                        Bundle bundle = new Bundle();
                        bundle.putString("content", "第二个Fragment");
                        mindFragment = PageFragment.newInstance(bundle);
                        fragmentTransaction.add(R.id.fl_container, mindFragment);
                        fragments.add(mindFragment);
                    }
                    showFragment(mindFragment);
                    break;
                case R.id.tv_money:
                    setBottomViewSelect(moneyTv);
                    if (moneyFragment == null) {
                        Bundle bundle = new Bundle();
                        bundle.putString("content", "第三个Fragment");
                        moneyFragment = PageFragment.newInstance(bundle);
                        fragmentTransaction.add(R.id.fl_container, moneyFragment);
                        fragments.add(moneyFragment);
                    }
                    showFragment(moneyFragment);
                    break;
                case R.id.tv_my:
                    setBottomViewSelect(myTv);
                    if (myFragment == null) {
                        Bundle bundle = new Bundle();
                        bundle.putString("content", "第四个Fragment");
                        myFragment = PageFragment.newInstance(bundle);
                        fragmentTransaction.add(R.id.fl_container, myFragment);
                        fragments.add(myFragment);
                    }
                    showFragment(myFragment);
                    break;
                default:
                    break;
            }
            currentSelectView = v;
            fragmentTransaction.commit();
        }
    }

    private void showFragment(Fragment mind) {
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        for (Fragment fragment : fragments) {
            if (mind == fragment) {
                fragmentTransaction.show(fragment);
            } else {
                fragmentTransaction.hide(fragment);
            }
        }
        fragmentTransaction.commit();
    }


    /**
     * 设置指定的底部导航栏View选中
     */
    private void setBottomViewSelect(TextView textView) {
        for (TextView bottomView : bottomViews) {
            if (bottomView == textView) {
                bottomView.setSelected(true);
            } else {
                bottomView.setSelected(false);
            }
        }
    }


}
