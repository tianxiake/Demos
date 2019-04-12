package com.runningsnail.demos.activity.tv;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MockActivity extends AppCompatActivity {

    public static final String TAG = "MockActivity";
    @BindView(R.id.btn_start_vod)
    Button btnStartOther;
    @BindView(R.id.btn_start_series)
    Button btnStartSeries;
    @BindView(R.id.btn_start_series2)
    Button btnStartSeries2;
    @BindView(R.id.btn_start_web_activity)
    Button btnStartWebActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_player);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_start_vod, R.id.btn_start_series,
            R.id.btn_start_series2, R.id.btn_start_web_activity})
    public void onClick(View v) {
        HiLogger.d(TAG, "启动三方播放Apk");
        int id = v.getId();
        switch (id) {
            case R.id.btn_start_vod:
                playVod();
                break;
            case R.id.btn_start_series:
                playSeries();
                break;
            case R.id.btn_start_series2:
                playSeries2();
                break;
            case R.id.btn_start_web_activity:
                startHeNanWebActivity();
                break;
            default:
                break;

        }
    }

    private void startHeNanWebActivity() {
        HiLogger.d(TAG, "startHeNanWebActivity");
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("cn.gd.snm.station",
                "cn.gd.snm.station.activity.LauncherActivity"));
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        //传递参数
        intent.putExtra("webUrl", "http://www.weather.com.cn/");
        startActivity(intent);

    }

    private void playSeries2() {
        ComponentName componentName = new ComponentName(
                "cn.gd.snm.snmcm",
                "cn.gd.snm.snmcm.ThreePartyVideoLaunchActivity");
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt("playType", 1);
        bundle.putString("mediaId", "02000002000000012016062999983011");
        bundle.putString("mediaName", "HellWorld");
        bundle.putString("seriesCode", "02000006000001032016101200000015");
        bundle.putString("packageNameFrom", this.getPackageName());
        bundle.putString("contentType", "series2");
        bundle.putString("activityFrom", "com.runningsnail.demos.activity.tv.MockActivity");
        intent.putExtras(bundle);
        intent.setComponent(componentName);
        startActivity(intent);
    }

    private void playSeries() {
        ComponentName componentName = new ComponentName(
                "cn.gd.snm.snmcm",
                "cn.gd.snm.snmcm.ThreePartyVideoLaunchActivity");
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt("playType", 1);
        bundle.putString("mediaId", "02000000000000010000000028503417");
        bundle.putString("mediaName", "HellWorld");
        bundle.putString("seriesCode", "02000000000000100000000000214633");
        bundle.putString("packageNameFrom", this.getPackageName());
        bundle.putString("contentType", "series");
        bundle.putString("activityFrom", "com.runningsnail.demos.activity.tv.MockActivity");
        intent.putExtras(bundle);
        intent.setComponent(componentName);
        startActivity(intent);
    }

    private void playVod() {
        ComponentName componentName = new ComponentName(
                "cn.gd.snm.snmcm",
                "cn.gd.snm.snmcm.ThreePartyVideoLaunchActivity");
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt("playType", 1);
        bundle.putString("mediaId", "02000038000000012016090199794560");
        bundle.putString("mediaName", "HellWorld");
        bundle.putString("packageNameFrom", this.getPackageName());
        bundle.putString("activityFrom", "com.runningsnail.demos.activity.tv.MockActivity");
        intent.putExtras(bundle);
        intent.setComponent(componentName);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
