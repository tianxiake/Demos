package com.runningsnail.demos;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import androidx.multidex.MultiDex;

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
    }

    /**
     * 多Dex的支持,解决方法数超64K的问题
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
