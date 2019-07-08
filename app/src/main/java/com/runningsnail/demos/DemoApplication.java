package com.runningsnail.demos;

import android.app.Application;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.StrictMode;

/**
 * @author liuyongjie create on 2018/12/5.
 */
public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build());

        Thread.setDefaultUncaughtExceptionHandler(new CrashHandler());

        String name;
        HandlerThread handlerThread = new HandlerThread("handler");
        handlerThread.start();
        Handler handler = new Handler(){


        };
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },300);
    }
}
