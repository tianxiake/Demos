package com.runningsnail.demos.activity.broadcast;

import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.runningsnail.demos.R;

/**
 * 动态广播
 *
 * @author yongjie created on 2019-04-18.
 */
public class DynamicBroadCastActivity extends AppCompatActivity {


    private MyBroadcastReceiver myBroadcastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_cast);

        //配置Intent-Filter接受指定的广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);

        //注册广播
        myBroadcastReceiver = new MyBroadcastReceiver();
        registerReceiver(myBroadcastReceiver, intentFilter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(myBroadcastReceiver!=null){
            unregisterReceiver(myBroadcastReceiver);
        }
    }
}
