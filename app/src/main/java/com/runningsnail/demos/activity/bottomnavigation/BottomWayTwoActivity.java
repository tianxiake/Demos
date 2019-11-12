package com.runningsnail.demos.activity.bottomnavigation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.runningsnail.demos.common.utils.HiLogger;
import com.runningsnail.demos.R;
import com.runningsnail.demos.fragment.PageFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * RadioGroup+RadioButton
 * <p>
 * RadioGroup管理RadioButton的互斥效果，本质还是使用TextView drawableTop/Right/Bottom/Left这个特殊属性来实现图片显示
 *
 * 缺点：
 * 1>图片的尺寸大小我们不能指定大小，如果图片过大，字体会显示不全，这个没有用一个单独的ImageView好用
 *
 * 优点：
 * 对连续点击不用我们自己去处理
 * 简单
 *
 * @author yongjie created on 2018/12/3.
 */
public class BottomWayTwoActivity extends AppCompatActivity {

    public static final String TAG = "BottomWayTwoActivity";
    @BindView(R.id.rg_group)
    RadioGroup radioGroup;

    @BindView(R.id.rb_home)
    RadioButton homeRb;

    @BindView(R.id.fl_container)
    FrameLayout frameLayout;

    FragmentManager fragmentManager;

    Fragment homeFragment;
    Fragment mindFragment;
    Fragment moneyFragment;
    Fragment myFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_way_two);
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                HiLogger.d(TAG, "checkedId:" + checkedId);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                hideAllFragment(fragmentTransaction);
                switch (checkedId) {
                    case R.id.rb_home:
                        HiLogger.d(TAG, "home");
                        if (homeFragment == null) {
                            Bundle bundle = new Bundle();
                            bundle.putString("content", "第一个Fragment");
                            homeFragment = PageFragment.newInstance(bundle);
                            fragmentTransaction.add(R.id.fl_container, homeFragment);
                        }
                        fragmentTransaction.show(homeFragment);
                        break;
                    case R.id.rb_mind:
                        HiLogger.d(TAG, "mind");
                        if (mindFragment == null) {
                            Bundle bundle = new Bundle();
                            bundle.putString("content", "第二个Fragment");
                            mindFragment = PageFragment.newInstance(bundle);
                            fragmentTransaction.add(R.id.fl_container, mindFragment);
                        }
                        fragmentTransaction.show(mindFragment);
                        break;
                    case R.id.rb_money:
                        HiLogger.d(TAG, "money");
                        if (moneyFragment == null) {
                            Bundle bundle = new Bundle();
                            bundle.putString("content", "第三个Fragment");
                            moneyFragment = PageFragment.newInstance(bundle);
                            fragmentTransaction.add(R.id.fl_container, moneyFragment);
                        }
                        fragmentTransaction.show(moneyFragment);
                        break;
                    case R.id.rb_my:
                        HiLogger.d(TAG, "my");
                        if (myFragment == null) {
                            Bundle bundle = new Bundle();
                            bundle.putString("content", "第四个Fragment");
                            myFragment = PageFragment.newInstance(bundle);
                            fragmentTransaction.add(R.id.fl_container, myFragment);
                        }
                        fragmentTransaction.show(myFragment);
                        break;
                    default:
                        break;
                }
                fragmentTransaction.commit();
            }
        });

        //设置默认选中项
        homeRb.setChecked(true);
        // FIXME: 2018/12/3 RadioGroup也可以设置默认选中项，但是这个方式设置,onCheckedChanged方法首次会触发两次,原因就在这个方法内部
//        radioGroup.check(R.id.rb_home);
    }


    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (homeFragment != null) {
            fragmentTransaction.hide(homeFragment);
        }

        if (mindFragment != null) {
            fragmentTransaction.hide(mindFragment);
        }

        if (moneyFragment != null) {
            fragmentTransaction.hide(moneyFragment);
        }

        if (myFragment != null) {
            fragmentTransaction.hide(myFragment);
        }
    }

}
