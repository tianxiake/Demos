package com.runningsnail.demos.activity;

import android.animation.Animator;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.runningsnail.demos.MainHandler;
import com.runningsnail.demos.R;
import com.runningsnail.demos.ScreenReceiver;
import com.runningsnail.demos.common.utils.HiLogger;
import com.runningsnail.demos.common.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @BindView(R.id.lv_content)
    ListView listView;
    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.iv_startup)
    ImageView ivStartup;

	private List<String> itemsData = new ArrayList<>();
	private List<String> clickItemsData = new ArrayList<>();
	private MainData mainData = new MainData();
	private ScreenReceiver screenReceiver;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        String name = this.getClass().getName();
        HiLogger.d(TAG, "class name:" + name);
		readStartActivityMessage();
	}

	private void readStartActivityMessage() {
		try {
			mainData = PathCenter.getMainData();
			parseItemsData();
			MainHandler.getInstance().post(new Runnable() {
				@Override
				public void run() {
					ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this, R.layout.item_main_list,
							itemsData);
					listView.setAdapter(arrayAdapter);
					listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
							String content = clickItemsData.get(position);
							HiLogger.d(TAG, "content %s", content);
							if (content.toLowerCase().contains("activity")) {
								startActivity(clickItemsData.get(position));
							}
						}
					});

					IntentFilter intentFilter = new IntentFilter();
					intentFilter.addAction(Intent.ACTION_SCREEN_ON);
					intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
					screenReceiver = new ScreenReceiver();
					registerReceiver(screenReceiver, intentFilter);
					HiLogger.i(TAG, "执行动画");
					ivStartup.animate().alpha(0.01f).setDuration(2000).setListener(new Animator.AnimatorListener() {
						@Override
						public void onAnimationStart(Animator animation) {

						}

						@Override
						public void onAnimationEnd(Animator animation) {
							ivStartup.setVisibility(View.GONE);
						}

						@Override
						public void onAnimationCancel(Animator animation) {
							ivStartup.setVisibility(View.GONE);
						}

						@Override
						public void onAnimationRepeat(Animator animation) {

						}
					}).start();
				}
			});
		} catch (Exception e) {
			HiLogger.e(TAG, "Exception", e);
		}
	}


	@Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 返回值有三个 0代表本周,1代表上周,2代表更早,-1代表未知
     *
     * @param time
     * @return
     */
    public int computeAreaTime(long time) {
        Calendar calendar = Calendar.getInstance();
        long currentTime = calendar.getTime().getTime();
        //当天零点时间
        long zoneTime = currentTime - currentTime % (24 * 60 * 60 * 1000) - 8 * 60 * 60 * 1000;
        calendar.setTime(new Date(zoneTime));
        //本周第一天时间点
        int firstDayOfWeek = calendar.getFirstDayOfWeek();
        calendar.set(Calendar.DAY_OF_WEEK, firstDayOfWeek + 1);
        long thisWeekFirstDayTime = calendar.getTime().getTime();
        //上一周第一天时间点
        calendar.add(Calendar.DAY_OF_WEEK, -7);
        long lastWeekFirstDayTime = calendar.getTime().getTime();
        if (time < lastWeekFirstDayTime) {
            return 2;
        }
        if (time >= lastWeekFirstDayTime && time < thisWeekFirstDayTime) {
            return 1;
        }
        return 0;
    }

    private void startActivity(String s) {
        try {
            Intent intent = new Intent(this, Class.forName(s));
            startActivity(intent);
            ToastUtil.showToast(this, "startActivity success");
        } catch (ClassNotFoundException e) {
            HiLogger.e(TAG, "class load error", e);
        }
    }

	public void parseItemsData() {
		List<MainData.ContentBean> content = mainData.getContent();
		for (int i = 0; i < content.size(); i++) {
			MainData.ContentBean contentBean = content.get(i);
			itemsData.add(contentBean.getTitle());
			clickItemsData.add("");
			List<MainData.ContentBean.SubBean> sub = contentBean.getSub();
			for (int j = 0; j < sub.size(); j++) {
				MainData.ContentBean.SubBean subBean = sub.get(j);
				itemsData.add(subBean.getSubTitle());
				clickItemsData.add(subBean.getPath());
			}
		}
    }

	@Override
	protected void onDestroy() {
		super.onDestroy();
		HiLogger.i(TAG, "onDestroy");
		unregisterReceiver(screenReceiver);
	}
}
