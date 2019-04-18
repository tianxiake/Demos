package com.runningsnail.demos.activity.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;

import com.runningsnail.demos.common.utils.HiLogger;

/**
 * @author yongjie created on 2019-04-18.
 */
public class MyBroadcastReceiver extends BroadcastReceiver {
    public static final String TAG = "MyBroadcastReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(action)){
            String state = intent.getStringExtra(WifiManager.EXTRA_WIFI_STATE);
            HiLogger.d(TAG, "netWork change state:" + state);
        }
    }
}
