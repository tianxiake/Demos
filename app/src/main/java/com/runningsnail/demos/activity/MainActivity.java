package com.runningsnail.demos.activity;

import android.animation.Animator;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;
import com.runningsnail.demos.common.utils.ToastUtil;

import java.io.File;
import java.util.Arrays;
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

    private List<String> itemsData;
    private List<String> clickItemsData;

    private final int message_a = 0xfff;
    private final int message_b = 0xbbb;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case message_a:
//                    handler.removeMessages(message_b);
                    HiLogger.i(TAG, "message_a");
                    break;
                case message_b:
                    HiLogger.i(TAG, "message_b");
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        String name = this.getClass().getName();
        HiLogger.d(TAG, "class name:" + name);
        itemsData = getItemsData();
        clickItemsData = getClickItemsData();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.item_main_list,
                itemsData);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String content = clickItemsData.get(position);
                HiLogger.d(TAG, "content %s", content);
                if (content.contains("activity")) {
                    startActivity(clickItemsData.get(position));
                }
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
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

        File cacheDir = this.getCacheDir();
        HiLogger.i(TAG, "cacheDir: %s", cacheDir.getAbsolutePath());

	    String url = "http://120.87.4.103/PLTV/88888973/224/3221225744/10000100000000060000000000334567_0.smil/index.m3u8?rrsip=120.87.19.109&fmt=ts2hls&servicetype=1&icpid=&accounttype=1&limitflux=-1&limitdur=-1&GuardEncType=2&accountinfo=p49v5jIfvzt5O7IlSZAOFothT6NCTKx5J%2BpGD8oFMAI6wMuLMl3oi7cXA9ryuJegtc39pYzYSmHw%2BiYMKqJsDXdBtVsV6b%2Fr2pgml9B6xCpvXvtpPyW1ZTL1lxghKXV8%3A20190926102838%2C11002000000556%2C112.96.29.123%2C20190926102838%2C10000100000000050000000000296969%2CD2822041D245E770558D10433CBB68B7%2C-1%2C1%2C1%2C%2C%2C2%2C%2C%2C%2C2%2CEND";
	    Uri parse = Uri.parse(url);
	    String rrsip = parse.getQueryParameter("rrsi");
	    String host = parse.getHost();
	    HiLogger.i(TAG, "rrsip:%s  host:%s", rrsip, host);


	    HiLogger.i(TAG, "状态栏1:" + getStatusHeight1(this) + ",状态栏2:" + getStatusHeight2(this) +
			    ",导航栏:" + getNavigationBarHeight(this) + "状态栏是否可见:" + isStatusBarShown(this));

	    ivStartup.postDelayed(new Runnable() {
		    @Override
		    public void run() {
				goToApp();
		    }
	    }, 3000);

    }

    private void goToApp(){
	    ComponentName componentName = new ComponentName("com.utstar.smc","com.utstar.smc.activity.HomeActivity");
	    Intent intent = new Intent();
	    intent.setComponent(componentName);
	    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    startActivity(intent);
    }


	public static int getStatusHeight1(Context context) {
		int statusHeight = -1;
		try {
			Class<?> clazz = Class.forName("com.android.internal.R$dimen");
			Object object = clazz.newInstance();
			int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
			statusHeight = context.getResources().getDimensionPixelSize(height);
			Log.i("TAG", "statusHeight:" + statusHeight);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusHeight;
	}

	public static boolean isStatusBarShown(Activity context) {
		WindowManager.LayoutParams params = context.getWindow().getAttributes();
		int paramsFlag = params.flags & (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
		return paramsFlag == params.flags;
	}

	public static int getStatusHeight2(Context context) {
		int statusBarHeight1 = -1;
		//获取status_bar_height资源的ID
		int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			//根据资源ID获取响应的尺寸值
			statusBarHeight1 = context.getResources().getDimensionPixelSize(resourceId);
		}
		return statusBarHeight1;
	}

	private int getNavigationBarHeight(Context context) {
		Resources resources = context.getResources();
		int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
		int height = resources.getDimensionPixelSize(resourceId);
		Log.v("dbw", "Navi height:" + height);
		return height;
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

    public List<String> getItemsData() {
        String[] stringArray = getResources().getStringArray(R.array.item_data);
        return Arrays.asList(stringArray);
    }

    public List<String> getClickItemsData() {
        String[] stringArray = getResources().getStringArray(R.array.click_item_data);
        return Arrays.asList(stringArray);
    }

	public static void main(String[] args) {

		int a = (int) 0.3f;
		byte a1 = (byte) ((a >> 8) & 0xFF);
		byte a2 = (byte) (a >> 16 & 0xFF);
		byte a3 = (byte) (a >> 24 & 0xff);
		byte a4 = (byte) (a >> 32 & 0xff);

		String s = Integer.toBinaryString(a1 & 0xFF);
		System.out.println(s);

//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(a4).append(a3).append()
	}
}
