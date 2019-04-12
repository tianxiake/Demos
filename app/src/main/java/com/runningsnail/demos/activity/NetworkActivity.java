package com.runningsnail.demos.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.FileIOUtils;

import java.io.File;
import java.util.Arrays;

public class NetworkActivity extends AppCompatActivity {

	public static final String TAG = "NetworkActivity";
	NetWorkReceiver netWorkReceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_network);
		try {
			netWorkReceiver = new NetWorkReceiver();
			IntentFilter intentFilter = new IntentFilter();
			intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
			registerReceiver(netWorkReceiver, intentFilter);
		} catch (Exception e) {
			StackTraceElement[] stackTrace = e.getStackTrace();
			String s = Arrays.toString(stackTrace);
			byte[] bytes = s.getBytes();
			File externalStorageDirectory = NetworkActivity.this.getExternalCacheDir();
			File file = new File(externalStorageDirectory.getAbsolutePath()+"/loglog.cat");
			boolean b = FileIOUtils.writeBytesToFile(bytes, file, false);
			Toast.makeText(this, "文件写入成功:" + b, Toast.LENGTH_SHORT).show();
			Toast.makeText(this, externalStorageDirectory.getAbsolutePath(), Toast.LENGTH_SHORT).show();
		}
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(netWorkReceiver!=null){
			unregisterReceiver(netWorkReceiver);
		}
	}

	public class NetWorkReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
//			if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
			try {
				Log.d(TAG, "网络状态:" + intent.toString());
				ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
				//获取ConnectivityManager对象对应的NetworkInfo对象
				//获取WIFI连接的信息
				NetworkInfo wifiNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
				//获取移动数据连接的信息
				NetworkInfo dataNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
				//有限网络
				NetworkInfo localNetwork = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET);
				if ((localNetwork != null && localNetwork.isConnected()) || (wifiNetworkInfo != null && wifiNetworkInfo.isConnected()) || (dataNetworkInfo != null && dataNetworkInfo.isConnected())) {
					Toast.makeText(context, "网络已连接", Toast.LENGTH_SHORT).show();
				} else{
					Toast.makeText(context, "网络已断开", Toast.LENGTH_SHORT).show();
				}
			} catch (Exception e) {
				StackTraceElement[] stackTrace = e.getStackTrace();
				String s = Arrays.toString(stackTrace);
				byte[] bytes = s.getBytes();
				File externalStorageDirectory = NetworkActivity.this.getExternalCacheDir();
				File file = new File(externalStorageDirectory.getAbsolutePath()+"/log.cat");
				boolean b = FileIOUtils.writeBytesToFile(bytes, file, false);
				Toast.makeText(context, "文件写入成功:" + b, Toast.LENGTH_SHORT).show();
				Toast.makeText(context, externalStorageDirectory.getAbsolutePath(), Toast.LENGTH_SHORT).show();
			}
//				if (localNetwork.isConnected() && wifiNetworkInfo.isConnected() && dataNetworkInfo.isConnected()) {
//					Toast.makeText(context, "本地网络已连接,WIFI已连接,移动数据已连接", Toast.LENGTH_SHORT).show();
//				} else if (wifiNetworkInfo.isConnected() && dataNetworkInfo.isConnected()) {
//					Toast.makeText(context, "WIFI已连接,移动数据已连接", Toast.LENGTH_SHORT).show();
//				} else if (wifiNetworkInfo.isConnected() && !dataNetworkInfo.isConnected()) {
//					Toast.makeText(context, "WIFI已连接,移动数据已断开", Toast.LENGTH_SHORT).show();
//				} else if (!wifiNetworkInfo.isConnected() && dataNetworkInfo.isConnected()) {
//					Toast.makeText(context, "WIFI已断开,移动数据已连接", Toast.LENGTH_SHORT).show();
//				} else {
//					Toast.makeText(context, "WIFI已断开,移动数据已断开", Toast.LENGTH_SHORT).show();
//				}
		}
	}
}
