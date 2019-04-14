package com.runningsnail.demos.activity.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.runningsnail.demos.R;
import com.runningsnail.demos.common.utils.HiLogger;

public class ForegroundService extends Service {

    public static final String TAG = "ForegroundService";

    public ForegroundService() {
    }

    private MyBinder myBinder = new MyBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        HiLogger.d(TAG, "onCreate()");
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.pic_one)
                .setContentText("HelloWorld")
                .build();
        //启动前台Service
        startForeground(0xff, notification);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        HiLogger.d(TAG, "onStartCommand()");
        return super.onStartCommand(intent, flags, startId);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        HiLogger.d(TAG, "onBind()");
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        HiLogger.d(TAG, "onUnbind()");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        HiLogger.d(TAG, "onDestroy()");
    }

    public class MyBinder extends Binder {
        public static final String TAG = "MyBinder";

        public void onStart() {
            HiLogger.d(TAG, "onStart()");
        }

        public void onStop() {
            HiLogger.d(TAG, "onStop()");
        }
    }
}
