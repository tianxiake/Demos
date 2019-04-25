package com.runningsnail.demos.activity.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
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
            WifiInfo wifiInfo = intent.getParcelableExtra(WifiManager.EXTRA_WIFI_INFO);
            HiLogger.d(TAG, "netWork change wifiInfos:" + wifiInfo);
        }
    }
}
